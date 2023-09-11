package com.example.dz16.models

import java.io.Serializable


data class Powerstats(
    var combat: String,
    var durability: String,
    var intelligence: String,
    var power: String,
    var speed: String,
    var strength: String
) : Serializable