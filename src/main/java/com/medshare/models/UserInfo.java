package com.medshare.models;

import javax.persistence.*;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private String locationName;

    @OneToOne(mappedBy = "userInfo")
    private User user;

    public UserInfo() {
    }

    public UserInfo(String phoneNumber, String locationName) {
        this.phoneNumber = phoneNumber;
        this.locationName = locationName;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "UserContactInfo{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + locationName + '\'' +
                '}';
    }
}
