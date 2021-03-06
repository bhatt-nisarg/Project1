package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView pass,otp,timer,send;
    EditText editpass,editemail;
    boolean setotp;
    SQLiteDatabaseHandler db;
    boolean timerOn = true;
    ImageButton submitdata;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

        db = new SQLiteDatabaseHandler(this);
//       long a= db.addUser_info();
        db.insertdata();
//       Log.d("sdsdsada", String.valueOf(a));
        pass = findViewById(R.id.password_select);
        otp = findViewById(R.id.otp_select);
        timer = findViewById(R.id.timeertext);
        send = findViewById(R.id.send);
        editemail = findViewById(R.id.editemail);
        editpass = findViewById(R.id.password);
        submitdata = findViewById(R.id.submit);
        submitdata.setOnClickListener(this);
        pass.setOnClickListener(this);
        otp.setOnClickListener(this);
        timer.setVisibility(View.INVISIBLE);
        send.setVisibility(View.INVISIBLE);

        send.setOnClickListener(this);

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.password_select:
                editpass.setText("");
                pass.setBackground(getResources().getDrawable(R.drawable.select_type));
                otp.setBackground(getResources().getDrawable(R.drawable.type_unselect));
                otp.setTextColor(getResources().getColor(R.color.pink));
                pass.setTextColor(getResources().getColor(R.color.white));
                pass.setElevation(8);
                send.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);
                otp.setElevation(0);
                if (setotp){
                    editpass.setHint("Enter OTP");
                    editpass.setInputType(InputType.TYPE_CLASS_NUMBER);

                }else {
                    editpass.setHint("Password");
                    editpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.otp_select:
                editpass.setText("");
                pass.setBackground(getResources().getDrawable(R.drawable.type_unselect));
                otp.setBackground(getResources().getDrawable(R.drawable.select_type));
                otp.setTextColor(getResources().getColor(R.color.white));
                pass.setTextColor(getResources().getColor(R.color.pink));
                otp.setElevation(8);
                pass.setElevation(0);
                send.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                if (setotp){
                    editpass.setHint("Enter OTP");
                    editpass.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else {
                    editpass.setHint("Phone No");
                    editpass.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                break;

            case R.id.send:

                editpass.setHint("Enter OTP");
                setotp = true;
                if (timerOn) {
                    new CountDownTimer(30000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timerOn = false;
                            NumberFormat f = new DecimalFormat("00");
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            timer.setText(f.format(min) + ":" + f.format(sec));
                        }

                        public void onFinish() {
                            timerOn = true;
                            send.setText("Re-Send");
                            timer.setText("00:00");
                        }
                    }.start();
                }
            case  R.id.submit:
                Cursor cursor = db.fetch();
                cursor.moveToFirst();
                String email =editemail.getText().toString();
                if (setotp) {
                    String otpselect = editpass.getText().toString();
                    if (editpass.length() != 6){
                        Toast.makeText(getApplicationContext(),"Enter valid OTP",Toast.LENGTH_SHORT).show();

                    }
                    else if(email.equals(cursor.getString(0))  && otpselect.equals(cursor.getString(2))){
                        Toast.makeText(getApplicationContext(),"Login Succesful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,Homepage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    String passwordselect = editpass.getText().toString();
                    //Log.d("asdf",cursor.getString(0)+" " + cursor.getString(1)+" "+cursor.getString(2) + " "+cursor.getString(3));
                    if (!(editemail.getText().toString().trim().matches(emailPattern)) || editpass.length()==0 || editpass.length() < 6){
                        Toast.makeText(getApplicationContext(),"Enter Valid Detail",Toast.LENGTH_SHORT).show();
                    }
                    else if(email.equals(cursor.getString(0)) && passwordselect.equals(cursor.getString(1))){


                        Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,Homepage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }


        }

    }
}