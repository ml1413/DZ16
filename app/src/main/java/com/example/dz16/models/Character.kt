package com.example.dz16.models

import java.io.Serializable


data class Character(
    var id: String,
    var name: String,
    var slug: String,
    //class__________________
    var appearance: Appearance,
    var biography: Biography,
    var connections: Connections,
    var images: Images,
    var powerstats: Powerstats,
    var work: Work
) :Serializable
