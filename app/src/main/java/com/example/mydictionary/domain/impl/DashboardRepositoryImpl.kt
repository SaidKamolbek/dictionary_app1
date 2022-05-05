package com.example.mydictionary.domain.impl

import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.mydictionary.data.source.local.dao.WordDao
import com.example.mydictionary.data.source.local.entities.WordEntity
import com.example.mydictionary.domain.DashboardRepository
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val wordDao: WordDao
) : DashboardRepository {


    override fun getAllWords(): LiveData<List<WordEntity>> = wordDao.getAllWords()

    override fun getWordsCursor(): Cursor = wordDao.getAllWordsCursor()

    override fun getCursorBySearch(query: String): LiveData<List<WordEntity>> = wordDao.getCursorByQuery(query)


}