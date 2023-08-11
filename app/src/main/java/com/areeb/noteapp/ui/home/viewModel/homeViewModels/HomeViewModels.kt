package com.areeb.noteapp.ui.home.viewModel.homeViewModels

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
class HomeViewModels @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private var _notes = MutableLiveData<List<NotesDto>>()
    val notes: LiveData<List<NotesDto>> get() = _notes

    fun addNotes(notesDto: NotesDto) {
        viewModelScope.launch {
            homeRepository.addNote(notesDto)
        }
    }

    fun deleteNotes(notesDto: NotesDto) {
        viewModelScope.launch {
            homeRepository.deleteNote(notesDto)
        }
    }

    fun getAllNotes() {
        viewModelScope.launch {
            homeRepository.getAllNotes().collectLatest {
                _notes.value = it
            }
        }
    }
}
