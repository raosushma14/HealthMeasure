package com.example.sushma.healthmeasure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Health.db";
    public static final String TABLE_NAME = "calories_table";
    public static final String ID_COLUMN = "ID";
    public static final String DATE_COLUMN = "DATE";
    public static final String BREAKFAST_COLUMN = "BREAKFAST";
    public static final String SNACK1_COLUMN = "SNACK1";
    public static final String LUNCH_COLUMN = "LUNCH";
    public static final String SNACK2_COLUMN = "SNACK2";
    public static final String DINNER_COLUMN = "DINNER";
    public static final String BREAKFAST_CALORIE_COLUMN = "BFC";
    public static final String SNACK1_CALORIE_COLUMN = "S1C";
    public static final String LUNCH_CALORIE_COLUMN = "LC";
    public static final String SNACK2_CALORIE_COLUMN = "S2C";
    public static final String DINNER_CALORIE_COLUMN = "DC";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATE_COLUMN + " TEXT NOT NULL, " +
                BREAKFAST_COLUMN + " TEXT NOT NULL, " +
                SNACK1_COLUMN + " TEXT NOT NULL, " +
                LUNCH_COLUMN + " TEXT NOT NULL, " +
                SNACK2_COLUMN + " TEXT NOT NULL, " +
                DINNER_COLUMN + " TEXT NOT NULL, " +
                BREAKFAST_CALORIE_COLUMN + " REAL NOT NULL," +
                SNACK1_CALORIE_COLUMN + " REAL NOT NULL," +
                LUNCH_CALORIE_COLUMN + " REAL NOT NULL," +
                SNACK2_CALORIE_COLUMN + " REAL NOT NULL," +
                DINNER_CALORIE_COLUMN + " REAL NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+  TABLE_NAME);
    }

    public boolean insertData(CalorieEntryDTO calorieEntryDTO){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE_COLUMN,calorieEntryDTO.getDate());
        contentValues.put(BREAKFAST_COLUMN,calorieEntryDTO.getBreakfast());
        contentValues.put(SNACK1_COLUMN,calorieEntryDTO.getSnack1());
        contentValues.put(LUNCH_COLUMN,calorieEntryDTO.getLunch());
        contentValues.put(SNACK2_COLUMN,calorieEntryDTO.getSnack2());
        contentValues.put(DINNER_COLUMN,calorieEntryDTO.getDinner());
        contentValues.put(BREAKFAST_CALORIE_COLUMN,calorieEntryDTO.getBreakfast_calorie());
        contentValues.put(SNACK1_CALORIE_COLUMN,calorieEntryDTO.getSnack1_calorie());
        contentValues.put(LUNCH_CALORIE_COLUMN,calorieEntryDTO.getLunch_calorie());
        contentValues.put(SNACK2_CALORIE_COLUMN,calorieEntryDTO.getSnack2_calorie());
        contentValues.put(DINNER_CALORIE_COLUMN,calorieEntryDTO.getDinner_calorie());

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
        //return true;
    }

    public Cursor getDataBasedOnDate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery("select * from "+TABLE_NAME+ " where "+DATE_COLUMN+"="+date,null);
        //Cursor res = db.rawQuery("SELECT * FROM  WHERE TRIM(name) = '"+name.trim()+"'", null)
        Cursor res = db.query(TABLE_NAME,
                new String[] {DATE_COLUMN,BREAKFAST_COLUMN,SNACK1_COLUMN,LUNCH_COLUMN,SNACK2_COLUMN,DINNER_COLUMN,BREAKFAST_CALORIE_COLUMN,SNACK1_CALORIE_COLUMN,LUNCH_CALORIE_COLUMN,SNACK2_CALORIE_COLUMN,DINNER_CALORIE_COLUMN},
                DATE_COLUMN + "=?",
                new String[] {date},
                null,
                null,
                null,
                null);
        return res;

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public boolean updateData(CalorieEntryDTO calorieEntryDTO){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DATE_COLUMN,calorieEntryDTO.getDate());
        contentValues.put(BREAKFAST_COLUMN,calorieEntryDTO.getBreakfast());
        contentValues.put(SNACK1_COLUMN,calorieEntryDTO.getSnack1());
        contentValues.put(LUNCH_COLUMN,calorieEntryDTO.getLunch());
        contentValues.put(SNACK2_COLUMN,calorieEntryDTO.getSnack2());
        contentValues.put(DINNER_COLUMN,calorieEntryDTO.getDinner());
        contentValues.put(BREAKFAST_CALORIE_COLUMN,calorieEntryDTO.getBreakfast_calorie());
        contentValues.put(SNACK1_CALORIE_COLUMN,calorieEntryDTO.getSnack1_calorie());
        contentValues.put(LUNCH_CALORIE_COLUMN,calorieEntryDTO.getLunch_calorie());
        contentValues.put(SNACK2_CALORIE_COLUMN,calorieEntryDTO.getSnack2_calorie());
        contentValues.put(DINNER_CALORIE_COLUMN,calorieEntryDTO.getDinner_calorie());

        int isUpdate = db.update(TABLE_NAME,contentValues, "DATE = ?",new String[] { calorieEntryDTO.getDate() });

        if(isUpdate > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
