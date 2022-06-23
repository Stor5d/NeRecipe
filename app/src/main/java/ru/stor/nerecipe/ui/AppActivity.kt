package ru.stor.nerecipe.ui

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.stor.nerecipe.R
import android.view.MenuItem.OnActionExpandListener as OnActionExpandListener1
import android.view.MenuItem.OnActionExpandListener as MenuItemOnActionExpandListener

class AppActivity : AppCompatActivity(R.layout.app_activity) {
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.action_bar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("AAA", item.itemId.toString())
        return super.onOptionsItemSelected(item)

    }
}