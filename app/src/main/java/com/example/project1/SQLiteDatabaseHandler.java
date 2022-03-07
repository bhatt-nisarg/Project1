package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Blob;


public class SQLiteDatabaseHandler extends SQLiteOpenHelper {
    //DATABASE VERSION
    private static final int DATABASE_VERSION = 2;
    //DATABASE NAME
    public static final String DB_NAME ="logindata";

    //TABLE NAME
    public static final String TABLE_NAME = "users";

    //column names
//    public static final String COLUMN_ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String OTP = "otp";
    public static final String PHONE = "phone";
    public static final String PROFILE_URL = "image";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("abd","created");
        String user_create = "CREATE TABLE " + TABLE_NAME + "("
                + EMAIL + " TEXT,"
                + PASSWORD + " TEXT,"
                + OTP + " NUMBER ,"
                + PHONE + " NUMBER ,"
                + PROFILE_URL + " TEXT "
                + ")";
        db.execSQL(user_create);

    }

    public void insertdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(EMAIL,"abc@gmail.com");
        Values.put(PASSWORD,"12345678");
        Values.put(OTP,"123456");
        Values.put(PHONE,"1234567890");
         Values.put(PROFILE_URL,"https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg");
        long id_res=db.insert(TABLE_NAME,null,Values);
        Log.d("CHECGCGHCGCA123", "2--" + id_res);
        db.close();
    }
    public boolean update(String email,String password,String phone,String image){
        Log.d("abcd",""+" " +  email+" "+" " + phone + " "+password + " " + image);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(EMAIL,email);
        Values.put(PASSWORD,password);
        Values.put(OTP,"123456");
        Values.put(PHONE,phone);
        Values.put(PROFILE_URL,image);
        db.update(TABLE_NAME,Values,null,null);

        db.close();
            return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public Cursor fetch() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{EMAIL,PASSWORD,OTP,PHONE,PROFILE_URL}, null, null, null, null, null);
        if (cursor != null) {
           cursor.moveToFirst();
        }
        return cursor;
    }
}
