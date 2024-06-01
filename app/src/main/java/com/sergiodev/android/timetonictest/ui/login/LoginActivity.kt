package com.sergiodev.android.timetonictest.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.sergiodev.android.timetonictest.R
import com.sergiodev.android.timetonictest.data.local.AppPreferences
import com.sergiodev.android.timetonictest.data.remote.RemoteConnection
import com.sergiodev.android.timetonictest.databinding.ActivityLoginBinding
import com.sergiodev.android.timetonictest.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels{LoginViewModelFactory()}
    private var email : String = "android.developer@timetonic.com"
    private var pwd : String = "Android.developer1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.email = email
        binding.pwd = pwd

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.state.observe(this){
            binding.progress.isVisible = it.loading
            it.error?.let {
                Toast.makeText(applicationContext, "Error: $it", Toast.LENGTH_SHORT).show()
            }
            it.success?.let {
                if(it == "ok") {
                    Intent(this@LoginActivity, MainActivity::class.java).let {
                        startActivity(it)
                        finish()
                    }
                }
            }
        }

        binding.loginBtn.setOnClickListener {
            viewModel.login(email, pwd)
        }
    }
}