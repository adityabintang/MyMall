package com.bintang.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bintang.myapplication.R
import com.bintang.myapplication.databinding.ActivityMainBinding
import com.bintang.myapplication.ui.register.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MainActivity : AppCompatActivity() {
    private var viewModel: LoginViewModel? = null
    private var binding: ActivityMainBinding? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewBinding = binding?.root

        setContentView(viewBinding)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val googleSignIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignIn)

        observe()

        binding?.btnSignIn?.setOnClickListener {
            //validation
            if (binding?.emaiAddress?.text?.toString()?.isEmpty() == true) {
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

        binding?.btnSignInGoogle?.setOnClickListener {
            signIn()
        }
        binding?.singup?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        val intent = mGoogleSignInClient?.signInIntent
        startActivityForResult(intent, SIGN_IN)
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
            //session shared preferences
            Toast.makeText(applicationContext, "Berhasil Login", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSinginResult(task)
        }
    }

    private fun handleSinginResult(task: Task<GoogleSignInAccount>?) {
        try {
            val account: GoogleSignInAccount? = task?.getResult(ApiException::class.java)
            val acct = GoogleSignIn.getLastSignedInAccount(this)
            if (acct != null) {
                val personeName = acct.displayName
                val personEmail = acct.email
                val personelastName = acct.familyName
            }
            startActivity(Intent(this, RegisterActivity::class.java))
        } catch (e: ApiException) {
            Log.e("TAG", "singINResult: failed code = " + e.statusCode)
        }
    }
}