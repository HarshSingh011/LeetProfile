package com.example.leetprofile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.leetprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.navigationDrawer, navController)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.acc -> {
                    navController.navigate(R.id.ent_user)
                    selectDrawerItem(R.id.acc)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.homeFragment)
                    selectDrawerItem(R.id.profile)
                    true
                }
                R.id.home -> {
                    navController.navigate(R.id.badgeFragment)
                    selectDrawerItem(R.id.home)
                    true
                }
                R.id.ques -> {
                    navController.navigate(R.id.previousQuestions)
                    selectDrawerItem(R.id.ques)
                    true
                }
                R.id.sub -> {
                    navController.navigate(R.id.submissionFragment)
                    selectDrawerItem(R.id.sub)
                    true
                }
                else -> false
            }
        }

        binding.navigationDrawer.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.acc -> {
                    navController.navigate(R.id.ent_user)
                    selectBottomItem(R.id.acc)
                }
                R.id.profile -> {
                    navController.navigate(R.id.homeFragment)
                    selectBottomItem(R.id.profile)
                }
                R.id.home -> {
                    navController.navigate(R.id.badgeFragment)
                    selectBottomItem(R.id.home)
                }
                R.id.ques -> {
                    navController.navigate(R.id.previousQuestions)
                    selectBottomItem(R.id.ques)
                }
                R.id.sub -> {
                    navController.navigate(R.id.submissionFragment)
                    selectBottomItem(R.id.sub)
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        navController.navigate(R.id.ent_user)
    }

    private fun selectDrawerItem(itemId: Int) {
        binding.navigationDrawer.menu.findItem(itemId).isChecked = true
    }

    private fun selectBottomItem(itemId: Int) {
        binding.bottomNavigation.selectedItemId = itemId
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_graph)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_graph)
        if (navController.currentDestination?.id != R.id.ent_user) {
            navController.navigate(R.id.ent_user)
        } else {
            super.onBackPressed()
        }
    }
}
