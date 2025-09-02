package com.acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

/**
 * Value object representing a Customer Identifier.
 * @summary
 * This class is immutable and ensures that the identifier is never null.
 *
 * @author Open-Source Applications Development Team
 * @version 1.0
 */
public record CustomerId(UUID value) {

    /**
     * Constructs a CustomerId with the given UUID value.
     *
     * @param value the UUID value of the customer identifier
     * @throws IllegalArgumentException if the value is null
     */
    public CustomerId {
        if (Objects.isNull(value))
            throw new IllegalArgumentException("Customer identifier cannot be null");
    }

    /**
     * Constructs a CustomerId with a randomly generated UUID value.
     */
    public CustomerId() {
        this(UUID.randomUUID());
    }

    /**
     * Returns the string representation of the CustomerId.
     *
     * @return the string representation of the contained UUID value
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
