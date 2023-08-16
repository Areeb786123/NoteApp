package com.areeb.noteapp.data.network.local.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.data.network.local.Dao.NoteDao

@Database(entities = [NotesDto::class], version = AppDataBase.VERSION_ONE)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val VERSION_ONE = 1
    }

    abstract fun noteDao(): NoteDao
}
