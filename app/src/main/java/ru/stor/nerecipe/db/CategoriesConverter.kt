package ru.stor.nerecipe.db

import androidx.room.TypeConverter

class CategoriesConverter {

    @TypeConverter
    fun fromCategories(categories: List<Int?>): String {
        return categories.joinToString("/")
    }

    @TypeConverter
    fun toCategories(data: String): List<Int> {
        return data.split("/").map { it.toInt() }
    }

}

