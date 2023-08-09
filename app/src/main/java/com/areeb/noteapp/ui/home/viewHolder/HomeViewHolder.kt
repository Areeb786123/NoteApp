package com.areeb.noteapp.ui.home.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.databinding.ItemsNotesBinding
import com.areeb.noteapp.ui.common.RecyclerItemClick

class HomeViewHolder(private val binding: ItemsNotesBinding) :
    ViewHolder(binding.root),
    View.OnClickListener {
    init {
        binding.deleteNoteBtn.setOnClickListener(this)
    }

    private lateinit var noteDto: NotesDto
    private lateinit var listener: RecyclerItemClick<NotesDto>
    fun bind(item: NotesDto, itemClick: RecyclerItemClick<NotesDto>) {
        this.listener = itemClick
        this.noteDto = item
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

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.deleteNoteBtn.id -> listener.onClick(noteDto)
        }
    }
}
