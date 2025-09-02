package com.acme.oop.shared.domain.model.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Value Object representing a monetary amount in a specific currency.
 *
 * Invariants:
 * - amount must be non-null and non-negative
 * - currency must be non-null
 * - amount must have at most two decimal places (for currencies like USD)
 */
public record Money(BigDecimal amount, Currency currency) {
    /**
     * Constructor enforcing invariants.
     * @param amount    The monetary amount, must be non-null and non-negative
     * @param currency  The currency, must be non-null
     */
    public Money {
        if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount must be non-null and non-negative");
        if (Objects.isNull(currency))
            throw new IllegalArgumentException("Currency must be non-null");
        if (amount.scale() > currency.getDefaultFractionDigits())
            throw new IllegalArgumentException("Amount has more decimal places than allowed for the currency");
    }

    /**
     * Returns a Money instance representing zero amount in USD.
     * @return Money instance with zero amount in USD
     */
    public static Money zero() {
        return new Money(BigDecimal.ZERO, Currency.getInstance("USD"));
    }

    /**
     * Adds two Money instances of the same currency.
     * @param other The other Money instance to add
     * @return A new Money instance representing the sum
     * @throws IllegalArgumentException if currencies do not match
     */
    public Money add(Money other) {
        if (!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot add money with different currencies");
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * Multiplies the monetary amount by a non-negative integer.
     * @param multiplier The non-negative integer to multiply by
     * @return A new Money instance representing the product
     * @throws IllegalArgumentException if multiplier is negative
     */
    public Money multiply(int multiplier) {
        if (multiplier < 0)
            throw new IllegalArgumentException("Multiplier must be non-negative");
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)), this.currency);
    }

}
