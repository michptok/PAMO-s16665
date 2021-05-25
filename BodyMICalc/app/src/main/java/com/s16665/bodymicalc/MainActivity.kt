package com.s16665.bodymicalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView?>(R.id.bottomNav)
        bottomNavigationView?.getMenu()?.getItem(0)?.isChecked = true
        bottomNavigationView?.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentLayout, StartFragment()).commit()
    }

    private val navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener? = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment = StartFragment()
        when (item.itemId) {
            R.id.start -> selectedFragment = StartFragment()
            R.id.bmi -> selectedFragment = BmiFragment()
            R.id.food -> selectedFragment = FoodFragment()
            R.id.chart -> selectedFragment = ChartFragment()
            R.id.quiz -> selectedFragment = QuizFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentLayout, selectedFragment).commit()
        true
    }
}