package com.areeb.noteapp.data.repository

import android.util.Log
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.data.network.local.DataBase.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class HomeRepository @Inject constructor(private val appDataBase: AppDataBase) {
    suspend fun addNote(note: NotesDto) {
        try {
            appDataBase.noteDao().addNote(note)
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
        }
    }

    suspend fun getAllNotes(): Flow<List<NotesDto>> {
        return flow {
            val notes = appDataBase.noteDao().getAllNotes()
            emit(notes)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteNote(note: NotesDto) {
        try {
            appDataBase.noteDao().deleteNote(note)
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
        }
    }

    suspend fun updateNote(note: NotesDto) {
        try {
            Log.e("chh", note.toString())
            appDataBase.noteDao().updateNote(note)
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
        }
    }

    suspend fun getNoteById(noteId: Long): Flow<NotesDto?> {
        return flow {
            try {
                val response = appDataBase.noteDao().getNoteById(noteId)
                emit(response)
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
