package ru.stor.nerecipe.classes

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id:Long,
    val title: String,
    val author: String,
    val categoryId: Int,
    val firstStage: Stage,
    val elected: Boolean = false
) {

    //private var stages = listOf(firstStage)

}