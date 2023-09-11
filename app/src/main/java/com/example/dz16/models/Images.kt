package com.example.dz16.models

import java.io.Serializable


data class Images(
    var lg: String,
    var md: String,
    var sm: String,
    var xs: String
) : Serializable