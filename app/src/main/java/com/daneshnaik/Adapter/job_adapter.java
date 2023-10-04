package com.daneshnaik.Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daneshnaik.clasess_fetch.job_details;
import com.daneshnaik.jobsearchapp.R;
import com.daneshnaik.jobsearchapp.portal_link_opner;

import java.util.ArrayList;

public class job_adapter extends RecyclerView.Adapter<job_adapter.Viewport> {
    Context context;
    ArrayList<job_details> arraylist_jobs;
    public job_adapter(Context context,ArrayList<job_details> arraylist_jobs){
        this.arraylist_jobs=arraylist_jobs;
        this.context=context;
    }
     public void setfileterUpdatelist(ArrayList<job_details> filterlist){
        this.arraylist_jobs=filterlist;
        notifyDataSetChanged();
     }

    @NonNull
    @Override
    public job_adapter.Viewport onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_job_holder,parent,false);
        return new Viewport(view);
    }

    @Override
    public void onBindViewHolder(@NonNull job_adapter.Viewport holder, int position) {
     job_details jobDetails=arraylist_jobs.get(position);
     holder.job_title.setText(jobDetails.getJob_title());
     holder.job_description.setText("  "+jobDetails.getJob_descrtption());
     holder.package_amt.setText("  "+jobDetails.getPackage_amount() +"/- INR");
     holder.branch.setText("  "+jobDetails.getBranch());
     holder.company_name.setText(jobDetails.getCompany_name());
     holder.location_badge.setText("  "+jobDetails.getLocation());
     holder.last_date_single.setText(" Last Date : "+jobDetails.getLast_date());
        Glide.with(context).load(jobDetails.getCompany_image_url()).placeholder(R.drawable.baseline_person_4_24).into(holder.Company_image_adapter);
     holder.apply_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(context, portal_link_opner.class);
             intent.putExtra("link",jobDetails.getLink());
             context.startActivity(intent);
         }
     });

    }

    @Override
    public int getItemCount() {
        return arraylist_jobs.size() ;
    }

    public class Viewport extends RecyclerView.ViewHolder {
        TextView job_title,company_name,branch,package_amt,job_description,location_badge,last_date_single;
        AppCompatButton apply_btn;
        ImageView Company_image_adapter;

        public Viewport(@NonNull View itemView) {
            super(itemView);
            job_title=itemView.findViewById(R.id.position_name);
            company_name=itemView.findViewById(R.id.company_name);
            branch=itemView.findViewById(R.id.branch_name);
            package_amt=itemView.findViewById(R.id.package_amount);
            job_description=itemView.findViewById(R.id.job_des);
            location_badge=itemView.findViewById(R.id.location_job);
            apply_btn=itemView.findViewById(R.id.apply_btn);
            last_date_single=itemView.findViewById(R.id.last_date_single);
            Company_image_adapter=itemView.findViewById(R.id.company_image_data_single);


        }
    }
}
