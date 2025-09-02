package com.acme.oop.sales.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object representing a Product Identifier in the Sales bounded context.
 *
 * @summary
 * This class encapsulates the unique identifier for a product, ensuring immutability and type safety.
 *
 * @author Open-Source Application Development Team
 * @version 1.0
 */
public record ProductId(UUID value) {

    /**
     * Constructs a ProductId with the given UUID value.
     *
     * @param value the UUID representing the product identifier
     * @throws IllegalArgumentException if the value is null
     */
    public ProductId {
        if (Objects.isNull(value))
            throw new IllegalArgumentException("ProductId cannot be null");
    }

    /**
     * Constructs a ProductId with a randomly generated UUID.
     */
    public ProductId() {
        this(UUID.randomUUID());
    }

}
