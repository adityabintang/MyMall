package com.bintang.myapplication.ui.profile


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bintang.myapplication.R
import com.bintang.myapplication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        val viewBinding = binding.root
        setContentView(viewBinding)
    }
}