package com.example.a11122025_mob_2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _jokeState = MutableLiveData(JokeState(isLoading = false))
    val jokeState: LiveData<JokeState> = _jokeState

    fun fetchJoke() {
        _jokeState.value = JokeState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getJokes()
                if (response.isSuccessful) {
                    val joke = response.body()
                    _jokeState.value = JokeState(
                        jokeText = joke?.value,
                        isLoading = false
                    )
                } else {
                    _jokeState.value = JokeState(
                        isLoading = false,
                        error = "HTTP შეცდომა: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _jokeState.value = JokeState(
                    isLoading = false,
                    error = e.message ?: "უცნობი შეცდომა"
                )

            }
        }
    }
}