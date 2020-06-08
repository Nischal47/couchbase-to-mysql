package com.example.couchbasemysql.model;

public class Order {
    String id;
    String status;
    String item;
    String itemName;
    String description;
    Integer quantity;
    Long time;
    Double price;
    Boolean complement;

    public Order(){

    }

    public Order( String status, String item, String itemName, String id, String description, Integer quantity, Long time, Double price, String group, Boolean complement){
        this.status = status;
        this.item = item;
        this.itemName = itemName;
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.time = time;
        this.price = price;
        this.complement = complement;
    }

}
