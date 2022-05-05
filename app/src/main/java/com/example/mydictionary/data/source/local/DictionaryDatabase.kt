package com.example.mydictionary.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydictionary.data.source.local.dao.WordDao
import com.example.mydictionary.data.source.local.entities.WordEntity

@Database(entities = [WordEntity::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao

    companion object {
        private var INSTANCE: DictionaryDatabase? = null

        fun init(context: Context): DictionaryDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            val instance = Room.databaseBuilder(
                context,
                DictionaryDatabase::class.java,
                "dictionary.db")
                .createFromAsset("eng_dictionary.db")
                .allowMainThreadQueries()
                .build()
            INSTANCE = instance
            return instance
        }

        fun getInstance() = INSTANCE!!

    }
}