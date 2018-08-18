package com.sunapp.sunnym.roomapp.Model;

/**
 * Created by SunnyM on 10/18/2015.
 */
public class LoginInfo {

    private String sUserName;
    private String sUserPassword;

    public LoginInfo()
    {

    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsUserPassword() {
        return sUserPassword;
    }

    public void setsUserPassword(String sUserPassword) {
        this.sUserPassword = sUserPassword;
    }
}
