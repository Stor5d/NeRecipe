package ru.stor.nerecipe.dataSettings

interface SettingsRepository {

    //val data:LiveData<List<Recipe>>

    fun saveStateSwitch(key: String, b: Boolean)
    fun getStateSwitch(key: String):Boolean

}