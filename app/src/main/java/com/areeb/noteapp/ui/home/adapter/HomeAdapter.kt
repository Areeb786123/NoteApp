package com.areeb.noteapp.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.ui.common.RecyclerItemClick
import com.areeb.noteapp.ui.home.viewHolder.HomeViewHolder

class HomeAdapter(
    private val item: List<NotesDto>,
    private val itemClick: RecyclerItemClick<NotesDto>,
) :
    RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(item[position], itemClick)
    }

    override fun getItemCount(): Int {
        return item.size
    }
}
