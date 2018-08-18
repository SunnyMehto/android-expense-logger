package com.sunapp.sunnym.roomapp.Model;

/**
 * Created by SunnyM on 10/18/2015.
 */
public class UserInfo
{
    private String sFirstName;
    private String sLastName;
    private String sContactNumber;

    public UserInfo()
    {

    }

    public String getsFirstName() {
        return sFirstName;
    }

    public void setsFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsContactNumber() {
        return sContactNumber;
    }

    public void setsContactNumber(String sContactNumber) {
        this.sContactNumber = sContactNumber;
    }
}
