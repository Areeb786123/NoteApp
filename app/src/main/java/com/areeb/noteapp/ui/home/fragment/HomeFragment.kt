package com.areeb.noteapp.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.noteapp.data.models.entitiy.notes.NotesDto
import com.areeb.noteapp.databinding.FragmentHomeBinding
import com.areeb.noteapp.ui.base.fragment.BaseFragment
import com.areeb.noteapp.ui.common.DialogCancel
import com.areeb.noteapp.ui.common.RecyclerItemClick
import com.areeb.noteapp.ui.detail.activity.DetailActivity
import com.areeb.noteapp.ui.home.adapter.HomeAdapter
import com.areeb.noteapp.ui.home.bottomSheet.AddNoteSheet
import com.areeb.noteapp.ui.home.viewModel.homeViewModels.HomeViewModels
import com.areeb.noteapp.utils.sharedPrefernces.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(), View.OnClickListener, DialogCancel {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModels: HomeViewModels by viewModels()
    private lateinit var homeAdapter: HomeAdapter

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
        observer()
        enableOrDisableDarkMode(SharedPreferences.getDarkModeValue(requireContext()))
    }

    private fun init() {
        viewModels.getAllNotes()
        binding.addNote.setOnClickListener(this)
    }

    private fun observer() {
        viewModels.notes.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.emptyValueAnimation.visibility = View.GONE
            } else {
                binding.emptyValueAnimation.visibility = View.VISIBLE
            }
            settingUpRecyclerView(it, onItemClick)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.addNote.id -> {
                addNoteSheet.setListener(this)
                activity?.supportFragmentManager?.let { addNoteSheet.show(it, TAG) }
            }
        }
    }

    private fun settingUpRecyclerView(
        notes: List<NotesDto>,
        onItemClick: RecyclerItemClick<NotesDto>,
    ) {
        binding.rvNotes.adapter = HomeAdapter(notes, onItemClick).also {
            it.notifyDataSetChanged()
        }
    }

    override fun onCancel() {
        Log.e("aa", "i am called")
        refresh()
    }

    private fun refresh() {
        binding.rvNotes.adapter?.notifyDataSetChanged()
        viewModels.getAllNotes()
        observer()
    }

    val onItemClick = object : RecyclerItemClick<NotesDto> {
        override fun onClick(t: NotesDto) {
            try {
                DetailActivity.startDetailActivity(t.id, requireContext())
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }

        override fun onDelete(t: NotesDto) {
            try {
                viewModels.deleteNotes(notesDto = t)
                refresh()
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }
    }
}
