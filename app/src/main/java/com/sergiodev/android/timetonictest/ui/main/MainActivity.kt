package com.sergiodev.android.timetonictest.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.sergiodev.android.timetonictest.R
import com.sergiodev.android.timetonictest.data.local.AppPreferences
import com.sergiodev.android.timetonictest.data.remote.RemoteConnection
import com.sergiodev.android.timetonictest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            val books = RemoteConnection.service.getAllBooks(AppPreferences.sesskey, AppPreferences.userid, AppPreferences.userid)
            val adapter = BookAdapter(books.allBooks.books)
            binding.bookList.adapter = adapter
        }
    }

}