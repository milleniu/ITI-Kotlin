package dev.hugozammit.mvvm.model

import java.io.Serializable

data class ProductFamily(
    var id: Int,
    var products: List<Product>,
    var name: String,
    var description: String,
    var totalPages: Int
) : Serializable