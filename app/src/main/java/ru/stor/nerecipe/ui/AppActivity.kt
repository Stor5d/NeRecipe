package ru.stor.nerecipe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.stor.nerecipe.R
import ru.stor.nerecipe.databinding.AppActivityBinding


class AppActivity : AppCompatActivity() {

    lateinit var binding: AppActivityBinding
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
                    true
                }
                R.id.filter -> {
                    replaceFragment(filterFragmentSwitch)
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