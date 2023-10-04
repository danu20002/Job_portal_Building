package com.daneshnaik.jobsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
TextInputEditText forgot_email;
AppCompatButton forgot_btn;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgot_email=findViewById(R.id.forgot_email);
        forgot_btn=findViewById(R.id.forgot_btn);
        auth=FirebaseAuth.getInstance();
        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgot_email_get=forgot_email.getEditableText().toString().trim();
                final ProgressDialog progressDialog=new ProgressDialog(forgot_password.this);
                progressDialog.setTitle("Don't Forget Your Mail Again");
                progressDialog.setMessage("sending Reset Mail");
                progressDialog.show();

                auth.sendPasswordResetEmail(forgot_email_get).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.setMessage("Sent SuccessFully");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(forgot_password.this, Enter_Activity.class));
                                Toast.makeText(forgot_password.this, "Sent Email of resetting password", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        },3000);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.setMessage(e.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();

                            }
                        },3000);
                    }
                });
            }
        });
    }
}