package com.itau.pix.strategy.key;

import com.itau.pix.entity.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class EmailStrategyTest {

    @Test
    void shouldIsValid() {
        final EmailStrategy emailStrategy = new EmailStrategy();
        final String key = "teste@mail.com";

        Assertions.assertTrue(emailStrategy.isValid(key));
    }

    @Test
    void shouldIsInvalid() {
        final EmailStrategy emailStrategy = new EmailStrategy();
        final String key1 = "testemail.com";
        final String key2 = "teste@mail.commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm";

        Assertions.assertFalse(emailStrategy.isValid(key1));
        Assertions.assertFalse(emailStrategy.isValid(key2));
    }

    @Test
    void shouldIsValidType() {
        final EmailStrategy emailStrategy = new EmailStrategy();

        Assertions.assertEquals(emailStrategy.getType(), KeyType.EMAIL);
    }

}
