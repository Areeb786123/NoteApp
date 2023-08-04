package com.areeb.noteapp.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.noteapp.databinding.FragmentHomeBinding
import com.areeb.noteapp.ui.base.fragment.BaseFragment
import com.areeb.noteapp.ui.home.bottomSheet.AddNoteSheet
import com.areeb.noteapp.ui.home.viewModel.homeViewModels.HomeViewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModels: HomeViewModels by viewModels()

    @Inject
    lateinit var addNoteSheet: AddNoteSheet

    companion object {
        private const val TAG = "addNoteDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.addNote.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.addNote.id -> {
                activity?.supportFragmentManager?.let { addNoteSheet.show(it, TAG) }
            }
        }
    }
}
