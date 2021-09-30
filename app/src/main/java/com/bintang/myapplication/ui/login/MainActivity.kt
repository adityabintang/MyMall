package com.bintang.myapplication.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bintang.myapplication.R
import com.bintang.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewModel: LoginViewModel? = null
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewBinding = binding?.root

        setContentView(viewBinding)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        observe()

        binding?.btnSignIn?.setOnClickListener {
            //validation
            if (binding?.emaiAddress?.text?.toString()?.isEmpty() == true){
                binding?.emaiAddress?.requestFocus()
                binding?.emaiAddress?.error = "Email Address wajib diisi"
            }
            binding?.emaiAddress?.text?.toString()?.let { it1 ->
                binding?.password?.text?.toString()?.let { it2 ->
                    viewModel?.loginUser(
                        it1,
                        it2
                    )
                }
            }
        }
    }

    private fun observe() {
        viewModel?.isSuccess()?.observe(this, Observer { responseSuccess(it) })
        viewModel?.isError()?.observe(this, Observer { resError(it) })
    }

    private fun resError(it: Throwable?) {
        Toast.makeText(applicationContext, "Akun tidak terdaftar", Toast.LENGTH_SHORT).show()
    }

    private fun responseSuccess(it: ResLogin?) {
        if (it?.isSuccess == true) {
            Toast.makeText(applicationContext, "Akun tidak terdaftar", Toast.LENGTH_SHORT).show()
        }
    }
}