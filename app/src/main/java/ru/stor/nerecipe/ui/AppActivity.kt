package ru.stor.nerecipe.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.stor.nerecipe.R
import ru.stor.nerecipe.databinding.AppActivityBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel


class AppActivity : AppCompatActivity() {

    lateinit var binding: AppActivityBinding
    private val viewModel by viewModels<RecipeViewModel>()
    private val feedFragment = FeedFragment()
    private val filterFragmentSwitch = FilterFragmentSwitch()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bNav = binding.bNav
        replaceFragment(feedFragment)

        bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(feedFragment)
                    viewModel.favoriteFilter.value = false
                    true
                }
                R.id.filter -> {
                    replaceFragment(filterFragmentSwitch)
                    viewModel.favoriteFilter.value = false
                    true
                }
                R.id.favorites -> {
                    replaceFragment(feedFragment)
                    viewModel.favoriteFilter.value = true
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }

    //    override fun onBackPressed() {
//
//        val fm = supportFragmentManager
//        var backPressedListener: OnBackPressedListener? = null
//        for (fragment in fm.fragments) {
//
//
//            if (fragment is OnBackPressedListener) {
//                backPressedListener = fragment
//                break
//            }
//        }
//        if (backPressedListener != null) {
//            backPressedListener.onBackPressed()
//        } else {
//            super.onBackPressed()
//        }
//    }
}