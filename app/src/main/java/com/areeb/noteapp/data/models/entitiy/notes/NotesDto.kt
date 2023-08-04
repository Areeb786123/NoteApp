package com.areeb.noteapp.data.models.entitiy.notes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesEntity")
data class NotesDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val notes: String,
)
