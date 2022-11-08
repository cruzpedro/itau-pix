package com.itau.pix.specification;

import com.itau.pix.entity.KeyType;
import com.itau.pix.entity.PixKey;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Objects;

public class PixKeySpecification {

    public static Specification<PixKey> type(KeyType type) {
        return (root, query, builder) -> Objects.nonNull(type)
                ? builder.equal(root.get("type"), type)
                : null;
    }

    public static Specification<PixKey> account(Integer agency, Integer account) {
        return (root, query, builder) -> Objects.nonNull(agency) && Objects.nonNull(account)
                ? builder.and(builder.equal(root.get("agency"), agency), builder.equal(root.get("account"), account))
                : null;
    }

    public static Specification<PixKey> customerName(String customer) {
        return (root, query, builder) -> Objects.nonNull(customer)
                ? builder.equal(root.get("customerName"), customer)
                : null;
    }

    public static Specification<PixKey> date(LocalDateTime creationDate, LocalDateTime disableDate) {
        return (root, query, builder) -> {
            if (Objects.nonNull(creationDate))
                return builder.equal(root.get("creationDate"), creationDate);
            else if (Objects.nonNull(disableDate))
                return builder.equal(root.get("disableDate"), disableDate);
            else
                return null;
        };
    }

}
