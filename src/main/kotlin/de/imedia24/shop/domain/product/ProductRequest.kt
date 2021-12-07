package de.imedia24.shop.domain.product

import java.math.BigDecimal

class ProductRequest {
    val sku: String;
    val name: String;
    var description: String? = null;
    val price: BigDecimal;
    val stock : String

    constructor(sku: String, name: String, description: String, price: BigDecimal, stock: String) {
        this.sku = sku
        this.name = name
        this.description = description
        this.price = price
        this.stock = stock
    }

}
