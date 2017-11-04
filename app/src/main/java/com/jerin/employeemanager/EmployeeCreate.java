package com.jerin.employeemanager;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jerin.employeemanager.database.DataSource;

import java.util.ArrayList;
import java.util.List;


public class EmployeeCreate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText empDescription;
    private EditText empName;
    private Spinner empDesignation;
    private String designation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        empName = (EditText)findViewById(R.id.text_emp_name);
        empDescription = (EditText) findViewById(R.id.text_emp_description);
        empDesignation = (Spinner) findViewById(R.id.spinner_designation);
        empDesignation.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Developer");
        categories.add("Team Lead");
        categories.add("Manager");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        empDesignation.setAdapter(dataAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_employee, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save:

                saveNote();
                return true;

            case android.R.id.home:

                onBackPressed();


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                EmployeeCreate.this);
        builder.setMessage("Do you want to exit without saving?");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO  Auto-generated method stub

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO  Auto-generated method stub

               EmployeeCreate.this.finish();
            }
        });

        AlertDialog alert = builder.create();

        alert.setTitle("Details not saved"); // dialog  Title
        alert.show();

    }

    private void saveNote() {
        String name = empName.getText().toString();
        String description = empDescription.getText().toString();
        //String designation = empDesignation.getText().toString();

        if(empName.getText().toString().contentEquals("") || designation.contentEquals("") || empDescription.getText().toString().contentEquals("")) {
            Toast toast = Toast.makeText(this, "Fill every fields", Toast.LENGTH_SHORT);
            toast.show();
        }else
        {
            DataSource db = new DataSource(this);
            db.open();
            db.createEmployee(System.currentTimeMillis() + "" ,name, description , designation);
            Intent _result = new Intent();
            _result.putExtra("newNote",true);
            this.setResult(Activity.RESULT_OK, _result);
            this.finish();

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        designation = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
