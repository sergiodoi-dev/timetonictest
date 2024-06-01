package com.sergiodev.android.timetonictest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sergiodev.android.timetonictest.data.local.AppPreferences
import com.sergiodev.android.timetonictest.data.model.Book
import com.sergiodev.android.timetonictest.data.remote.RemoteConnection
import com.sergiodev.android.timetonictest.ui.login.LoginViewModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private val _state = MutableLiveData(MainState())
    val state: LiveData<MainState> = _state

    init {
        _state.value = MainState(loading = true)

        viewModelScope.launch {
            val books = RemoteConnection.service.getAllBooks(
                AppPreferences.sesskey,
                AppPreferences.userid,
                AppPreferences.userid
            )
            _state.value = MainState(loading = false, books = books.allBooks.books)
        }
    }

    data class MainState(
        val loading: Boolean = false,
        val books: List<Book>? = null,
    )
}



@Suppress("UNCHECKED_CAST")
class MainViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}