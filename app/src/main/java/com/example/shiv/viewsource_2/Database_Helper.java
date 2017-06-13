package com.example.shiv.viewsource_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shiv on 5/28/17.
 * Okay so here are all my database operations. This database system is heavy AF but i hope to manage the complexity
 *
 * Menu tables and Level tables are different. All of them have to be initialized at the start of the level
 * . I am thinking of writing a generalized function for all inserts into the databse
 */

public class Database_Helper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "Game.db";
    //Menu Tables
    public static final String TABLE_NAME = "StartMenu";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "File";
    public static final String COL_2 = "Type";
    public static final String COL_3 = "Content";
    //Level Tables:
    //Level 1:
    public static final String LEVEL_1 = "level_1";
    public static final String L1ID = "ID";
    public static final String L1SYM = "Symbol";
    //public static final String L1SRC = "Source"; ditching the source for now
    public static final String L1X= "XP"; // X position
    public static final String L1Y= "YP"; //Y position
    //Level 1 Source Code Tables
    public static final String LEVEL_1SRC = "level_1src";
    //public static final String L1ID = "ID";
    public static final String SRC1 = "Source"; // Actaull source code
    public static final String Type1 = "Type"; //editable or non editable source code
    public static final String Sequence= "Seq"; // Position of the code in the database
    //This table has a foreign key that references the ID of level_1 table

    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Variables.error+= "\nHere";
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,File TEXT,Type TEXT, Content TEXT)");

        try {
            Variables.error += "\nTryig to create";
            db.execSQL("create table " + LEVEL_1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Symbol TEXT, XP TEXT, YP TEXT)"); //Source TEXT, //keeping the source outside
            //db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,File TEXT,Type TEXT, Content TEXT)");
            db.execSQL("create table " + LEVEL_1SRC +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Par INTEGER, Source TEXT,Type TEXT, Seq TEXT, FOREIGN KEY(Par) REFERENCES level_1(ID))");
            Variables.error += "\nIT Shouldnt reach here";
        }
        catch (Exception e){
            Variables.error += "not creating";
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS "+LEVEL_1);
        onCreate(db);

    }

    public void startLevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        //bhai kya mehenat wale jugaad ke baad ye error theek hu hai
        db.execSQL("DROP TABLE IF EXISTS "+LEVEL_1);
        db.execSQL("create table " + LEVEL_1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Symbol TEXT, XP TEXT, YP TEXT)"); //Source TEXT //no source for now
        if(Variables.enter_level) {
            db.execSQL("DROP TABLE IF EXISTS " + LEVEL_1SRC);
            db.execSQL("create table " + LEVEL_1SRC + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Par INTEGER, Source TEXT,Type TEXT, Seq TEXT, FOREIGN KEY(Par) REFERENCES level_1(ID))");

        }
        //onCreate(db);

    }

    //For main menu
    public boolean insertData(String FileN,String FType,String FCont) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,FileN);
        contentValues.put(COL_2,FType);
        contentValues.put(COL_3,FCont);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //For levels in general
    // Create this first
    public boolean insertLevelData(String sym, String x, String y) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(L1SYM, sym);
        //contentValues.put(L1SRC, source); For now I'm not putting in source
        contentValues.put(L1X, x);
        contentValues.put(L1Y, y);
        long result = db.insert(LEVEL_1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //For level codes in general
    //create tis next
    public boolean insertLevelSource(String src,String type, String seq, Integer par) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SRC1, src);
        contentValues.put(Type1, type);
        contentValues.put(Sequence, seq);
        contentValues.put("Par", par);
        long result = db.insert(LEVEL_1SRC,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getAllSource() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+LEVEL_1SRC,null);
        return res;
    }

    public Cursor getAllLevelData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+LEVEL_1,null);
        return res;
    }
    public boolean updateData(String ID, String FileN,String FType,String FCont) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,FileN);
        contentValues.put(COL_2,FType);
        contentValues.put(COL_3,FCont);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { ID });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public boolean truncateData(String TName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME); //causes errors
        db.execSQL("DELETE FROM "+LEVEL_1);
        return true;
    }
}
