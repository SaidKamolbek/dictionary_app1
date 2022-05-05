package com.example.mydictionary.data.source.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(t: List<T>)


    @Delete
    fun delete(t: T)

    @Update
    fun update(t: T)

}