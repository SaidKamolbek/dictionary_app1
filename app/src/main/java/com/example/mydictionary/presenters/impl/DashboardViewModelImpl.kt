package com.example.mydictionary.presenters.impl

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydictionary.data.source.local.entities.WordEntity
import com.example.mydictionary.domain.DashboardRepository
import com.example.mydictionary.presenters.DashboardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModelImpl @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel(), DashboardViewModel {
    override val wordLiveData: LiveData<List<WordEntity>> = repository.getAllWords()

    override val cursorWordsLiveData: MutableLiveData<Cursor> = MutableLiveData()


    override fun loadWords() {
        cursorWordsLiveData.value = repository.getWordsCursor()
    }

    override fun search(query: String) {
        if (query.trim().isNotEmpty()) {
            cursorWordsLiveData.value = repository.getCursorBySearch("%$query%")
        }
    }
}