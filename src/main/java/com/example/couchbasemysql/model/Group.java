package com.example.couchbasemysql.model;

import java.util.Timer;

public class Group {

    String id;
    String name;
    String table;
    Timer time;
    Boolean isPaid;

    public Group(){

    }

    public Group(String id, String name, String table, Timer time, Boolean isPaid){
        this.id = id;
        this.name = name;
        this.table = table;
        this.time = time;
        this.isPaid = isPaid;
    }

}

