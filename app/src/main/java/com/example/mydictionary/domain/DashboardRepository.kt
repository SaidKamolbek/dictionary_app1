package com.example.mydictionary.domain

import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.mydictionary.data.source.local.entities.WordEntity

interface DashboardRepository {

    fun getAllWords(): LiveData<List<WordEntity>>

    fun getWordsCursor(): Cursor

    fun getCursorBySearch(query: String): LiveData<List<WordEntity>>
}