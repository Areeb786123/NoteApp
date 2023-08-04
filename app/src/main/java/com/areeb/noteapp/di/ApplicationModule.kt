package com.areeb.noteapp.di

import android.content.Context
import com.areeb.noteapp.data.network.local.Dao.NoteDao
import com.areeb.noteapp.data.network.local.DataBase.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providesDataBase(
        @ApplicationContext context: Context,
    ): AppDataBase {
        return AppDataBase.getDatabase(context)
    }

    @Provides
    fun providesNoteDao(appDataBase: AppDataBase): NoteDao {
        return appDataBase.noteDao()
    }
}
