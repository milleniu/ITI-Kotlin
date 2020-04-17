package dev.hugozammit.mvvm.model

import java.io.Serializable

data class Product (
    var id: Int,
    var categoryId: Int,
    var name: String,
    var url: String,
    var description: String,
    var salePrice: SalePrice
) : Serializable