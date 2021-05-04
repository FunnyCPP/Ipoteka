package com.codart.ipoteka.data.entities

data class LoginResponse(
        var data: User?,
        var error: List<Any>,
        var success: Int
)

data class User(
    var account_custom_field: List<Any>,
    var address_id: String,
    var approved: String,
    var cart: String,
    var cart_count_products: Int,
    var code: String,
    var custom_field: String,
    var custom_fields: List<Any>,
    var customer_group_id: String,
    var customer_id: String,
    var date_added: String,
    var email: String,
    var fax: String,
    var firstname: String,
    var ip: String,
    var language_id: String,
    var lastname: String,
    var newsletter: String,
    var safe: String,
    var status: String,
    var store_id: String,
    var telephone: String,
    var wishlist: List<Any>,
    var wishlist_total: String
)