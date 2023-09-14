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
    private val startFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.activity_container) as StartFragment
    }

    private val detailsFragment = DetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d("TAG1", "onCreate: Activity")
        progressBar = binding.progressBar
        startFragment.setOnItemClickListener {
            detailsFragment.setItem(item = it)
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_container, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }


}
