package com.areeb.noteapp.ui.home.bottomSheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.databinding.AddNoteBottomSheetBinding
import com.areeb.noteapp.ui.common.DialogCancel
import com.areeb.noteapp.ui.home.viewModel.homeViewModels.HomeViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteSheet @Inject constructor() : BottomSheetDialogFragment(), View.OnClickListener {
    companion object {
        private const val WIDTH = 0.99
        private const val HIGHT = 0.99
    }

    private lateinit var dialogListener: DialogCancel
    private var _binding: AddNoteBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModels by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = AddNoteBottomSheetBinding.inflate(layoutInflater)
        bottomSheetAppearance()
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        addData()
        onItemClick()
    }

    private fun addData() {
        binding.let {
            it.saveNoteBtn.setOnClickListener { click ->
                if (it.addNoteTitleTextView.text.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "title should not be empty",
                        Toast.LENGTH_SHORT,
                    )
                        .show()
                } else if (it.addNoteEditText.text.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "notes should not be empty",
                        Toast.LENGTH_SHORT,
                    )
                        .show()
                } else {
                    addNotes(
                        binding.addNoteTitleTextView.text.toString(),
                        binding.addNoteEditText.text.toString(),
                    )
                }
            }
        }
    }

    private fun addNotes(title: String, note: String) {
        val notes = NotesDto(
            title = title,
            notes = note,
        )
        try {
            viewModel.addNotes(notes)
            cancelDialog()
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
        }
    }

    fun setListener(listener: DialogCancel) {
        dialogListener = listener
    }

    private fun cancelDialog() {
        dialogListener.onCancel()
        binding.let {
            it.addNoteEditText.text.clear()
            it.addNoteTitleTextView.text.clear()
        }
        dialog?.cancel()
    }

    private fun bottomSheetAppearance() {
        dialog?.setCanceledOnTouchOutside(true)
        val width = (resources.displayMetrics.widthPixels * WIDTH).toInt()
        val height = (resources.displayMetrics.heightPixels * HIGHT).toInt()
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun onItemClick() {
        binding.cancelDialog.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.cancelDialog.id -> cancelDialog()
        }
    }
}
