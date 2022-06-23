package ru.stor.nerecipe.classes

import kotlinx.serialization.Serializable

@Serializable
data class Stage(
    val content:String,
    val photoLocation: String? =null
)