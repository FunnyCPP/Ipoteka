package com.codart.ipoteka.data.entities

data class FirebaseTokenResponse(
    var data: Any,
    var error: List<Any>,
    var success: Int
)