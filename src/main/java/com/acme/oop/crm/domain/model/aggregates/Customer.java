package com.acme.oop.crm.domain.model.aggregates;

import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

/**
 * Customer aggregate root
 * Represents a customer in the Customer Relationship Management (CRM) bounded context.
 * @summary
 * The Customer class encapsulates customer details and behaviors, ensuring data integrity and consistency.
 * It includes validation logic to prevent invalid states and provides methods to update and retrieve customer information.
 *
 * @author Open-Source Applications Development Team
 * @version 1.0
 */
@Getter
public class Customer {
    private final CustomerId id;
    @Setter @NonNull private String name;
    @Setter @NonNull private String email;
    @Setter @NonNull private Address address;

    /**
     * Constructs a new Customer instance with the specified name, email, and address.
     * Validates input parameters to ensure they are not null or blank.
     *
     * @param name    the name of the customer, must not be null or blank
     * @param email   the email of the customer, must not be null or blank
     * @param address the address of the customer, must not be null
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Customer(String name, String email, Address address) {
        if (Objects.isNull(name) || name.isBlank())
            throw new IllegalArgumentException("Customer name cannot be null or blank");
        if (Objects.isNull(email) || email.isBlank())
            throw new IllegalArgumentException("Customer email cannot be null or blank");
        if (Objects.isNull(address))
            throw new IllegalArgumentException("Customer address cannot be null");
        this.id = new CustomerId();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Updates the customer's contact information.
     * Validates input parameters to ensure they are not null or blank.
     *
     * @param email   the new email of the customer, must not be null or blank
     * @param address the new address of the customer, must not be null
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public void updateContactInfo(@NonNull String email, @NonNull Address address) {
        if (email.isBlank())
            throw new IllegalArgumentException("Customer email cannot be blank");
        setEmail(email);
        setAddress(address);
    }

    /**
     * Retrieves the customer's contact information in a formatted string.
     *
     * @return a string containing the customer's name, email, and address in the format "Name <email>, Address"
     */
    public String getContactInfo() {
        return String.format("%s <%s>, %s", name, email, address);
    }
}
