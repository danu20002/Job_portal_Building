package com.daneshnaik.jobsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Management_activity extends AppCompatActivity {
    BottomNavigationView bottom_navigation_prof;
    FirebaseAuth auth;
    FirebaseDatabase database;
    CircleImageView image_profile_photo;
    TextView username_profile_management,Email_profile_management;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        image_profile_photo=findViewById(R.id.imagview_profile_management);
        username_profile_management=findViewById(R.id.username_profile_management);
         Email_profile_management=findViewById(R.id.text_email_profile);

        database=FirebaseDatabase.getInstance();
       auth= FirebaseAuth.getInstance();
       database.getReference().child("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               String profile_pic_profile_management=snapshot.child("profile_image").getValue().toString();
               if(snapshot.exists()){

                   Glide.with(getApplicationContext()).load(profile_pic_profile_management).into(image_profile_photo);
                   username_profile_management.setText(snapshot.child("name").getValue().toString());
                   Email_profile_management.setText(snapshot.child("email").getValue().toString());
               }else{
                   Glide.with(getApplicationContext()).load(R.drawable.baseline_person_4_24).into(image_profile_photo);
                   username_profile_management.setText("");
                   Email_profile_management.setText("");
               }


           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
































        bottom_navigation_prof=findViewById(R.id.bottom_navigation_home);
        bottom_navigation_prof.setSelectedItemId(R.id.profile_menu);

        bottom_navigation_prof.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int nav_id=item.getItemId();
                if (nav_id==R.id.home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                } else if (nav_id==R.id.profile_menu) {

                    return true;

                } else if (nav_id==R.id.applications) {
                    startActivity(new Intent(getApplicationContext(), learning.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
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