package com.sergiodev.android.timetonictest.data.model

data class Book (
    val ownerPrefs : OwnerPrefs,
)

data class OwnerPrefs (
    val oCoverImg: String,
    val title: String
)
