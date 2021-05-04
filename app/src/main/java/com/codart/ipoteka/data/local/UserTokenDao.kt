package com.codart.ipoteka.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codart.ipoteka.data.entities.TOKEN_TABLE_NAME
import com.codart.ipoteka.data.entities.Token

@Dao
interface UserTokenDao {
    @Query("SELECT * FROM $TOKEN_TABLE_NAME LIMIT 1")
    fun getToken(): LiveData<Token>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(token: Token)
}