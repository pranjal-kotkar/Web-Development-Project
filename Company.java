package com.example;

import java.sql.Timestamp;
import java.sql.Date;

public class Company {
    private int companyId;
    private String companyName;
    private String contactPerson;
    private String contactEmail;
    private String details;
    private double stipend;
    private String eligibleBranches;
    private String location;
    private Date scheduleDate;
    private Timestamp createdAt;
    private String companyImage;
    private double cgpaCriteria;
    private String googleFormLink;

    // Getters and setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getStipend() {
        return stipend;
    }

    public void setStipend(double stipend) {
        this.stipend = stipend;
    }

    public String getEligibleBranches() {
        return eligibleBranches;
    }

    public void setEligibleBranches(String eligibleBranches) {
        this.eligibleBranches = eligibleBranches;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public double getCgpaCriteria() {
        return cgpaCriteria;
    }

    public void setCgpaCriteria(double cgpaCriteria) {
        this.cgpaCriteria = cgpaCriteria;
    }
    public String getGoogleFormLink() {
    	return googleFormLink; } 
    public void setGoogleFormLink(String googleFormLink) {
    	this.googleFormLink = googleFormLink; }
}

