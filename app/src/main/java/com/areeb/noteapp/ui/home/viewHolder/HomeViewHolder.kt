package com.areeb.noteapp.ui.home.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.databinding.ItemsNotesBinding

class HomeViewHolder(private val binding: ItemsNotesBinding) : ViewHolder(binding.root) {

    fun bind(item: NotesDto) {
        settingData(item)
    }

    private fun settingData(itemNotesDto: NotesDto) {
        binding.let {
            it.titleTextView.text = itemNotesDto.title
            it.notesTextView.text = itemNotesDto.notes
        }
    }

    companion object {
        fun from(viewGroup: ViewGroup): HomeViewHolder {
            return HomeViewHolder(
                ItemsNotesBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false,
                ),
            )
        }
    }
}
