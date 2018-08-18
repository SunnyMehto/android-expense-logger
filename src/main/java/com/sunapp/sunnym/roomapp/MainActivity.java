package com.sunapp.sunnym.roomapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.sunapp.sunnym.roomapp.Common.Constants;
import com.sunapp.sunnym.roomapp.Fragments.Frag_Entry;
import com.sunapp.sunnym.roomapp.Fragments.Frag_ListEntry;
import com.sunapp.sunnym.roomapp.Fragments.Frag_Login;

public class MainActivity extends AppCompatActivity implements Frag_Login.OnFragmentInteractionListener
        ,Frag_ListEntry.OnFragmentInteractionListener
        ,Frag_Entry.OnFragmentInteractionListener

{
    Button btnListData;

    boolean doubleBackToExitPressedOnce;
    @Override
    public void onBackPressed() {
        FragmentManager fm = Constants.CurrentActivity.getFragmentManager();
        //Log.print("back stack entry", fm.getBackStackEntryCount() + "");

        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
            // super.onBackPressed();
            // return;
        } else {
            if (doubleBackToExitPressedOnce) {
                fm.popBackStack();
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press one more time to exit",
                    Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    doubleBackToExitPressedOnce = false;
                }
            }, 3000);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnListData = (Button)findViewById(R.id.btnListData);
        btnListData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.IsLoggedIn)
                {
                    Frag_ListEntry listEntry = new Frag_ListEntry();
                    Constants.ReplaceFragment(listEntry);
                }
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                handleUncaughtException(thread, e);
            }
        });
        Constants.CurrentActivity = this;
        Constants.ReplaceFragment(new Frag_Login());
    }


    private void handleUncaughtException (Thread thread, Throwable e)
    {
        //String fullFileName = "extractLogToFile(); // code not shown

        // The following shows what I'd like, though it won't work like this.
        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra (Intent.EXTRA_EMAIL, new String[] {"me@mydomain.com"});
        intent.putExtra (Intent.EXTRA_SUBJECT, "log file");
        //intent.putExtra (Intent.EXTRA_STREAM, Uri.parse ("file://" + fullFileName));
        //startActivityForResult (intent, ACTIVITY_REQUEST_SEND_LOG);
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        //if (requestCode == ACTIVITY_REQUEST_SEND_LOG)
            System.exit(1);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
