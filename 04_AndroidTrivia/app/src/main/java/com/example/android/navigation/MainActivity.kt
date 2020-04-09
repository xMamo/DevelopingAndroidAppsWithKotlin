package com.example.android.navigation

import android.os.*
import androidx.appcompat.app.*
import androidx.databinding.*
import androidx.drawerlayout.widget.*
import androidx.navigation.*
import androidx.navigation.ui.*
import com.example.android.navigation.databinding.*

class MainActivity : AppCompatActivity() {
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
		val navController = findNavController(R.id.myNavHostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
		NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id == controller.graph.startDestination) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
	}

	override fun onSupportNavigateUp(): Boolean {
		return NavigationUI.navigateUp(findNavController(R.id.myNavHostFragment), appBarConfiguration)
	}
}