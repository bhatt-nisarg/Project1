package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class view_image extends AppCompatActivity {
    ImageView viewimage;
    TextView viewtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        viewimage = findViewById(R.id.viewpic);
        viewtext = findViewById(R.id.viewtex);

        Intent intent = getIntent();

        Picasso.with(getApplicationContext()).load(intent.getStringExtra("image")).into(viewimage);
        viewtext.setText(intent.getStringExtra("text"));
    }
}