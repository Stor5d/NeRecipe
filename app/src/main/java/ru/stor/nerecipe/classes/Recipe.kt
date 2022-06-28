package ru.stor.nerecipe.classes

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id:Long,
    val title: String,
    val author: String,
    val categories: List<Int>,
    val firstStage: Stage,
    val liked: Boolean = false
) {

    //private var stages = listOf(firstStage)

}