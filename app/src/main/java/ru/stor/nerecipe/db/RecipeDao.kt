package ru.stor.nerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
   // @Query("SELECT * FROM recipes ORDER BY id DESC")
   // fun getAll(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getAll(): LiveData<List<RecipeWithStages>>

    @Insert
    fun insertRecipe(recipe: RecipeEntity):Long

    @Insert
    fun insertStage(stage: StageEntity)

    @Query("UPDATE recipes SET title = :title WHERE id = :id")
    fun updateContentById(id: Long, title: String)

    @Query("""
        UPDATE recipes SET
        likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
        WHERE id = :id
        """)
    fun likeById(id: Long)

//    @Query("""
//        UPDATE recipes SET
//        shareCount = shareCount + 1
//        WHERE id = :id
//        """)
//    fun share(id: Long)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun removeById(id: Long)
}