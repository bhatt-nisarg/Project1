package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class view_image extends AppCompatActivity implements View.OnClickListener {
    ImageView viewimage;
    TextView viewtext;
    Button down,share;
    OutputStream outputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        viewimage = findViewById(R.id.viewpic);
        viewtext = findViewById(R.id.viewtex);
        down = findViewById(R.id.download);
        share = findViewById(R.id.share);
        ActivityCompat.requestPermissions(view_image.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        Intent intent = getIntent();
        down.setOnClickListener(this);
        share.setOnClickListener(this);
        Picasso.with(getApplicationContext()).load(intent.getStringExtra("image")).into(viewimage);
        viewtext.setText(intent.getStringExtra("text"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.download:
                    saveimage();
                break;
            case R.id.share:
                break;
        }

    }
    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;

    }

    private void saveimage() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Bitmap b = getBitmapFromView(viewimage);

        viewimage.setDrawingCacheEnabled(false);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String folder;
        folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + "SaveImage/";
        File dir = new File(folder);

        if (!dir.exists()){

            dir.mkdir();
            Log.d("drdd",dir.exists()+" ");

        }


        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + "SaveImage/"+System.currentTimeMillis()+".jpg");
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
            Toast.makeText(view_image.this,"Successfuly Saved",Toast.LENGTH_SHORT).show();
            Log.d("sdsaadsa", file+"==="+ outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}