package com.sergiodev.android.timetonictest.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.sergiodev.android.timetonictest.R
import com.sergiodev.android.timetonictest.data.RemoteConnection
import com.sergiodev.android.timetonictest.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
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


        binding.loginBtn.setOnClickListener {
            lifecycleScope.launch {
                val appkey = RemoteConnection.service.createAppkey()
                val oauthkey = RemoteConnection.service.createOauthkey(appkey.appkey, email, pwd)

                if (oauthkey.status == "ok") {
                    val sesskey = RemoteConnection.service.createSesskey(oauthkey.oauthkey!!, oauthkey.o_u!!, oauthkey.o_u)
                    Toast.makeText(applicationContext, "Status: ${sesskey.status}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Error: ${oauthkey.error}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}