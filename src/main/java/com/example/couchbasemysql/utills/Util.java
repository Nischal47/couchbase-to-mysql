package com.example.couchbasemysql.utills;

import com.couchbase.client.java.json.JsonObject;
import com.example.couchbasemysql.model.Group;
import com.example.couchbasemysql.model.Order;
import com.example.couchbasemysql.model.TableCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.log.Log;
import org.json.JSONObject;
import org.slf4j.Logger;

import javax.xml.catalog.CatalogException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Util {

    public static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    public static String buildUpdateTable(JSONObject jsonObject, String id) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        String categoryId = jsonObject.getJSONObject("category").getString("id");
        String status = jsonObject.getString("status");

        String insert = "name=\"" + name + "\",table_category=\"" + categoryId + "\",status=\"" + status + "\"";
        System.out.println(insert);
        return insert;
    }

    public static String buildInsertTable(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String id = jsonObject.getString("id");
        String type = jsonObject.getString("type");
        String categoryId = jsonObject.getJSONObject("category").getString("id");
        String status = jsonObject.getString("status");

        String insert = "(id,name,table_category,status) values (\"" + id + "\",\"" + name + "\",\"" + categoryId + "\",\"" + status + "\")";
        System.out.println(insert);
        return insert;

    }

    public static String buildUpdateCategory(JSONObject jsonObject, String id) {
        String name = jsonObject.getString("name");

        String insert = "name=\"" + name + "\"";
        System.out.println(insert);
        return insert;
    }

    public static String buildInsertCategory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String id = jsonObject.getString("id");

        String insert = "(id,name) values (\"" + id + "\",\"" + name + "\")";
        System.out.println(insert);
        return insert;
    }

    public static String buildUpdateGroup(JSONObject jsonObject, String id) {
        String name = jsonObject.getString("name");
        String tables = jsonObject.getString("table");
        Long time = jsonObject.getLong("time");
        Boolean paid = jsonObject.getBoolean("paid");

        String insert = "name=\"" + name + "\",tables=\"" + tables + "\",time=" + time + ",paid=" + paid;
        System.out.println(insert);
        return insert;
    }

    public static String buildInsertGroup(JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String tables = jsonObject.getString("table");
        Long time = jsonObject.getLong("time");
        Boolean paid = jsonObject.getBoolean("paid");

        String insert = "(id,name,tables,time,paid) values (\"" + id + "\",\"" + name + "\",\"" + tables + "\"," + time + "," + paid + ")";
        System.out.println(insert);
        return insert;
    }

    public static String buildInsertItem(JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        Integer price = jsonObject.getInt("price");
        String size = jsonObject.getString("size");
        String category = jsonObject.getString("category");
//        String image = jsonObject.getString("image");

        String insert = "(id,name,price,size,category) values (\"" + id + "\",\"" + name + "\"," + price + ",\"" + size + "\",\"" + category + "\")";
        System.out.println(insert);
        return insert;
    }

    public static String buildUpdateItem(JSONObject jsonObject, String id) {
        String name = jsonObject.getString("name");
        Integer price = jsonObject.getInt("price");
        String size = jsonObject.getString("size");
        String category = jsonObject.getString("category");
//        String image = jsonObject.getString("image");

        String insert = "name=\"" + name + "\",price=" + price + ",size=\"" + size + "\"," + "category=\"" + category + "\"";
        System.out.println(insert);
        return insert;
    }

    public static String buildInsertOrder(JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String status = jsonObject.getString("status");
        String item = jsonObject.getString("item");
        String itemName = jsonObject.getString("itemName");
        String description = jsonObject.getString("description");
        int quantity = jsonObject.getInt("quantity");
        long time = jsonObject.getLong("time");
        double price = jsonObject.getDouble("price");
        String group = jsonObject.getString("group");
        boolean complement = jsonObject.getBoolean("complement");

        String insert = "(id,statuss,item,itemName,description,quantity,time,price,groups,complement)" +
                " values (\"" + id + "\",\"" + status + "\",\"" + item + "\",\"" + itemName + "\",\"" + description + "\"," + quantity + "," + time + "," + price  + ",\""+ group + "\"," + complement + ")";
        System.out.println(insert);
        return insert;
    }

    public static String buildUpdateOrder(JSONObject jsonObject, String id) {
        String status = jsonObject.getString("status");
        String item = jsonObject.getString("item");
        String itemName = jsonObject.getString("itemName");
        int quantity = jsonObject.getInt("quantity");
        long time = jsonObject.getLong("time");
        double price = jsonObject.getDouble("price");
        String group = jsonObject.getString("group");
        Boolean complement = jsonObject.getBoolean("complement");

        String insert = "statuss=\"" + status + "\",item=\"" + item + "\",itemName=\"" + itemName + "\",quantity=" + quantity + ",time=" + time + ",price=" + price + ",groups=\"" + group + "\"" + ",complement=" + complement;
        System.out.println(insert);
        return insert;
    }

    public static String buildInsertBill(JSONObject jsonObject) {
        int serviceChargeAmount = jsonObject.getInt("serviceChargeAmount");
        String id = jsonObject.getString("id");
//        Group group = jsonObject.getJson;
        String table = jsonObject.getString("table");
        System.out.println(table);
        String tableCategory = jsonObject.getString("tableCategory");
        String date = jsonObject.getString("date");
        double subTotal = jsonObject.getDouble("subTotal");
        double totalDiscount = jsonObject.getDouble("totalDiscount");
        double grandTotal = jsonObject.getDouble("grandTotal");
        double tenderAmount = jsonObject.getDouble("tenderAmount");
        int changeAmount = jsonObject.getInt("changeAmount");
//        ArrayList<Order> orders = jsonObject.getJSONArray("id");
//        boolean isPaid = true;
        String insert = "(serviceChargeAmount,id,tables,tableCategory,date,subTotal," +
                "totalDiscount,grandTotal,tenderAmount," +
                "changeAmount) values (" + serviceChargeAmount + ",\"" + id + "\",\""  + table + "\",\""+
                tableCategory + "\",\"" + date + "\"," + subTotal + "," + totalDiscount + "," + grandTotal +
                "," + tenderAmount + "," + changeAmount + ")";
        System.out.println(insert);
        return insert;
    }

    public static String buildUpdateBill(JSONObject jsonObject, String ids) {
        int serviceChargeAmount = jsonObject.getInt("serviceChargeAmount");
//        Group group = jsonObject.getJson;
        String table = jsonObject.getString("table");
        String tableCategory = jsonObject.getString("tableCategory");
        String date = jsonObject.getString("date");
        double subTotal = jsonObject.getDouble("subTotal");
        double totalDiscount = jsonObject.getDouble("totalDiscount");
        double grandTotal = jsonObject.getDouble("grandTotal");
        double tenderAmount = jsonObject.getDouble("tenderAmount");
        int changeAmount = jsonObject.getInt("changeAmount");
//        ArrayList<Order> orders = jsonObject.getJSONArray("id");
//        boolean isPaid = false;
        String insert = "serviceChargeAmount=" + serviceChargeAmount + ",tables=\"" + table +"\"," +
                  "tableCategory=\"" + tableCategory + "\"," + "date=\"" + date + "\"," + "subTotal=" + subTotal + ",totalDiscount=" +
                totalDiscount + ",grandTotal=" + grandTotal + ",tenderAmount=" + tenderAmount + ",changeAmount=" + changeAmount;
        System.out.println(insert);
        return insert;
    }

    public static String buildInsertTableCategory(JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");

        String insert = "(id,name) values (\"" + id + "\",\"" + name + "\")";
        System.out.println(insert);
        return insert;
    }

    public static String buildUpdateTableCategory(JSONObject jsonObject, String id) {
        String name = jsonObject.getString("name");

        String insert = "name=\"" + name + "\"";
        System.out.println(insert);
        return insert;
    }

}
