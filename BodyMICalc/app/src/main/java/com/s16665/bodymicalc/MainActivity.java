package com.s16665.bodymicalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new StartFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new StartFragment();
            switch (item.getItemId()) {
                case R.id.start:
                    selectedFragment = new StartFragment();
                    break;
                case R.id.bmi:
                    selectedFragment = new BmiFragment();
                    break;
                case R.id.food:
                    selectedFragment = new FoodFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, selectedFragment).commit();
            return true;
        }
    };
}