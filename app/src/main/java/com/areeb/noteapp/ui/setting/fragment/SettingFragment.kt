package com.areeb.noteapp.ui.setting.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.noteapp.databinding.FragmentSettingBinding
import com.areeb.noteapp.ui.base.fragment.BaseFragment
import com.areeb.noteapp.ui.setting.viewModel.SettingViewModel
import com.areeb.noteapp.utils.sharedPrefernces.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModels: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        observer()
        onSwitchClick()
        if (SharedPreferences.getDarkModeValue(requireContext())) {
            binding.darkModeTitle.text = "Enable Light Mode"
        } else {
            binding.darkModeTitle.text = "Enable Dark Mode"
        }

        binding.darkModeSwitch.isChecked = SharedPreferences.getDarkModeValue(requireContext())
    }

    private fun observer() {
        viewModels.isDarkModeEnable.observe(viewLifecycleOwner) {
            enableOrDisableDarkMode(it)
            settingUpFragmentData(it)
            SharedPreferences.setDarkModeValue(requireContext(), it)
        }
    }

    private fun onSwitchClick() {
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch is ON
                // You can perform actions related to turning dark mode ON here
                viewModels.setDarkMode(true)
                SharedPreferences.setDarkModeValue(requireContext(), true)
            } else {
                // Switch is OFF
                // You can perform actions related to turning dark mode OFF here
                viewModels.setDarkMode(false)
                SharedPreferences.setDarkModeValue(requireContext(), false)
            }
        }
    }

    private fun settingUpFragmentData(isEnable: Boolean) {
        if (isEnable) {
            binding.darkModeTitle.text = "Enable Light Mode"
        } else {
            binding.darkModeTitle.text = "Enable Dark Mode"
        }
    }
}
