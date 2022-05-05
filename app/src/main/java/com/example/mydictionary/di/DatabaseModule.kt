package com.example.mydictionary.di

import android.content.Context
import com.example.mydictionary.data.source.local.DictionaryDatabase
import com.example.mydictionary.data.source.local.dao.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): DictionaryDatabase =
        DictionaryDatabase.init(context)

    @Provides
    fun provideNewsDao(newsDatabase: DictionaryDatabase): WordDao = newsDatabase.getWordDao()


}