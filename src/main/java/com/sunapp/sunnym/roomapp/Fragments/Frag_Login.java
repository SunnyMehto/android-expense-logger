package com.sunapp.sunnym.roomapp.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sunapp.sunnym.roomapp.Common.Constants;
import com.sunapp.sunnym.roomapp.Model.UserInfo;
import com.sunapp.sunnym.roomapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_Login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_Login extends Fragment {
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
     * @return A new instance of fragment Frag_Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_Login newInstance(String param1, String param2) {
        Frag_Login fragment = new Frag_Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView txtUserName,txtPassword;
    Button btnLogin;
    View view;

    public Frag_Login() {
        // Required empty public constructor
        SetUserData();
    }

    private void SetUserData()
    {

        UserInfo user1 = new UserInfo();
        user1.setsFirstName("Sunny");
        user1.setsLastName("Mehto");
        user1.setsContactNumber("+919867866700");

        UserInfo user2 = new UserInfo();
        user2.setsFirstName("Ajit");
        user2.setsLastName("Kumar");
        user2.setsContactNumber("+919867866700");

        UserInfo user3 = new UserInfo();
        user3.setsFirstName("Mayur");
        user3.setsLastName("Tripathi");
        user3.setsContactNumber("+919867866700");

        Constants.listUser.add(user1);
        Constants.listUser.add(user2);
        Constants.listUser.add(user3);
    }

    private void OpenEntryView()
    {
        Toast.makeText(this.getActivity(),"Successfully logged in  !",Toast.LENGTH_LONG).show();
        Constants.ReplaceFragment(new Frag_Entry());
    }

    private boolean validateLogin()
    {
        boolean retVal = false;
        String sErrorMessage = "Login Info Incorrect !";
        if(txtUserName.getText().equals(""))
        {
            sErrorMessage = "Enter User Name !";
        }
        else if(txtUserName.getText().equals("")) {
            sErrorMessage = "Enter Password !";
        }
        else {
            String sEnteredUserName = txtUserName.getText().toString();
            String sEnteredPassword = txtPassword.getText().toString();

            int count = Constants.listUser.size();
            for (int i = 0; i < count; i++) {
                UserInfo userInfo = Constants.listUser.get(i);
                if (userInfo.getsFirstName().equals(sEnteredUserName) &&
                        userInfo.getsLastName().equals(sEnteredPassword)) {
                    Constants.loggedInUserInfo = userInfo;
                    Constants.IsLoggedIn = true;
                    retVal = true;
                    break;
                }
            }
        }
        if(!retVal)
        {
            Toast.makeText(this.getActivity(),sErrorMessage,Toast.LENGTH_LONG).show();
        }
        return retVal;
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
        view = inflater.inflate(R.layout.fragment_frag__login, container, false);

        txtUserName = (TextView)view.findViewById(R.id.txtLoginUserName);
        //txtUserName.setText("Sunny");
        txtPassword = (TextView)view.findViewById(R.id.txtLoginPassword);
        //txtPassword.setText("Mehto");
        btnLogin    = (Button)view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateLogin()) {
                    OpenEntryView();
                }
            }
        });

        return view;
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
