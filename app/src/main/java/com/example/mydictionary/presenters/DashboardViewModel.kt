package com.example.mydictionary.presenters

import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.mydictionary.data.source.local.entities.WordEntity

interface DashboardViewModel {

    val wordLiveData: LiveData<List<WordEntity>>
//    val cursorWordsLiveData: LiveData<WordEntity>
//
//    fun loadWords()

    fun search(query: String): LiveData<List<WordEntity>>

}