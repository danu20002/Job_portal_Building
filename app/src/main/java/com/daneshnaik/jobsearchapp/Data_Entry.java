package com.daneshnaik.jobsearchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daneshnaik.clasess_fetch.job_details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Data_Entry extends AppCompatActivity {
TextInputEditText company_name_data,job_role_data,branch_name_data,job_description_data,package_amount_data,link_data,location_name_data,last_date_data;
AppCompatButton submit_btn_data;
FirebaseAuth auth;
FirebaseDatabase database;
Uri company_image;
ImageView company_pic;
FirebaseStorage storage;
int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        company_name_data=findViewById(R.id.company_name_data);
        job_role_data=findViewById(R.id.job_role_data);
        branch_name_data=findViewById(R.id.branch_name_data);
        job_description_data=findViewById(R.id.job_description_data);
        package_amount_data=findViewById(R.id.package_amount_data);
        link_data=findViewById(R.id.link_provide_data);
        location_name_data=findViewById(R.id.location_name_data);
        company_pic=findViewById(R.id.company_image_data);
        last_date_data=findViewById(R.id._last_date_data);
        submit_btn_data=findViewById(R.id.submit_btn_data);

        //firebase
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        final String senderId=FirebaseAuth.getInstance().getUid();

        submit_btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(Data_Entry.this);
                progressDialog.setTitle("Uploading to Firebase");
                progressDialog.setIcon(R.drawable.baseline_link_24);
                progressDialog.setMessage("Uploading Image Please wait");
                progressDialog.show();
                StorageReference reference=storage.getReference().child("Company_images").child(auth.getUid());
                reference.putFile(company_image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isComplete()){
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photo_data_taker=uri.toString();
                                    String company_name_taker=company_name_data.getEditableText().toString().trim();
                                    String job_role_taker=job_role_data.getEditableText().toString().trim();
                                    String branch_name_taker=branch_name_data.getEditableText().toString().trim();
                                    String job_discription_taker=job_description_data.getEditableText().toString().trim();
                                    String package_amount_taker=package_amount_data.getEditableText().toString().trim();
                                    String link_data_taker=link_data.getEditableText().toString().trim();
                                    String location_data_taker=location_name_data.getEditableText().toString().trim();
                                    String last_date_data_taker=last_date_data.getEditableText().toString().trim();
                                    job_details jobby_boy=new job_details(senderId,job_role_taker,company_name_taker,branch_name_taker,package_amount_taker,job_discription_taker,photo_data_taker,location_data_taker,link_data_taker,last_date_data_taker);
                                    database.getReference().child("Job_Apply").push().setValue(jobby_boy).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            progressDialog.setMessage("please wait 2 sec Uploading ");
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    startActivity(new Intent(Data_Entry.this, MainActivity.class));
                                                    finish();
                                                }
                                            },2000);
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
                                            },2000);
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Data_Entry.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Data_Entry.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });
        company_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,46);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if(data.getData()!=null){
                company_pic.setImageURI(data.getData());
                company_image=data.getData();
            }
        }
    }
}