package com.daneshnaik.jobsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class learning extends AppCompatActivity {
    BottomNavigationView bottom_navigation_learning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        bottom_navigation_learning=findViewById(R.id.bottom_navigation_home);
        bottom_navigation_learning.setSelectedItemId(R.id.applications);

        bottom_navigation_learning.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int nav_id=item.getItemId();
                if (nav_id==R.id.home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                } else if (nav_id==R.id.profile_menu) {
                    startActivity(new Intent(getApplicationContext(), Profile_Management_activity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;

                } else if (nav_id==R.id.applications) {

                    return  true;
                } else if (nav_id==R.id.chats) {
                    startActivity(new Intent(getApplicationContext(), Messages.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                }
                return true;
            }
        });
    }
}