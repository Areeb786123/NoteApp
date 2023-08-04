package com.areeb.noteapp.data.network.local.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.data.network.local.Dao.NoteDao

@Database(entities = [NotesDto::class], version = AppDataBase.VERSION_ONE)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val VERSION_ONE = 1

        /*
        * Volatile annotation  is used so that every component in app  get notify that INSTANCE value is changed */
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            if (INSTANCE == null) {
                /*
                * this synchronized block is used to create only instance
                * */
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "noteDB",
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun noteDao(): NoteDao
}
