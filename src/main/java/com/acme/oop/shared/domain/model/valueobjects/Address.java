package com.acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;

/**
 * Value object representing an address.
 * Immutable and validated upon creation.
 * @param street the street name, cannot be null or blank
 * @param number the street number, cannot be null or blank
 * @param city the city name, cannot be null or blank
 * @param postalCode the postal code, cannot be null or blank
 * @param country the country name, cannot be null or blank
 *
 * @author Open-Source Applications Development Team
 * @version 1.0
 */
public record Address(String street, String number, String city, String postalCode, String country) {
    /***
     * Constructs an Address value object with validation.
     * @throws IllegalArgumentException if any parameter is null or blank
     */
    public Address {
        if (street == null || street.isBlank())
            throw new IllegalArgumentException("Street cannot be null or blank");
        if (number == null || number.isBlank())
            throw new IllegalArgumentException("Number cannot be null or blank");
        if (Objects.isNull(city) || city.isBlank())
            throw new IllegalArgumentException("City cannot be null or blank");
        if (Objects.isNull(postalCode) || postalCode.isBlank())
            throw new IllegalArgumentException("Postal code cannot be null or blank");
        if (Objects.isNull(country) || country.isBlank())
            throw new IllegalArgumentException("Country cannot be null or blank");
    }

    /***
     * Returns a string representation of the address.
     * @return an address string in the format "street number, city, postalCode, country"
     */
    @Override
    public String toString() {
        return String.format("%s %s, %s, %s, %s", street, number, city, postalCode, country);
    }
}
