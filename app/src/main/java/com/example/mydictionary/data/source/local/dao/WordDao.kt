package com.example.mydictionary.data.source.local.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mydictionary.data.source.local.entities.WordEntity

@Dao
interface WordDao : BaseDao<WordEntity> {

    @Query("SELECT * FROM words")
    fun getAllWords(): LiveData<List<WordEntity>>


    @Query("SELECT * FROM words")
    fun getAllWordsCursor(): Cursor


    @Query("SELECT * FROM words WHERE en_word LIKE :query")
    fun getCursorByQuery(query: String): Cursor
}