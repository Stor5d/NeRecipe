package ru.stor.nerecipe.classes

internal enum class Categories(
    val key: String,
    val id: Int
) {
    European("European",0),
    Asian("Asian",1),
    PanAsian("Pan_Asian",2),
    Eastern("Eastern",3),
    American("American",4),
    Russian("Russian",5),
    Mediterranean("Mediterranean",6);
}