package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
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
import java.util.Objects;

public class view_image extends AppCompatActivity implements View.OnClickListener {
    ImageView viewimage;
    TextView viewtext;
    Button down,share;
    Intent intent;
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
         intent = getIntent();
        down.setOnClickListener(this);
        share.setOnClickListener(this);
        Picasso.with(getApplicationContext()).load(intent.getStringExtra("image")).into(viewimage);
        viewtext.setText(intent.getStringExtra("text"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.download:

//                    saveimage();
                try {
                    saveimgMediaStore();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.share:
                shareImage();
                break;
        }

    }

    private void shareImage() {
        Bitmap icon = getBitmapFromView(viewimage);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);


        OutputStream outstream;
        try {
            outstream = getContentResolver().openOutputStream(uri);
            icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            outstream.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image"));

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

//    private void saveimage() {
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        Bitmap b = getBitmapFromView(viewimage);
//
//        viewimage.setDrawingCacheEnabled(false);
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//
//        String folder;
//        folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + "SaveImage/";
//        File dir = new File(folder);
//
//        if (!dir.exists()){
//
//            dir.mkdir();
//            Log.d("drdd",dir.exists()+" ");
//
//        }
//
//
//        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + "SaveImage/"+System.currentTimeMillis()+".jpg");
//        try {
//            file.createNewFile();
//            outputStream = new FileOutputStream(file);
//            outputStream.write(bytes.toByteArray());
//            outputStream.close();
//            Toast.makeText(view_image.this,"Successfuly Saved",Toast.LENGTH_SHORT).show();
//            Log.d("sdsaadsa", file+"==="+ outputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    public void saveimgMediaStore() throws IOException {
        Bitmap bitmap = getBitmapFromView(viewimage);
        FileOutputStream fos;
        String folder;
        folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + "SaveImage/";
        File dir = new File(folder);

        if (!dir.exists()){

            dir.mkdir();
            Log.d("drdd",dir.exists()+" ");

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis() + ".jpg");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM+File.separator+"SaveImage/");
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                fos = (FileOutputStream) resolver.openOutputStream(Objects.requireNonNull(imageUri));
//            fos = resolver.openOutputStream(Objects.requireNonNull(imageUri));

        } else {
//            String imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
            File image = new File(folder, System.currentTimeMillis() + ".jpg");
            fos = new FileOutputStream(image);
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        Objects.requireNonNull(fos).close();
        Toast.makeText(view_image.this,"Successfuly Saved",Toast.LENGTH_SHORT).show();
    }


}