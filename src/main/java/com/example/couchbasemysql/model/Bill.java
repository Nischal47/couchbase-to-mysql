package com.example.couchbasemysql.model;

import java.util.ArrayList;

public class Bill {

    Boolean serviceChargeAmount;
    String id;
    Group group;
    String table;
    String tableCategory;
    String date;
    Double subTotal;
    Double totalDiscount;
    Double grandTotal;
    Double tenderAmount;
    Integer changeAmount;
    ArrayList<Order> orders;
    Boolean isPaid;

    public Bill(){

    }
}
