package com.areeb.noteapp.ui.base.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.areeb.noteapp.R
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.data.network.local.DataBase.AppDataBase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var appData: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        get()
    }

    private fun dataBaseTest() {
        val note = NotesDto(id = 0, title = "hai", notes = "my nam is areeb")
        lifecycleScope.launch {
            appData.noteDao().addNote(note)
        }
    }

    private fun get() {
        lifecycleScope.launch {
            Log.e("jj", appData.noteDao().getAllNotes().toString())
        }
    }
}
