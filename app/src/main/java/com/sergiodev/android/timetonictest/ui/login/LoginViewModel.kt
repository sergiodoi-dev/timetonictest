package com.sergiodev.android.timetonictest.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sergiodev.android.timetonictest.data.local.AppPreferences
import com.sergiodev.android.timetonictest.data.remote.RemoteConnection
import com.sergiodev.android.timetonictest.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData(LoginState())
    val state: LiveData<LoginState> = _state

    fun login(email: String, pwd: String) {

        _state.value = LoginState(loading = true)

        viewModelScope.launch {
            val appkey = RemoteConnection.service.createAppkey()
            val oauthkey = RemoteConnection.service.createOauthkey(appkey.appkey, email, pwd)

            if (oauthkey.status == "ok") {
                val sesskey = RemoteConnection.service.createSesskey(oauthkey.oauthkey!!, oauthkey.o_u!!, oauthkey.o_u)
                if (sesskey.status == "ok") {
                    AppPreferences.sesskey = sesskey.sesskey!!
                    AppPreferences.userid = oauthkey.o_u

                    _state.value = LoginState(success = sesskey.status, loading = false)
                } else {
                    _state.value = LoginState(error = sesskey.error, loading = false)
                }
            } else {
                _state.value = LoginState(error = oauthkey.error, loading = false)
            }
        }
    }

    data class LoginState(val loading : Boolean = false, val error:String? = null, val success:String? = null)

}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }
}