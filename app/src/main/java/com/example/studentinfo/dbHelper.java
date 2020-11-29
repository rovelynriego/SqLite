package com.example.studentinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    public dbHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(inputid TEXT primary key,inputname TEXT , inputaddress TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists Userdetails");

    }


    public boolean insertuserdata(String inputid, String inputname, String inputaddress) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inputid", inputid);
        contentValues.put("inputname", inputname);
        contentValues.put("inputaddress", inputaddress);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }

    public boolean updateuserdata(String inputid, String inputname, String inputaddress) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inputname", inputname);
        contentValues.put("inputaddress", inputaddress);
        Cursor cursor = DB.rawQuery("Select* from Userdetails where inputid= ?", new String[]{inputid});
        if (cursor.getCount() > 0) {


            long result = DB.update("Userdetails", contentValues, "inputid=?", new
                    String[]{inputid});

            if (result == -1) {
                return false;

            } else {
                return true;
            }

        } else {
            return false;
        }
    }




    public boolean deletedata(String inputid) {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select* from Userdetails where inputid= ?", new String[]{inputid});
        if (cursor.getCount()>0){


            long result = DB.delete("Userdetails","inputid=?",new
                    String[]{inputid});

            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else {
            return false;
        }

    }




    public Cursor getdata() {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;

    }






}

