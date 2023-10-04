package com.daneshnaik.jobsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Messages extends AppCompatActivity {
    BottomNavigationView bottom_navigation_messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        bottom_navigation_messages=findViewById(R.id.bottom_navigation_home);
        bottom_navigation_messages.setSelectedItemId(R.id.chats);

        bottom_navigation_messages.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

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
                    startActivity(new Intent(getApplicationContext(), learning.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return  true;
                } else if (nav_id==R.id.chats) {

                    return true;
                }
                return true;
            }
        });
    }
}