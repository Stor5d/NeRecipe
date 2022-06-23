package ru.stor.nerecipe.adapter

import ru.stor.nerecipe.classes.Stage

interface StageInteractionListener {

    fun onRemoveClicked(stage: Stage)
    fun onUpClicked(stage: Stage)
    fun onDownClicked(stage: Stage)
}