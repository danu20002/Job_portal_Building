package com.daneshnaik.clasess_fetch;

public class job_details {


    String senderId;
    String job_title;
    String Company_name;
    String Branch;
    String Package_amount;
    String Job_descrtption;


    String company_image_url;

    String location;
    String link;



    String last_date;
    public job_details(String job_title, String company_name, String branch, String package_amount, String job_descrtption) {
        this.job_title = job_title;
        Company_name = company_name;
        Branch = branch;
        Package_amount = package_amount;
        Job_descrtption = job_descrtption;
    }

    public job_details() {
    }

    public job_details(String job_title, String company_name, String branch, String package_amount, String job_descrtption, String link) {
        this.job_title = job_title;
        Company_name = company_name;
        Branch = branch;
        Package_amount = package_amount;
        Job_descrtption = job_descrtption;
        this.link = link;
    }

    public job_details(String senderId, String job_title, String company_name, String branch, String package_amount, String job_descrtption, String link) {
        this.senderId = senderId;
        this.job_title = job_title;
        Company_name = company_name;
        Branch = branch;
        Package_amount = package_amount;
        Job_descrtption = job_descrtption;
        this.link = link;
    }

    public job_details(String senderId, String job_title, String company_name, String branch, String package_amount, String job_descrtption, String location, String link) {
        this.senderId = senderId;
        this.job_title = job_title;
        Company_name = company_name;
        Branch = branch;
        Package_amount = package_amount;
        Job_descrtption = job_descrtption;
        this.location = location;
        this.link = link;
    }

    public job_details(String senderId, String job_title, String company_name, String branch, String package_amount, String job_descrtption, String company_image_url, String location, String link) {
        this.senderId = senderId;
        this.job_title = job_title;
        Company_name = company_name;
        Branch = branch;
        Package_amount = package_amount;
        Job_descrtption = job_descrtption;
        this.company_image_url = company_image_url;
        this.location = location;
        this.link = link;
    }

    public job_details(String senderId, String job_title, String company_name, String branch, String package_amount, String job_descrtption, String company_image_url, String location, String link, String last_date) {
        this.senderId = senderId;
        this.job_title = job_title;
        Company_name = company_name;
        Branch = branch;
        Package_amount = package_amount;
        Job_descrtption = job_descrtption;
        this.company_image_url = company_image_url;
        this.location = location;
        this.link = link;
        this.last_date = last_date;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getPackage_amount() {
        return Package_amount;
    }

    public void setPackage_amount(String package_amount) {
        Package_amount = package_amount;
    }

    public String getJob_descrtption() {
        return Job_descrtption;
    }

    public void setJob_descrtption(String job_descrtption) {
        Job_descrtption = job_descrtption;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getCompany_image_url() {
        return company_image_url;
    }

    public void setCompany_image_url(String company_image_url) {
        this.company_image_url = company_image_url;
    }
    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }
}
