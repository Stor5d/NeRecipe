package ru.stor.nerecipe.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.stor.nerecipe.R
import ru.stor.nerecipe.databinding.BottomLayoutBinding


class BottomFragment : Fragment(R.layout.bottom_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val binding = BottomLayoutBinding.inflate(layoutInflater, container, false)
        //val view = binding.root
        //val imageView: ImageView = view.findViewById<View>(R.id.my_image) as ImageView
        //   val binding = BottomLayoutBinding.inflate(layoutInflater, container, false)
        //  val bNav = binding.bNav

        val bNav = view.findViewById<BottomNavigationView>(R.id.bNav)


        val navController =
            ((childFragmentManager.findFragmentById(R.id.mainContainerView)) as NavHostFragment).navController
        setupWithNavController(bNav, navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.recipeCreateFragment2
                || destination.id == R.id.filterSetupFragment
                || destination.id == R.id.stageCreateFragment
            ) {
                bNav.visibility = View.GONE
            } else {
                bNav.visibility = View.VISIBLE
            }
        }


//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.feedFragment3,
//                R.id.filterFragmentSwitch,
//                R.id.favoriteFragment
//            ))
//      setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        val navHostFragment = findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.feedFragment3,
//                R.id.filterFragmentSwitch,
//                R.id.favoriteFragment
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//
//        //  val navController = findNavController(R.id.nav_host_fragment)
//        bNav.setupWithNavController(navController)


//        val binding = BottomLayoutBinding.inflate(layoutInflater, container, false)
//        val bNav = binding.bNav
//        Log.e("AAA bnav", findNavController().graph.label.toString())
//
//       // val navController = (childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
//       // NavigationUI.setupWithNavController(bNav,navController)
//

//        bNav.setOnItemSelectedListener {
//            Log.e("AAA", "bNav")
//            when (it.itemId) {
//                R.id.feedFragment3 -> {
//                    //Log.e("AAA", "home")
//                    // viewModel.favoriteFilter.value = false
//                    true
//                }
//                R.id.filterFragmentSwitch -> {
//                   // Log.e("AAA", "filter")
//                    //   findNavController().navigate(FeedFragmentDirections.feedToSwitch())
//                    //   viewModel.favoriteFilter.value = false
//                    true
//                }
//                R.id.favoriteFragment -> {
//                  //  Log.e("AAA", "favorites")
//                    //    viewModel.favoriteFilter.value = true
//                    true
//                }
//                else -> {
//                    true
//                }
//            }
//        }
//        return view
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}

