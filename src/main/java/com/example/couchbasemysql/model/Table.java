package com.example.couchbasemysql.model;

public class Table {

    String id;
    String name;
    TableCategory tableCategory;
    Status status;

    public Table(){

    }

    public Table(String id, String name, TableCategory tableCategory, Status status){
        this.id = id;
        this.name = name;
        this.tableCategory = tableCategory;
        this.status = status;
    }


}
