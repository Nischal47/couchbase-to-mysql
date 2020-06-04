package com.example.couchbasemysql.model;

public class Item {

    String id;
    String name;
    Double price;
    String size;
    String category;

    public Item(){

    }

    public Item(String id, String name, Double price, String size, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.category = category;
    }

}
