package com.jerin.employeemanager.models;

/**
 * Created by Jerin on 03-Nov-17.
 */
public class Employee {

    private String id;
    private String designation;
    private String description;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getDesignation() {return designation;}

    public void setDesignation(String designation) {this.designation = designation;}




    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}


    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Employee(String id, String description, String name, String designation){
        this.designation = designation;
        this.name = name;
        this.description = description;
        this.id = id;

    }


    public Employee() {
    }


}


