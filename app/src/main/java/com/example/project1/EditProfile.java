package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {
    SQLiteDatabaseHandler db;
    EditText change_phoneno,change_email,change_password;
    Button update_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        db = new SQLiteDatabaseHandler(this);
        change_phoneno = findViewById(R.id.phoneno);
        change_email = findViewById(R.id.email);
        change_password = findViewById(R.id.password);
        update_detail = findViewById(R.id.update);


        update_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = change_phoneno.getText().toString();
                String email = change_email.getText().toString();
                String password = change_password.getText().toString();
                if (phone.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_SHORT).show();

                }
                else {
                    boolean detail = db.update(email,password,phone);
                    if (detail == true){
                        Toast.makeText(getApplicationContext(),"Data Update!",Toast.LENGTH_SHORT).show();
                        Intent i  = new Intent(EditProfile.this,SettingFragment.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Data is Not Updated",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}