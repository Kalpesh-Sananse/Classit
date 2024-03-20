package com.example.dept1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public  static final String DBNAME="login.db";
    public DBHelper( Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT , password TEXT,Regno TEXT primary key,Phoneno TEXT, EMail TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");

    }
    public boolean insertData(String Username, String password, String Regno, String phone, String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username",Username);
        values.put("password",password);
        values.put("Regno",Regno);
        values.put("Phoneno",phone);
        values.put("EMail",Email);

        long result = db.insert("users",null,values);
        if(result == -1) return  false;
        else return true;


    }

    public boolean checkusername(String regno){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where Regno=?",new String[] {regno});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public boolean checkusernamepassword(String regno, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where Regno=? and password=?", new String[]{regno,password});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public Cursor  getdata(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from users",null);
        return cursor;
    }


}
