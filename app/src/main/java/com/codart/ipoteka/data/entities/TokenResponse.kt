package com.codart.ipoteka.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val TOKEN_TABLE_NAME = "token"
const val TOKEN_ID = 11322

data class TokenResponse(
        val success: Int,
        val error: Array<String>,
        val data: Token
)
@Entity(tableName = TOKEN_TABLE_NAME)
data class Token(
        @PrimaryKey
        val token_id: Int=TOKEN_ID,
        val access_token: String,
        val expires_in: Int,
        val token_type: String
)
