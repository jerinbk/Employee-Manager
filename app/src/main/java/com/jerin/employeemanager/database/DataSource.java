package com.jerin.employeemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jerin.employeemanager.models.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerin on 03-Nov-17.
 */
public class DataSource {
    private SQLiteDatabase database;
    private DataHelper dbHelper;
    private String[] noteAllColumns = {DataHelper.EMP_ID, DataHelper.EMP_NAME, DataHelper.EMP_DESCRIPTION, DataHelper.EMP_DESIGNATION};

    public DataSource(Context context) {
        dbHelper = new DataHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Employee createEmployee(String id, String name, String description, String designation) {
        ContentValues values = new ContentValues();

        values.put(DataHelper.EMP_NAME, name);
        values.put(DataHelper.EMP_DESCRIPTION, description);
        values.put(DataHelper.EMP_ID, id);
        values.put(DataHelper.EMP_DESIGNATION, designation);

        try {
            database.insert(DataHelper.TABLE_EMPLOYEE, null, values);
            Cursor cursor = database.query(DataHelper.TABLE_EMPLOYEE,
                    noteAllColumns, null, null, null, null, null);
            cursor.moveToFirst();
            Employee newUser = cursorToEmployee(cursor);
            cursor.close();
            Log.i("BASKIN",newUser.getId());
            return newUser;
        } catch (SQLiteConstraintException ex) {
            Log.i("BASKIN","duplicate "+id);
            return null;
        }
    }

    public void deleteAllEmployees() {
        int rows = database.delete(DataHelper.TABLE_EMPLOYEE, null, null);
        Log.i("BASKIN", rows + " deleted");
    }

    public void UpdateEmployee(String id , String name, String description, String designation) {
        ContentValues values = new ContentValues();
        values.put(DataHelper.EMP_ID, id);
        values.put(DataHelper.EMP_NAME, name);
        values.put(DataHelper.EMP_DESCRIPTION, description);
        values.put(DataHelper.EMP_DESIGNATION, designation);

        int rows = database.update(DataHelper.TABLE_EMPLOYEE,
                values, "entryId = " + id, null);


        Log.i("user", rows + "updated");
    }



    public void deleteEmployee(String id) {

        database.delete(DataHelper.TABLE_EMPLOYEE, "entryId = " + id, null);

    }


    public List<Employee> getAllEmployees()
    {
        List<Employee> employees = new ArrayList<Employee>();

        Cursor cursor = database.query(DataHelper.TABLE_EMPLOYEE,
                noteAllColumns, null , null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Employee _Note = cursorToEmployee(cursor);
                employees.add(0, _Note);
                cursor.moveToNext();
            }

        cursor.close();
        return employees;
    }



    private Employee cursorToEmployee(Cursor cursor) {
        Employee note = new Employee();
        note.setDescription(cursor.getString(cursor.getColumnIndex(DataHelper.EMP_DESCRIPTION)));
        note.setName(cursor.getString(cursor.getColumnIndex(DataHelper.EMP_NAME)));
        note.setId(cursor.getString(cursor.getColumnIndex(DataHelper.EMP_ID)));
        note.setDesignation(cursor.getString(cursor.getColumnIndex(DataHelper.EMP_DESIGNATION)));
        return note;
    }

}
