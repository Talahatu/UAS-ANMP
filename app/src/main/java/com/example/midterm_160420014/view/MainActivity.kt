package com.example.midterm_160420014.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.midterm_160420014.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.hostFragment)
        val drawerLayout = this.findViewById<DrawerLayout>(R.id.drawerLayout)
        when(navController.currentDestination?.label.toString()){
            "Home"->{
                if(drawerLayout.isOpen)drawerLayout.close()
                else drawerLayout.open()
                return super.onSupportNavigateUp()
            }
            "History"->{
                if(drawerLayout.isOpen)drawerLayout.close()
                else drawerLayout.open()
                return super.onSupportNavigateUp()
            }
            "Profile"->{
                if(drawerLayout.isOpen)drawerLayout.close()
                else drawerLayout.open()
                return super.onSupportNavigateUp()
            }
            else -> return NavigationUI.navigateUp(navController,drawerLayout)
        }

    }
}