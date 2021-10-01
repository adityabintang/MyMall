package com.bintang.myapplication.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bintang.myapplication.R

import androidx.lifecycle.ViewModelProviders

import com.bintang.myapplication.databinding.ActivityRegisterBinding
import com.bintang.myapplication.ui.login.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class RegisterActivity : AppCompatActivity() {
    private var viewModel: RegisterViewModel? = null
    private var binding: ActivityRegisterBinding? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val viewBinding = binding?.root
        setContentView(viewBinding)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        val gs0 = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gs0)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            binding?.firstanme?.setText(acct.displayName)
            binding?.lastName?.setText(acct.familyName)
            binding?.emailRegister?.setText(acct.email)
        }

        bserverRegsiter()
        binding?.btnRegister?.setOnClickListener {
            validation()
        }
    }

    private fun validation() {
        viewModel?.registerUser(
            binding?.emailRegister?.text.toString(),
            binding?.firstanme?.text.toString(),
            binding?.lastName?.text.toString(),
            binding?.passwordRegister4?.text.toString()
        )
    }

    private fun bserverRegsiter() {
        viewModel?.success?.observe(this, Observer { resSuccess(it) })
        viewModel?.error?.observe(this, Observer { resError(it) })
    }

    private fun resError(it: Throwable?) {
        Toast.makeText(applicationContext, "TIdak Berhasil", Toast.LENGTH_SHORT).show()
    }

    private fun resSuccess(it: ResRegister?) {
        if (it?.isSuccess == true) {
            Toast.makeText(applicationContext, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}