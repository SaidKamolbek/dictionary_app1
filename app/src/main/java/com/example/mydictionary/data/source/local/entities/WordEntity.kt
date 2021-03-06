package com.example.mydictionary.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mydictionary.data.model.WordModel

@Entity(tableName = "words")
class WordEntity(

    @ColumnInfo(name = "_id")
    @PrimaryKey()
    val id: Int,

    @ColumnInfo(name = "en_word")
    val word: String,

    @ColumnInfo(name = "en_definition")
    val definition: String,

    @ColumnInfo(name = "example")
    val example: String,

    @ColumnInfo(name = "antonyms")
    val antonym: String,

    @ColumnInfo(name = "synonyms")
    val synonym: String

)

fun WordEntity.toWordModel(): WordModel = WordModel(id, word, definition, example, antonym, synonym)