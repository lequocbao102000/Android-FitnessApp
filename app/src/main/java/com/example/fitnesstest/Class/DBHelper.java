package com.example.fitnesstest.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;

public class DBHelper {
    Context context;
    Utils utils;
    String dbName="WorkoutDB.db";

    public DBHelper(Context context) {
        this.context = context;
        utils=new Utils(context);
    }

    public DBHelper(Context context, Utils utils) {
        this.context = context;
        this.utils = utils;
    }
    private SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }
    private void closeDB(SQLiteDatabase db) {
        db.close();
    }
    public void createTable() {
        SQLiteDatabase db = openDB();
        String sqlWorkout = "CREATE TABLE IF NOT EXISTS tblWorkout (" + " WorkoutID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + " Image TEXT," + " Name TEXT );";
        String sqlExercise = "CREATE TABLE IF NOT EXISTS tblExercise (" + " ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + " Name TEXT," + " Description TEXT," + " GIF TEXT,"+" Level TEXT," + " WorkoutID INTEGER );";
        String sqlDailyBMI = "CREATE TABLE IF NOT EXISTS tblDailybmi (" + " Date TEXT NOT NULL PRIMARY KEY," + " Cao FLOAT," + " Nang FLOAT,"+" BMI FLOAT,"+" Hinh TEXT );";
        String sqlDailyEx = "CREATE TABLE IF NOT EXISTS tblDailyex (" + " Date TEXT NOT NULL," + " Name TEXT," + " GIF TEXT,"+" Level TEXT );";
        db.execSQL(sqlWorkout);
        db.execSQL(sqlExercise);
        db.execSQL(sqlDailyBMI);
        db.execSQL(sqlDailyEx);
        closeDB(db);
    }
    public  void dropTable()
    {
        SQLiteDatabase db=openDB();
        String sqldrop1="DROP TABLE IF EXISTS tblExercise";
        String sqldrop="DROP TABLE IF EXISTS tblWorkout";
        db.execSQL(sqldrop);
        db.execSQL(sqldrop1);
        closeDB(db);
    }

    public ArrayList<Workout> getALLWorkout(){
        SQLiteDatabase db = openDB();
        ArrayList<Workout> arr = new ArrayList<>();
        String sql = "select * from tblWorkout";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    int image = csr.getInt(1);
                    String name = csr.getString(2);
                    arr.add(new Workout(id, image , name));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<Exercise> getExercise(int maworkout){
        SQLiteDatabase db = openDB();
        ArrayList<Exercise> arr = new ArrayList<>();
        String sql = "select * from tblExercise where WorkoutID="+maworkout+"";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String description=csr.getString(2);
                    int gif=csr.getInt(3);
                    int level=csr.getInt(4);
                    int workoutid=csr.getInt(5);
                    arr.add(new Exercise(id,name,description,gif,level,workoutid));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public ArrayList<BMI> getDailyBMI(String datechon){
        SQLiteDatabase db = openDB();
        ArrayList<BMI> arr=new ArrayList<>();
        Cursor csr=db.rawQuery("select * from tblDailybmi where Date='"+datechon+"' limit 1",null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    double cao = csr.getDouble(1);
                    double nang = csr.getDouble(2);
                    double bmi = csr.getDouble(3);
                    int hinh = csr.getInt(4);
                    arr.add(new BMI(cao, nang, bmi, hinh));
                } while (csr.moveToNext());
            }
        }

        closeDB(db);
        return arr;
    }

    public ArrayList<Exercise> getDailyEX(String datechon){
        SQLiteDatabase db = openDB();
        ArrayList<Exercise> arr=new ArrayList<>();
        Cursor csr=db.rawQuery("select * from tblDailyex where Date='"+datechon+"'",null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    String name = csr.getString(1);
                    int gif = csr.getInt(2);
                    int level = csr.getInt(3);
                    arr.add(new Exercise(name, gif, level));
                } while (csr.moveToNext());
            }
        }

        closeDB(db);
        return arr;
    }

    public void insertWorkout(){
        ArrayList<Workout> arrWorkout =utils.getDataWorkout();
        SQLiteDatabase db = openDB();
        int ma=0;
        for(Workout fu : arrWorkout) {
            ContentValues cv = new ContentValues();
            cv.put("WorkoutID",ma);
            cv.put("Image", fu.getImage());
            cv.put("Name", fu.getName());
            db.insert("tblWorkout", null, cv);
            ma=ma+1;
        }
        closeDB(db);
    }

    public void insertExercise(){
        ArrayList<Exercise> arrExercise =utils.getDataExercise();
        SQLiteDatabase db = openDB();
        int ma=0;
        for(Exercise ex : arrExercise) {
            ContentValues cv = new ContentValues();
            cv.put("ID",ma);
            cv.put("Name", ex.getName());
            cv.put("Description", ex.getDesription());
            cv.put("GIF", ex.getGif());
            cv.put("Level",ex.getLevel());
            cv.put("WorkoutID",ex.getIdworkout());
            db.insert("tblExercise", null, cv);
            ma=ma+1;
        }
        closeDB(db);
    }

    public void insertDailyBMI(String ngay,double cao,double nang,double bmi,String hinh){
        SQLiteDatabase db=openDB();
        ContentValues cv=new ContentValues();
        cv.put("Date",ngay);
        cv.put("Cao",cao);
        cv.put("Nang",nang);
        cv.put("BMI",bmi);
        cv.put("Hinh",hinh);
        db.insert("tblDailybmi",null,cv);
        closeDB(db);
    }

    public void insertDailyEx(String ngay,String name,String gif,String level){
        SQLiteDatabase db=openDB();
        ContentValues cv=new ContentValues();
        cv.put("Date",ngay);
        cv.put("Name",name);
        cv.put("GIF",gif);
        cv.put("Level",level);
        db.insert("tblDailyex",null,cv);
        closeDB(db);
    }



    public boolean check()
    {
        boolean check =false;
        SQLiteDatabase db = openDB();
        String sql = "select * from tblWorkout";
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.getCount()>0)
        {
            check = true;
        }
        closeDB(db);
        return check;
    }
}
