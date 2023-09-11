package com.example.dz16.models

import java.io.Serializable


data class Appearance(
    var eyeColor: String,
    var gender: String,
    var hairColor: String,
    var height: List<String>,
    var race: String ,
    var weight: List<String>
) : Serializable