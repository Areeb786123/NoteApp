package com.areeb.noteapp.ui.detail.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.databinding.FragmentDetailBinding
import com.areeb.noteapp.ui.base.activity.MainActivity
import com.areeb.noteapp.ui.detail.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class DetailFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModels: DetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        observer()
        setOnClickListener()
    }

    private fun observer() {
        viewModels.note.observe(viewLifecycleOwner) {
            addData(it)
        }
    }

    private fun addData(notesDto: NotesDto) {
        binding.let {
            val editableFactory = Editable.Factory.getInstance()

            val titleEditable = editableFactory.newEditable(notesDto.title)
            it.titleEditText.text = titleEditable

            val notesEditable = editableFactory.newEditable(notesDto.notes)
            it.notesDetailEditText.text = notesEditable
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.let {
            it.notesDetailEditText.text.clear()
            it.titleEditText.text.clear()
        }
    }

    private fun setOnClickListener() {
        binding.let {
            it.editBtn.setOnClickListener(this)
            it.updateNoteBtn.setOnClickListener(this)
            it.cancelBtn.setOnClickListener(this)
        }
    }

    private fun updateNote() {
        val updateNote = NotesDto(
            title = binding.titleEditText.text.toString(),
            notes = binding.notesDetailEditText.text.toString(),
        )
        try {
            if (updateNote.notes.isEmpty()) {
                Toast.makeText(requireContext(), "please add notes", Toast.LENGTH_SHORT).show()
            } else if (updateNote.title.isEmpty()) {
                Toast.makeText(requireContext(), "please add title", Toast.LENGTH_SHORT).show()
            } else {
                viewModels.updateNotes(updateNote)
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "some error occur", Toast.LENGTH_SHORT).show()
            Log.e("error", e.message.toString())
        }
    }

    override fun onClick(view: View?) {
        binding.let {
            when (view?.id) {
                it.editBtn.id -> {
                    it.llOptions.visibility = View.VISIBLE
                    it.editBtn.visibility = View.GONE
                    makeComponentClickable(true)
                }

                it.updateNoteBtn.id -> {
                    updateNote()
                    val handler = Handler(Looper.myLooper()!!)
                    handler.postDelayed({
                        MainActivity.startMainActivity(requireContext())
                    }, 500)
                }

                it.cancelBtn.id -> {
                    it.llOptions.visibility = View.GONE
                    it.editBtn.visibility = View.VISIBLE
                    makeComponentClickable(false)
                }

                else -> {
                    Log.e("error", "some error occur")
                }
            }
        }
    }

    private fun makeComponentClickable(isEnable: Boolean) {
        binding.let {
            it.titleEditText.apply {
                isClickable = isEnable
                isFocusable = isEnable
                isFocusableInTouchMode = isEnable
            }.also { et ->
                if (isEnable) {
                    et.requestFocus()
                } else {
                    et.clearFocus()
                }
            }
            it.notesDetailEditText.apply {
                isClickable = isEnable
                isFocusable = isEnable
                isFocusableInTouchMode = isEnable
            }
        }
    }
}
