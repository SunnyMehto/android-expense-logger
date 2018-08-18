package com.sunapp.sunnym.roomapp.Common;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

import com.sunapp.sunnym.roomapp.Model.UserInfo;
import com.sunapp.sunnym.roomapp.R;

import java.util.ArrayList;

/**
 * Created by SunnyM on 10/18/2015.
 */
public class Constants {
    public static ArrayList<UserInfo> listUser = new ArrayList<UserInfo>();
    public static UserInfo loggedInUserInfo;
    public static Activity CurrentActivity;
    public static boolean IsLoggedIn = false;


    public static void ReplaceFragment(Fragment fragment)
    {
        try {
            FragmentManager manager = Constants.CurrentActivity.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frameMain, fragment); // newInstance() is a static factory method.
            transaction.addToBackStack(fragment.getTag());
            transaction.commit();
        }
        catch   (Exception e)
        {
            Toast.makeText(Constants.CurrentActivity, e.getMessage(), Toast.LENGTH_SHORT);
        }
    }
}
