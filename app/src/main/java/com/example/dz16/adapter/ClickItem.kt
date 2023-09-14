package com.example.dz16.adapter

import android.util.Log
import com.example.dz16.models.Character

interface ClickItem {
    fun getModel(item: Character)
}