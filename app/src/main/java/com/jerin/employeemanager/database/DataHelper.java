package com.jerin.employeemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jerin on 03-Nov-17.
 */
public class DataHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Employee.db";

    private static final String TEXT_TYPE = "String";
    public static final String TABLE_EMPLOYEE = "entry";
    public static final String EMP_DESCRIPTION = "content";
    public static final String EMP_ID = "entryId";
    public static final String EMP_NAME = "title";
    public static final String EMP_DESIGNATION = "date";



    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_EMPLOYEE + "(" + EMP_ID + " TEXT PRIMARY KEY ," + TEXT_TYPE
            + " TEXT," + EMP_NAME + " TEXT," + EMP_DESCRIPTION
            + " TEXT," + EMP_DESIGNATION + " TEXT " + ")";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
