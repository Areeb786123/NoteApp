package com.areeb.noteapp.ui.detail.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.areeb.noteapp.databinding.ActivityDetailBinding
import com.areeb.noteapp.ui.detail.viewModel.DetailViewModel
import com.areeb.noteapp.utils.Constants.Companion.NOTE_ID
import com.areeb.noteapp.utils.Constants.Companion._NOTE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModels: DetailViewModel by viewModels()

    companion object {
        fun startDetailActivity(id: Long, context: Context) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(NOTE_ID, id)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
        viewModels.setNoteID(getData())
    }

    private fun getData(): Long {
        return intent.getLongExtra(_NOTE_ID, 0L)
    }
}
