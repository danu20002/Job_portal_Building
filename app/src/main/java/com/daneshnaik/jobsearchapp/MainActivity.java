package com.daneshnaik.jobsearchapp;




import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daneshnaik.Adapter.job_adapter;
import com.daneshnaik.clasess_fetch.job_details;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
LinearLayoutCompat linearLayoutCompat;
FloatingActionButton floatingActionButton;
RecyclerView recyclerView;
job_adapter adapter;
FirebaseAuth auth;
FirebaseDatabase database;
ArrayList<job_details> details;
androidx.appcompat.widget.SearchView searchView;
SwipeRefreshLayout swipeRefreshLayout;
ImageView profile_image_main;
BottomNavigationView bottom_navigation;
TextView name_card_main,view_all_name_user;


    int id =0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutCompat=findViewById(R.id.linear_layout_main);
        Animation animator= AnimationUtils.loadAnimation(this,R.anim.rcy_animation);
        linearLayoutCompat.setAnimation(animator);
        profile_image_main=findViewById(R.id.profile_image_data);
        swipeRefreshLayout=findViewById(R.id.refreshLayout);
        floatingActionButton =findViewById(R.id.floating_main);

       searchView=findViewById(R.id.main_serchview);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        name_card_main=findViewById(R.id.name_card_main);
        view_all_name_user=findViewById(R.id.view_all_main_user);




       FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               String profile_photo=snapshot.child("profile_image").getValue().toString();
               Glide.with(getApplicationContext()).load(profile_photo).placeholder(R.drawable.baseline_person_4_24).into(profile_image_main);
               name_card_main.setText(snapshot.child("name").getValue().toString());

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });


       view_all_name_user.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),Profile_Management_activity.class);
               Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rcy_animation);
               animation.start();
               startActivity(intent);
               finish();
           }
       });







        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition(0);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                layoutManager.setReverseLayout(true);
                layoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(layoutManager);

                swipeRefreshLayout.setRefreshing(false);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),Data_Entry.class));
//                auth.signOut();
//                startActivity(new Intent(MainActivity.this,Enter_Activity.class));
//                finish();

            }
        });
        details=new ArrayList<>();
        adapter=new job_adapter(this,details);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


        database.getReference().child("Job_Apply").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                details.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    job_details detailing=dataSnapshot.getValue(job_details.class);
                    details.add(detailing);
                    if(snapshot.exists())
                    {
                        id=(int) snapshot.getChildrenCount();
                    }
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        }
           );







        bottom_navigation=findViewById(R.id.bottom_navigation_home);
        bottom_navigation.setSelectedItemId(R.id.home);


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int nav_id=item.getItemId();
                if (nav_id==R.id.home){

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
                    startActivity(new Intent(getApplicationContext(), Messages.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                }
                return true;
            }
        });

    }
    private  void filterList(String text){
        ArrayList<job_details> filterlist=new ArrayList<>();
        for(job_details users1: details){
            if(users1.getCompany_name().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(users1);
            } else if (users1.getJob_title().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(users1);
            } else if (users1.getBranch().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(users1);
            } else if (users1.getJob_descrtption().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(users1);
            } else if (users1.getPackage_amount().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(users1);
            } else if (users1.getLocation().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(users1);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Match Found",Toast.LENGTH_LONG).show();
        }else{
            adapter.setfileterUpdatelist(filterlist);
        }

    }




}