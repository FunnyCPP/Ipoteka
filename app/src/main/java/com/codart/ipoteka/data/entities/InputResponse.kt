package com.codart.ipoteka.data.entities

data class InputResponse(
    var data: String,
    var error: List<Any>,
    var success: Int
)