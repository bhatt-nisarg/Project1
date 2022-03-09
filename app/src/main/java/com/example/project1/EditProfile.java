package com.example.project1;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabaseHandler db;
    TextView select_image;
    ImageView addimg;
    int SELECT_PICTURE = 200;
    EditText change_phoneno,change_email,change_password;
    Button update_detail;
    String path,dburl;

    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 100;
    public static boolean isExternalStorageDocument(Uri uri){
    return "com.android.externalstorage.documents".equals(uri.getAuthority());}

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);
        db = new SQLiteDatabaseHandler(this);
        change_phoneno = findViewById(R.id.phoneno);
        change_email = findViewById(R.id.email);
        change_password = findViewById(R.id.password);
        update_detail = findViewById(R.id.update);
        select_image = findViewById(R.id.select);
        addimg = findViewById(R.id.addimage);
//        backto_profile = findViewById(R.id.edit_back);
        select_image.setOnClickListener(this);
        update_detail.setOnClickListener(this);
//        backto_profile.setOnClickListener(this);
        checkPermissions();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.update:
                String phone = change_phoneno.getText().toString();
                String email = change_email.getText().toString();
                String password = change_password.getText().toString();


                if (phone.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_SHORT).show();

                }
                else {
                    boolean detail = db.update(email,password,phone,path);
                    if (detail == true){
                        Toast.makeText(getApplicationContext(),"Data Update!",Toast.LENGTH_SHORT).show();
                        Fragment fragment = new SettingFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.settinglayout,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();


                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Data is Not Updated",Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.select:
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permission Required !",Toast.LENGTH_SHORT).show();
                    checkPermissions();
                }
                else {
                    imageselecter();
                }
                break;


        }

    }
    void imageselecter(){
        Intent i  = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"select image"),SELECT_PICTURE);
    }
     @Override public void onActivityResult(int requestcode,int resultcode,Intent data) {
         super.onActivityResult(requestcode, resultcode, data);
         if (resultcode == RESULT_OK){
               if (requestcode == SELECT_PICTURE ){
                 Uri Selectedimage = data.getData();
                 if (Selectedimage != null){
                     Log.d("url",Selectedimage.toString());
                     path = getpath(getApplicationContext(),Selectedimage);
                     dburl = path.substring(path.lastIndexOf("/") + 1);
                     addimg.setImageURI(Selectedimage);


                 }
                     else {
                     Picasso.with(getApplicationContext()).load(R.drawable.userimage).into(addimg);
                 }
             }
         }
     }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissions() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user_profile why we need to read the contacts
            }
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
            return;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    checkPermissions();
                }
                return;
            }
        }
    }
    private String getpath(Context context,final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else {
                    return "/storage/" + type + "/" + split[1] + "";
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }

            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                DatabaseUtils.dumpCursor(cursor);

                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;

    }
}