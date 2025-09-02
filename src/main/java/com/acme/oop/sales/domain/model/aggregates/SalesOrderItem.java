package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * SalesOrderItem represents an item in a sales order, including product details,
 * quantity, and unit price.
 * @summary SalesOrderItem is an entity that holds information about a product in a sales order.
 *
 * @author Open-Source Application Development Team
 * @version 1.0
 */
@Getter
public class SalesOrderItem {
    private final ProductId productId;
    @Setter private int quantity;
    @Setter private Money unitPrice;

    /**
     * Constructs a SalesOrderItem with the specified product ID, quantity, and unit price.
     *
     * @param productId The {@link ProductId} unique identifier of the product.
     * @param quantity  The quantity of the product ordered. Must be greater than zero.
     * @param unitPrice The {@link Money} price per unit of the product. Must be greater than zero and have a valid currency.
     * @throws IllegalArgumentException if quantity is less than or equal to zero,
     *                                  if unitPrice is less than or equal to zero,
     *                                  or if unitPrice has an invalid currency.
     */
    public SalesOrderItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
        if (unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Unit price must be greater than zero");
        if (Objects.isNull(unitPrice.currency())
                || Objects.isNull(unitPrice.currency().getCurrencyCode())
                || unitPrice.currency().getCurrencyCode().isBlank())
            throw new IllegalArgumentException("Unit price currency must be valid");
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total price for this sales order item.
     *
     * @return The total price as a {@link Money} object, calculated by multiplying the unit price by the quantity.
     */
    public Money calculateItemTotal() {
        return unitPrice.multiply(quantity);
    }
}
