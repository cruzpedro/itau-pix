package com.itau.pix;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HttpRequest {

    private final TestRestTemplate testRestTemplate;

    public ResponseEntity<?> doGetRequest(final String path, final Map<String, String> mapParams) {
        final String url = getUrlWithQueryParams(path, mapParams);
        return doRequest(url, HttpMethod.GET, "");
    }

    public ResponseEntity<?> doPostRequest(final String url, final String payload) {
        return doRequest(url, HttpMethod.POST, payload);
    }

    public ResponseEntity<?> doPutRequest(final String url, final String payload) {
        return doRequest(url, HttpMethod.PUT, payload);
    }

    private String getUrlWithQueryParams(final String path, final Map<String, String> mapParams) {
        final StringBuilder url = new StringBuilder(path);

        if (!mapParams.isEmpty()) {
            url.append("?");

            mapParams.forEach((property, value) -> {
                if (!value.isBlank()) {
                    url.append(property).append("=").append(value).append("&");
                }
            });
            url.replace(url.lastIndexOf("&"), url.length(), "");
        }

        return url.toString();
    }

    private ResponseEntity<?> doRequest(final String url, final HttpMethod httpMethod, final String payload) {
        try {
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<String> httpEntity = new HttpEntity<>(payload, httpHeaders);

            return testRestTemplate.exchange(new URI(url), httpMethod, httpEntity, String.class);
        } catch (HttpStatusCodeException ex) {
            return ResponseEntity.status(ex.getStatusCode()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
