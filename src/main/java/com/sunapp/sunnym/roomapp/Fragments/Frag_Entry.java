package com.sunapp.sunnym.roomapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.sunapp.sunnym.roomapp.Common.Constants;
import com.sunapp.sunnym.roomapp.Model.EntryInfo;
import com.sunapp.sunnym.roomapp.Model.UserInfo;
import com.sunapp.sunnym.roomapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_Entry.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_Entry#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_Entry extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_Entry.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_Entry newInstance(String param1, String param2) {
        Frag_Entry fragment = new Frag_Entry();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView txtUserName,txtEntryItemName,txtEntryPrice,txtEntryDate;
    CheckBox chkSunny,chkAjit,chkMayur;
    Button btnOK;
    View view;

    public Frag_Entry() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view                = inflater.inflate(R.layout.fragment_frag__entry, container, false);
        txtUserName         = (TextView)view.findViewById(R.id.txtEntryUserName);
        txtEntryItemName    = (TextView)view.findViewById(R.id.txtEntryItemName);
        txtEntryPrice       = (TextView)view.findViewById(R.id.txtEntryPrice);
        txtEntryDate        = (TextView)view.findViewById(R.id.txtEntryDate);
        chkSunny            = (CheckBox)view.findViewById(R.id.chkSunny);
        chkAjit             = (CheckBox)view.findViewById(R.id.chkAjit);
        chkMayur            = (CheckBox)view.findViewById(R.id.chkMayur);
        btnOK               = (Button)view.findViewById(R.id.btnOK);

        txtUserName.setText(Constants.loggedInUserInfo.getsFirstName() + " " +Constants.loggedInUserInfo.getsLastName());

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEntry())
                {
                    Toast.makeText(Constants.CurrentActivity, "Submit Successfully !", Toast.LENGTH_LONG).show();
                    SetValue();
                    resetView();
                }
            }
        });
        return view;
    }

    private void SetValue()
    {
        EntryInfo objEntryInfo = new EntryInfo();
        objEntryInfo.setDate(txtEntryDate.getText().toString());
        objEntryInfo.setTotalPrice(txtEntryPrice.getText().toString());
        objEntryInfo.setItemName(txtEntryItemName.getText().toString());
        String sShare = "";
        if(chkSunny.isChecked())
        {
            sShare =  sShare + "S";
        }
        if(chkAjit.isChecked())
        {
            sShare =  sShare + " A";
        }
        if(chkMayur.isChecked())
        {
            sShare =  sShare + " M";
        }
        objEntryInfo.setShare(sShare);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("Date", objEntryInfo.getDate());
            jsonObject.put("Item", objEntryInfo.getItemName());
            jsonObject.put("Price", objEntryInfo.getTotalPrice());
            jsonObject.put("Share", objEntryInfo.getShare());
            writeStringAsFile(jsonObject.toString(), "Data.txt");

            if(isExternalStorageReadable())
            {
               String sssss = readFileAsString("Data.txt");
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void writeStringAsFile(final String fileContents, String fileName) {
        if(isExternalStorageWritable() ) {
            Context context = Constants.CurrentActivity.getApplicationContext();
            try {
                File file = new File(context.getFilesDir(),fileName);
                if(file.exists())
                {
                    FileWriter logWriter = new FileWriter(file,true);
                    BufferedWriter out = new BufferedWriter(logWriter);
                    out.write(fileContents);////<---HERE IS THE CHANGE
                    out.newLine();
                    out.close();
                }
                else
                {
                    FileWriter out = new FileWriter(new File(context.getFilesDir(), fileName));
                    out.write(fileContents);
                    out.close();
                }
            } catch (IOException e) {

            }
        }
    }
    ArrayList<EntryInfo> entryInfo = new ArrayList<EntryInfo>();
    public String readFileAsString(String fileName) {
        Context context = Constants.CurrentActivity.getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            //ArrayList<EntryInfo> entryInfo = new ArrayList<EntryInfo>();
            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            while ((line = in.readLine()) != null) {
                try {
                    JSONObject jsonObj = new JSONObject(line);
                    EntryInfo obj1 = new EntryInfo();
                    obj1.setItemName(jsonObj.getString("Item"));
                    obj1.setDate(jsonObj.getString("Date"));
                    obj1.setTotalPrice(jsonObj.getString("Price"));
                    obj1.setShare(jsonObj.getString("Share"));
                    entryInfo.add(obj1);
                }
                catch (Exception e) {

                }
            }
            //    stringBuilder.append(line);

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return stringBuilder.toString();
    }

    private void resetView()
    {
        txtEntryItemName.setText("");
        txtEntryPrice.setText("");
        //txtEntryDate.setText("");
        chkAjit.setChecked(false);
        chkMayur.setChecked(false);
        chkSunny.setChecked(false);
    }

    private boolean validateEntry()
    {
        boolean retVal = true;
        String sErrorMessage = "Login Info Incorrect !";
        if (txtEntryDate.getText().equals(""))
        {
            sErrorMessage = "Enter Date !";
            retVal = false;
        } else if(txtEntryItemName.getText().equals("")) {
            sErrorMessage = "Enter Item Name !";
            retVal = false;
        }
        else if(txtEntryPrice.getText().equals("")) {
            sErrorMessage = "Enter Item Price !";
            retVal = false;
        }
        else if (!chkSunny.isChecked() && !chkAjit.isChecked() && !chkMayur.isChecked())
        {
            sErrorMessage = "Check atleast one person !";
            retVal = false;
        }
        if(!retVal)
        {
            Toast.makeText(this.getActivity(),sErrorMessage,Toast.LENGTH_LONG).show();
        }
        return retVal;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
