package com.areeb.noteapp.ui.base.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.areeb.noteapp.data.network.local.DataBase.AppDataBase
import com.areeb.noteapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var appData: AppDataBase
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun startMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
        settingUpBottomNavigation()
    }

    private fun settingUpBottomNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment?
        val navController = navHost!!.navController
        binding.bottomNav.setupWithNavController(navController)
    }
}
