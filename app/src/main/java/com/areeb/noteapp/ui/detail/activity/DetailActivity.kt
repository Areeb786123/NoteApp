package com.areeb.noteapp.ui.detail.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.areeb.noteapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
    }
}
