package com.example.dz16.models

import java.io.Serializable


data class Biography(
    var aliases: List<String>,
    var alignment: String,
    var alterEgos: String,
    var firstAppearance: String,
    var fullName: String,
    var placeOfBirth: String,
    var publisher: String
): Serializable