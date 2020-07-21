package ru.cocovella.prof_android_dev.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "word")
    var word: String, @field:ColumnInfo(name = "description")
    var description: String?
)
