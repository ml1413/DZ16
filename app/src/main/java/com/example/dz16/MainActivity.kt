package com.example.dz16

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dz16.databinding.ActivityMainBinding
import com.example.dz16.utils.progressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_bar)

    }


}
