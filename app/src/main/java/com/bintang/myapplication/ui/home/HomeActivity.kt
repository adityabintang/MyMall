package com.bintang.myapplication.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bintang.myapplication.R
import com.bintang.myapplication.databinding.ActivityHomeBinding
import com.bintang.myapplication.databinding.ActivityMainBinding
import com.mobile.ecommercemymall.View.Profile.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val viewBinding = binding?.root
        setContentView(viewBinding)
        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()


        makeCurrentFragment(homeFragment)

        binding?.bottomNav?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> makeCurrentFragment(homeFragment)
                R.id.menu_profile -> makeCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}