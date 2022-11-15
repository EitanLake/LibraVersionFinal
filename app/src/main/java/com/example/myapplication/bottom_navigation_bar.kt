package com.example.myapplication

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.databinding.ActivityBottomNavigationBarBinding


enum class  ProviderType {
    BASIC,
    GOOGLE
}

class bottom_navigation_bar : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(home())

        binding.bottomnavigationview.setOnItemReselectedListener {

            when (it.itemId) {
                R.id.maps -> replaceFragment(maps())
                R.id.home -> replaceFragment(home())
                else -> {
                }
            }

            true
        }


    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentlayout,fragment)
        fragmentTransaction.commit()

    }
}