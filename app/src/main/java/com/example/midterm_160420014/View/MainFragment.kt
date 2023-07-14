package com.example.midterm_160420014.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.midterm_160420014.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainFragment : Fragment() {
    private lateinit var navControler:NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControler = (childFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        view?.findViewById<BottomNavigationView>(R.id.bottomNav)?.setupWithNavController(navControler)

        drawerLayout = view.findViewById(R.id.drawerLayout)
        val appBarConfig = AppBarConfiguration(
            setOf(R.id.itemHome,R.id.itemProfile,R.id.itemHistory),
            drawerLayout
        )
        NavigationUI.setupActionBarWithNavController((requireActivity() as AppCompatActivity), navControler, appBarConfig)
        val navView = view.findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navControler)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_main, container, false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return rootView
    }
}