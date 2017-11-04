package com.jerin.employeemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jerin.employeemanager.adapter.EmployeeListAdapter;
import com.jerin.employeemanager.database.DataSource;
import com.jerin.employeemanager.models.Employee;

import java.util.List;


/**
 * Created by Jerin on 04-Nov-17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView employeeListView;
    static final int New_Employee_Request =100;
    static final int Save_Employee_Request =100;
    DataSource db;
    EmployeeListAdapter employeeListAdapter;


    List<Employee> allEmployees;
    boolean flag = true;

    @Override
    protected void onResume()
    {
        super.onResume();
        db = new DataSource(this);
        db.open();
        allEmployees = db.getAllEmployees();
           }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        employeeListView = (ListView) findViewById(R.id.notes_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeeCreate.class);
                startActivityForResult(intent, New_Employee_Request);
            }
        });



        db = new DataSource(this);
        fetchAllEmployees();

    }




    @Override
    public void onClick(View view) {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == New_Employee_Request) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                if(data.getBooleanExtra("newNote",false)) {
                    fetchAllEmployees();
                }

            }
        }

    }




    private void fetchAllEmployees() {
        db = new DataSource(this);
        db.open();
        final List<Employee> allEmployees = db.getAllEmployees();

        employeeListAdapter = new EmployeeListAdapter(this, allEmployees);
        employeeListView.setAdapter(employeeListAdapter);
        employeeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EmployeeView.class);
                intent.putExtra("id", allEmployees.get(position).getId());
                intent.putExtra("name", allEmployees.get(position).getName());
                intent.putExtra("description", allEmployees.get(position).getDescription());
                intent.putExtra("designation", allEmployees.get(position).getDesignation());
                flag = true;
                startActivityForResult(intent, Save_Employee_Request);


            }
        });
    }

    public
    void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        builder.setMessage("Do you want to exit the App?");

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

                MainActivity.this.finish();
            }
        });

        AlertDialog alert = builder.create();

        alert.setTitle("Warning"); // dialog  Title
        alert.show();

    }


}
