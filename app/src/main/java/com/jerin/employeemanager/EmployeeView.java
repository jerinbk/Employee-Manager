package com.jerin.employeemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jerin.employeemanager.database.DataSource;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Jerin on 03-Nov-17.
 */
public class EmployeeView extends AppCompatActivity  {
    TextView empName;
    TextView empDesignation;
    TextView empDescription;
    DataSource db = new DataSource(this);
    String id, name, description, designation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        empName = (TextView) findViewById(R.id.viewEmpName);
        empDescription = (TextView) findViewById(R.id.viewEmpDescription);
        empDesignation = (TextView) findViewById(R.id.viewEmpDesignation);
        empName.setText(getIntent().getStringExtra("name"));
        empDescription.setText(getIntent().getStringExtra("description"));
        empDesignation.setText(getIntent().getStringExtra("designation"));
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        designation = getIntent().getStringExtra("designation");
        description = getIntent().getStringExtra("description");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view_employee, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {


            case android.R.id.home:
                onBackPressed();

            case R.id.delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        EmployeeView.this);
                builder.setMessage("Do you want to delete this Employee details?");

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
                        db.open();
                        db.deleteEmployee(id);
                        db.close();
                        Intent _result = new Intent();
                        _result.putExtra("newNote", true);
                        setResult(Activity.RESULT_OK, _result);
                        EmployeeView.this.finish();
                    }
                });

                AlertDialog alert = builder.create();

                alert.setTitle("Warning"); // dialog  Title
                alert.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void saveEditEmployee() {

        String name = empName.getText().toString();
        String designation = empDesignation.getText().toString();
        String description = empDescription.getText().toString();

        if ((name.contentEquals("")) && (description.contentEquals("")) && (designation.contentEquals(""))) {
            db.open();
            db.deleteEmployee(id);
            db.close();
            Intent _result = new Intent();
            _result.putExtra("newNote", true);
            setResult(Activity.RESULT_OK, _result);
            EmployeeView.this.finish();
        }

    }

}
