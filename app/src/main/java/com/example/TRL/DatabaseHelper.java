package com.example.TRL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "books_table";
    private static final String COL1 = "b_id";
    private static final String COL2 = "b_title";
    private static final String COL3 = "b_autor_vn";
    private static final String COL4 = "b_autor_nn";
    private static final String COL5 = "b_comment";
    private static final String COL6 = "b_gelesen";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " INT DEFAULT 0 )";
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Here needs to be something that doesnt just delete the whole table after an update lol
        /*db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);*/
    }

    public boolean addData(String col2, String col3, String col4, String col5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, col2);
        contentValues.put(COL3, col3);
        contentValues.put(COL4, col4);
        contentValues.put(COL5, col5);
        contentValues.put(COL6, 0);

        //Log.d(TAG, "addData: Adding " + items + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(String where, int orderby, boolean orderasc){

        String ordercol;
        String order;

        if(orderasc == true) {
            order = "ASC";
        }
        else{
            order = "DESC";
        }

        switch(orderby){
            case 0:
                ordercol = COL1;
                break;
            case 1:
                ordercol = COL2;
                break;
            case 2:
                ordercol = COL3;
                break;
            case 3:
                ordercol = COL4;
                break;
            case 4:
                ordercol = COL5;
                break;
            case 5:
                ordercol = COL6;
                break;
            default:
                ordercol = COL2;
                break;
        }

        if(where != ""){
            where = " WHERE " + where;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + where + " ORDER BY " + ordercol + " " + order;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Returns only the ID that matches the name passed in
     * @param titel
     * @return
     */
    public Cursor getItemID(String titel){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + titel + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newValue
     * @param id
     *
     */
    public void updateName(String newValue, int id, String col){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + col +
                " = '" + newValue + "' WHERE ID = '" + id + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newValue);
        db.execSQL(query);
    }

    public void updateName(Integer newValue, int id, String col){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + col +
                " = " + newValue + " WHERE ID = '" + id + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newValue);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     */
    public void deleteName(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE ID = '" + id + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting ID" + id + " from database.");
        db.execSQL(query);
    }

    public int getCount(String where){
        SQLiteDatabase db = this.getWritableDatabase();

        if(where != ""){
            where = " WHERE " + where;
        }

        String query = "SELECT ID FROM " + TABLE_NAME + where;

        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}




















