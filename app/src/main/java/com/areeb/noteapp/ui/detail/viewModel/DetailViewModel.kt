package com.areeb.noteapp.ui.detail.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {
    private var _noteId = MutableLiveData<Long>()
    val noteId: LiveData<Long> get() = _noteId

    private var _note = MutableLiveData<NotesDto>()
    val note: LiveData<NotesDto> get() = _note

    fun updateNotes(notes: NotesDto) {
        viewModelScope.launch {
            homeRepository.updateNote(notes)
        }
    }

    fun setNoteID(id: Long) {
//        _noteId.value = id
        Log.e("ll", id.toString())
        findNoteById(id)
    }

    fun findNoteById(noteId: Long?) {
        viewModelScope.launch {
            homeRepository.getNoteById(noteId ?: 0L).collectLatest {
                Log.e("ll", it.toString())
                _note.value = it
            }
        }
    }
}
