package com.itau.pix.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.pix.HttpRequest;
import com.itau.pix.SpringIntegrationTest;
import com.itau.pix.dto.PixKeyRequestTest;
import com.itau.pix.handler.payload.ApiError;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class StepDefinition extends SpringIntegrationTest {

    private ResponseEntity<?> response;
    private PixKeyRequestTest pixKeyRequestTest;
    private String payload;

    private final HttpRequest httpRequest;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final Map<String, String> mapParams = new HashMap<>();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(final Object fromValue, final Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    @Given("database is clean")
    public void databaseIsClean () {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "pix.tb_pix_key");
    }

    @And("execute sqlFile in database with name {string}")
    public void executeSqlFileInDatabase(final String file) throws IOException {
        log.info("M=executeSqlFileInDatabase, sqlFile={}", file);

        final InputStream inputStream = new ClassPathResource("__files/database/" + file).getInputStream();
        final InputStreamReader sqlReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        jdbcTemplate.execute(FileCopyUtils.copyToString(sqlReader));
    }

    @Given("query param {string} with value {string}")
    public void queryParamWithValue(final String name, final String value) {
        log.info("M=queryParamWithValue name={}, value={}", name, value);
        mapParams.put(name, value);
    }

    @Given("create pix-key request is build with new information:")
    public void createPostRequestIsBuildWithNewInformation(final PixKeyRequestTest pixKeyRequestTest) throws JsonProcessingException {
        this.pixKeyRequestTest = pixKeyRequestTest;
        payload = objectMapper.writeValueAsString(pixKeyRequestTest);
    }

    @When("a GET request to resource {string} is made")
    public void aGetRequestToResourceIsMade(final String url) {
        log.info("M=aGetRequestToResourceIsMade url={}", url);

        response = httpRequest.doGetRequest(url, mapParams);
    }

    @When("a Post request to resource {string} is made")
    public void aPostRequestToResourceIsMade(final String url) {
        log.info("M=aPostRequestToResourceIsMade url={}, payload={}", url, payload);

        response = httpRequest.doPostRequest(url, payload);
    }

    @When("a Put request to resource {string} is made")
    public void aPutRequestToResourceIsMade(final String url) {
        log.info("M=aPostRequestToResourceIsMade url={}, payload={}", url, payload);

        response = httpRequest.doPutRequest(url, payload);
    }

    @Then("it should return http status code {int}")
    public void itShouldReturnHttpStatusCode(final int expectedStatusCode) throws IOException {
        final int statusCode = response.getStatusCode().value();
        Object body = null;

        if (Objects.nonNull(response.getBody())) {
            body = objectMapper.readValue(response.getBody().toString(), Object.class);
        }

        log.info("M=itShouldReturnHttpStatusCode statusCode={}, body={}", statusCode, body);

        Assert.assertEquals(expectedStatusCode, statusCode);
    }

    @And("response body for request must match file {string}")
    public void responseBodyForRequestMustMatchFile(final String fileName) throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource("__files/expected/" + fileName).getFile());

        final Object actual = objectMapper.readValue(response.getBody().toString(), Object.class);
        final Object expected = objectMapper.readValue(file, Object.class);

        log.info("M=responseBodyForRequestMustMatchFile actual={}", actual);
        log.info("M=responseBodyForRequestMustMatchFile expected={}", expected);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @And("response message must match {string}")
    public void responseMessageMustMatch(final String expectedMessage) throws JsonProcessingException {
        String actualMessage = expectedMessage.isBlank() ? expectedMessage : null;

        if (Objects.nonNull(response.getBody())) {
            actualMessage = objectMapper.readValue(response.getBody().toString(), ApiError.class).getMessage();
        }

        Assertions.assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Given("create {int} posts in database for user {int}")
    public void createRepostsInDatabase(int count, int userId) {
        final String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (int i = 0; i < count; i++) {
            final String sql = "INSERT INTO tb_post(id_post, txt_message, dat_creation, id_user) " +
                    "values (nextval('sq_post_id'), 'post mocked', to_date('" + today + "', 'YYYY-MM-DD'), "+userId+")";

            jdbcTemplate.execute(sql);
        }
    }
}
