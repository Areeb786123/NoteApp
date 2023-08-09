package com.areeb.noteapp.ui.home.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.areeb.noteapp.databinding.AddNoteBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteSheet @Inject constructor() : BottomSheetDialogFragment() {
    companion object {
        private const val WIDTH = 0.99
        private const val HIGHT = 0.99
    }

    private var _binding: AddNoteBottomSheetBinding? = null
    private val binding get() = _binding!!
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

    private fun bottomSheetAppearance() {
        dialog?.setCanceledOnTouchOutside(true)
        val width = (resources.displayMetrics.widthPixels * WIDTH).toInt()
        val height = (resources.displayMetrics.heightPixels * HIGHT).toInt()
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}
