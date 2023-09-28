package com.example.dz16

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dz16.databinding.ActivityMainBinding
import com.example.dz16.fragmants.DetailsFragment
import com.example.dz16.fragmants.StartFragment
import com.example.dz16.utils.onOffProgressBar
import com.example.dz16.utils.progressBar

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressBar = binding.progressBar

    }


}
