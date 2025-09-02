package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * SalesOrder aggregate root representing a customer's order in the sales bounded context.
 * @summary This class encapsulates the details of a sales order, including the customer ID,
 * order date, list of items, and total amount. It provides methods to add items and calculate the total order amount.
 * @author Open-Source Application Development Team
 * @version 1.0
 */
public class SalesOrder {
    @Getter private final UUID id;
    @Getter private final CustomerId customerId;
    @Getter private LocalDateTime orderDate;
    private final List<SalesOrderItem> items;
    @Getter private Money totalAmount;

    /** * Constructor for creating a new SalesOrder
     * @param customerId the {@link CustomerId} ID of the customer placing the order
     */
    public SalesOrder(@NonNull CustomerId customerId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new java.util.ArrayList<>();
        this.totalAmount = Money.zero();
    }

    /** * Adds an item to the sales order
     * @param productId the {@link ProductId} ID of the product being added
     * @param quantity the quantity of the product being added
     * @param unitPrice the {@link Money} unit price of the product being added
     * @throws IllegalArgumentException if quantity is less than or equal to zero or if unit price is less than or equal to zero
     */
    public void addItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (unitPrice.amount().compareTo(Money.zero().amount()) <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than zero");
        }
        SalesOrderItem newItem = new SalesOrderItem(productId, quantity, unitPrice);
        items.add(newItem);
        this.totalAmount = calculateOrderTotal();
    }

    /** * Calculates the total amount of the sales order by summing the total of each item
     * @return the {@link Money} total amount of the sales order
     */
    public Money calculateOrderTotal() {
        return this.items
                .stream()
                .map(SalesOrderItem::calculateItemTotal)
                .reduce(Money.zero(), Money::add);
    }

    public String getOrderTotalAsString() {
        return this.totalAmount.toString();
    }
}
