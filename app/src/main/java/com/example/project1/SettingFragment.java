package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class SettingFragment extends Fragment implements View.OnClickListener {

    ImageView profile_display;
    TextView editemail,editphone,editpassword,disp_email,disp_phone;
    ImageButton logout_btn;


    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);

        profile_display = view.findViewById(R.id.dis_profile);
        editemail = view.findViewById(R.id.edt_email);
        editphone = view.findViewById(R.id.edt_phone);
        editpassword = view.findViewById(R.id.edt_password);
        disp_email = view.findViewById(R.id.dis_email);
        disp_phone = view.findViewById(R.id.dis_phone);
        logout_btn = view.findViewById(R.id.logout);

        disp_phone.setText("1234567890");
        disp_email.setText("abc@gmail.oom");

        //setonclickevents
        profile_display.setOnClickListener(this);
        editemail.setOnClickListener(this);
        editpassword.setOnClickListener(this);
        editphone.setOnClickListener(this);
        logout_btn.setOnClickListener(this);

    return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dis_profile:
                break;
            case R.id.edt_email:
                break;
            case R.id.edt_password:
                break;
            case R.id.edt_phone:
                break;
            case R.id.logout:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                prefs.edit().putBoolean("Islogin", false).apply();
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                break;

        }
    }
}