package ru.stor.nerecipe.adapter

import ru.stor.nerecipe.classes.Stage

interface StageInteractionListener {

    fun onRemoveStageClicked(stage: Stage)
    fun onSaveStageClicked(content: String, uriPhoto: String?)
    fun onMoveStageUpClicked(position: Int)
    fun onMoveStageDownClicked(position: Int)
}