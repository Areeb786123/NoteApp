package com.areeb.noteapp.data.network.local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(noteDao: NotesDto)

    @Delete
    suspend fun deleteNote(noteDao: NotesDto)

    @Query("SELECT * FROM notesEntity")
    suspend fun getAllNotes(): List<NotesDto>
}
