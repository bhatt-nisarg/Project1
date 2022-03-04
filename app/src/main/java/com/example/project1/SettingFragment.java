package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
    SQLiteDatabaseHandler db;
    ImageView profile_display;
    TextView editprofile,disp_email,disp_phone;
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
        db = new SQLiteDatabaseHandler(getContext());
        Cursor cursor = db.fetch();
        cursor.moveToFirst();
        profile_display = view.findViewById(R.id.dis_profile);
        editprofile = view.findViewById(R.id.edt_profile);
        disp_email = view.findViewById(R.id.dis_email);
        disp_phone = view.findViewById(R.id.dis_phone);
        logout_btn = view.findViewById(R.id.logout);

        disp_phone.setText(cursor.getString(0));
        disp_email.setText(cursor.getString(3));

        //setonclickevents
        editprofile.setOnClickListener(this);
        profile_display.setOnClickListener(this);
        logout_btn.setOnClickListener(this);

    return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.dis_profile:
                break;
            case R.id.edt_profile:
                Intent intent = new Intent(getContext(),EditProfile.class);

                startActivity(intent);
                break;

            case R.id.logout:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                prefs.edit().putBoolean("Islogin", false).apply();

                Intent intent3 = new Intent(getContext(),MainActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent3);
                getActivity().finish();
                break;

        }
    }
}