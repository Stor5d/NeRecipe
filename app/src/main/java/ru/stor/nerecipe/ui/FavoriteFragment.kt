package ru.stor.nerecipe.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.stor.nerecipe.R

class FavoriteFragment:Fragment(R.layout.favorite_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e("AAA", "this Favorite")
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}