package com.example.a11122025_mob_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.a11122025_mob_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        binding.btnJoke.setOnClickListener {
            viewModel.fetchJoke()
        }
    }

    private fun observeViewModel() = with(binding) {
        viewModel.jokeState.observe(this@MainActivity) { state ->
            idLoadingPB.visibility = if (state.isLoading) View.VISIBLE else View.GONE

            btnJoke.isEnabled = !state.isLoading
            if (state.jokeText != null) tvJoke.text = state.jokeText

            if (state.error != null) {
                Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_SHORT).show()
                tvJoke.text = "შეცდომა: ${state.error}"
            }
        }
    }
}