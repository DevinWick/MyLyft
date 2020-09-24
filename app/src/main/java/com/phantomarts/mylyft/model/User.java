package com.phantomarts.mylyft.model;

import com.google.android.gms.maps.model.LatLng;

public class User {
    public static final int ACCOUNT_COMPLETE_STAGE1=1; //phone verification
    public static final int ACCOUNT_COMPLETE_ALL=0;

    public static LatLng currentLocation;

    private String email;
    private String password;
    private String phoneNo;
    private String firstName;
    private String lastName;
    private int regCompleteStatus=1;

    public User() {
    }

    public int getRegCompleteStatus() {
        return regCompleteStatus;
    }

    public void setRegCompleteStatus(int regCompleteStatus) {
        this.regCompleteStatus = regCompleteStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
