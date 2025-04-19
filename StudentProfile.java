package com.example;

import java.math.BigDecimal;

public class StudentProfile { private int studentId;
private int userId; 
private String profileDetails; 
private boolean eligibilityStatus; 
private int academicId; 
private int liveBacklogs; 
private int deadBacklogs;
private BigDecimal cgpa;
private BigDecimal percentage10th;
private BigDecimal percentage12th; 
private String branchName; 
private boolean hasInternship;
private byte[] profilePicture; 
private String contactNo;
private String userHandle;
// Getters and setters
public int getStudentId() { return studentId; }
public void setStudentId(int studentId) { this.studentId = studentId; } 

public int getUserId() { return userId; } 
public void setUserId(int userId) { this.userId = userId; }

public String getProfileDetails() { return profileDetails; }
public void setProfileDetails(String profileDetails)

{ this.profileDetails = profileDetails; }
public boolean isEligibilityStatus() { return eligibilityStatus; } 

public void setEligibilityStatus(boolean eligibilityStatus) { this.eligibilityStatus = eligibilityStatus; }
public int getAcademicId() { return academicId; } 
public void setAcademicId(int academicId) { this.academicId = academicId; }
public int getLiveBacklogs() { return liveBacklogs; }
public void setLiveBacklogs(int liveBacklogs) { this.liveBacklogs = liveBacklogs; }
public int getDeadBacklogs() { return deadBacklogs; }
public void setDeadBacklogs(int deadBacklogs) { this.deadBacklogs = deadBacklogs; }
public BigDecimal getCgpa() { return cgpa; }
public void setCgpa(BigDecimal cgpa) { this.cgpa = cgpa; } 
public BigDecimal getPercentage10th() { return percentage10th; }
public void setPercentage10th(BigDecimal percentage10th) { this.percentage10th = percentage10th; } 
public BigDecimal getPercentage12th() { return percentage12th; }
public void setPercentage12th(BigDecimal percentage12th) { this.percentage12th = percentage12th; } 
public String getBranchName() { return branchName; }
public void setBranchName(String branchName) { this.branchName = branchName; }
public boolean isHasInternship() { return hasInternship; }
public void setHasInternship(boolean hasInternship) { this.hasInternship = hasInternship; } 
public byte[] getProfilePicture() { return profilePicture; } 
public void setProfilePicture(byte[] profilePicture) { this.profilePicture = profilePicture; }
public String getContactNo() { return contactNo; }
public void setContactNo(String contactNo) { this.contactNo = contactNo; }
public String getUserHandle() { return userHandle; }
public void setUserHandle(String userHandle) { this.userHandle = userHandle; }


}

