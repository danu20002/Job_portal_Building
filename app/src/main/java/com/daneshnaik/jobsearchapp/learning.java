package com.daneshnaik.jobsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class learning extends AppCompatActivity {
    BottomNavigationView bottom_navigation_learning;
    AppCompatButton logout_btn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        bottom_navigation_learning=findViewById(R.id.bottom_navigation_home);
        bottom_navigation_learning.setSelectedItemId(R.id.applications);
        logout_btn=findViewById(R.id.logout_btn);


        auth=FirebaseAuth.getInstance();


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), Enter_Activity.class));
                finish();
            }
        });











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