package ru.stor.nerecipe.dataSettings

interface SettingsRepository {

    fun saveStateSwitch(key: String, b: Boolean)
    fun getStateSwitch(key: String):Boolean

}