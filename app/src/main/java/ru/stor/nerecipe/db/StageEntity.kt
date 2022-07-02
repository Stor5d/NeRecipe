package ru.stor.nerecipe.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.stor.nerecipe.classes.Stage

@Entity(
    tableName = "stages",
   // primaryKeys = ["recipe_id", "id"],
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = ["id"],
        childColumns = ["recipe_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class StageEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "recipe_id")
    val recipeId: Long,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "uri_photo")
    val uriPhoto: String?

    ) {
    internal fun Stage.toModel() = Stage(
        id = id,
        recipeId = recipeId,
        content = content,
        uriPhoto = uriPhoto,
    )
}