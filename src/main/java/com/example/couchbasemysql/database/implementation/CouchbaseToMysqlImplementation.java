package com.example.couchbasemysql.database.implementation;


import com.couchbase.client.java.json.JsonObject;
import com.example.couchbasemysql.database.CouchbaseToMysql;
import com.example.couchbasemysql.utills.ClearData;
import com.example.couchbasemysql.utills.Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.util.List;

public class CouchbaseToMysqlImplementation implements CouchbaseToMysql {
    List<String> couchbaseDataTable = null;
    List<String> couchbaseDataCategory = null;
    List<String> couchbaseDataItem = null;
    List<String> couchbaseDataGroup = null;
    List<String> couchbaseDataOrder = null;
    List<String> couchbaseDataTableCategory = null;

    @Override
    public void couchbaseToMysql() {

        CouchbaseDatabaseImplementation couchbaseDatabase = new CouchbaseDatabaseImplementation();
        try{
            couchbaseDatabase.setBucket("getting-started-db");
            couchbaseDataTable = couchbaseDatabase.query("WHERE type = \"table\"", JsonObject.create());
            couchbaseDataCategory = couchbaseDatabase.query("WHERE type = \"category\"", JsonObject.create());
            couchbaseDataGroup = couchbaseDatabase.query("WHERE type = \"group\"", JsonObject.create());
            couchbaseDataItem = couchbaseDatabase.query("WHERE type = \"menu_item\"", JsonObject.create());
            couchbaseDataOrder = couchbaseDatabase.query("WHERE type = \"order\"", JsonObject.create());
            couchbaseDataTableCategory = couchbaseDatabase.query("WHERE type = \"table_category\"", JsonObject.create());
        }finally {
            couchbaseDatabase.disconnect();
        }
        if(!CollectionUtils.isEmpty(couchbaseDataTable)){
            upsertTablesToMysql(couchbaseDataTable);
        }
        if(!CollectionUtils.isEmpty(couchbaseDataCategory)){
            upsertCategoryToMysql(couchbaseDataCategory);
        }
        if(!CollectionUtils.isEmpty(couchbaseDataGroup)){
            upsertGroupToMysql(couchbaseDataGroup);
        }
        if(!CollectionUtils.isEmpty(couchbaseDataItem)){
            upsertItemToMysql(couchbaseDataItem);
        }
        if(!CollectionUtils.isEmpty(couchbaseDataOrder)){
            upsertOrderToMysql(couchbaseDataOrder);
        }
        if(!CollectionUtils.isEmpty(couchbaseDataTableCategory)){
            upsertTablesCategoryToMysql(couchbaseDataTableCategory);
        }
    }


    @Override
    public void upsertTablesToMysql(List<String> data) {
        CouchbaseDatabaseImplementation couchbaseDatabase = null;
        MysqlDatabaseImplementation mysqlDatabase = null;

        try{
            couchbaseDatabase = new CouchbaseDatabaseImplementation();
            mysqlDatabase = new MysqlDatabaseImplementation();
            for (String record : data){
                JSONObject jRecord = new JSONObject(record);
                mysqlDatabase.executeUpdate("INSERT INTO " + "tables " + Util.buildInsertTable(jRecord)
                        + " ON DUPLICATE KEY UPDATE " + Util.buildUpdateTable(jRecord, jRecord.getString("id")));
            }
            List<String> mysqlData = mysqlDatabase.executeQuery("select * from `tables`");
            int i = 1;
            for (String mysql:mysqlData){
                JSONObject jRecord = new JSONObject(mysql);
                couchbaseDatabase.setBucket("getting-started-db");
                List<String> cb = couchbaseDatabase.query(" WHERE id =\""+jRecord.getString("id")+"\"");
                if(cb == null || cb.isEmpty()) {
                    mysqlDatabase.executeUpdate("DELETE FROM tables WHERE id = \"" + jRecord.getString("id") + "\"");
                    System.out.println("Deleted");
                }
            }
        } finally {
            mysqlDatabase.close();
            couchbaseDatabase.disconnect();
        }
    }

    @Override
    public void upsertCategoryToMysql(List<String> data) {
        CouchbaseDatabaseImplementation couchbaseDatabase = null;
        MysqlDatabaseImplementation mysqlDatabase = null;

        try{
            couchbaseDatabase = new CouchbaseDatabaseImplementation();
            mysqlDatabase = new MysqlDatabaseImplementation();
            for (String record : data){
                JSONObject jRecord = new JSONObject(record);
                mysqlDatabase.executeUpdate("INSERT INTO " + "category " + Util.buildInsertCategory(jRecord)
                        + " ON DUPLICATE KEY UPDATE " + Util.buildUpdateCategory(jRecord, jRecord.getString("id")));
            }
            List<String> mysqlData = mysqlDatabase.executeQuery("select * from `category`");
            int i = 1;
            for (String mysql:mysqlData){
                JSONObject jRecord = new JSONObject(mysql);
                couchbaseDatabase.setBucket("getting-started-db");
                List<String> cb = couchbaseDatabase.query(" WHERE id =\""+jRecord.getString("id")+"\"");
                if(cb == null || cb.isEmpty()) {
                    mysqlDatabase.executeUpdate("DELETE FROM tables WHERE id = \"" + jRecord.getString("id") + "\"");
                    System.out.println("Deleted");
                }
            }
        } finally {
            mysqlDatabase.close();
            couchbaseDatabase.disconnect();
        }
    }

    @Override
    public void upsertGroupToMysql(List<String> data) {
        CouchbaseDatabaseImplementation couchbaseDatabase = null;
        MysqlDatabaseImplementation mysqlDatabase = null;

        try{
            couchbaseDatabase = new CouchbaseDatabaseImplementation();
            mysqlDatabase = new MysqlDatabaseImplementation();
            for (String record : data){
                JSONObject jRecord = new JSONObject(record);
                mysqlDatabase.executeUpdate("INSERT INTO `group` " + Util.buildInsertGroup(jRecord)
                        + " ON DUPLICATE KEY UPDATE " + Util.buildUpdateGroup(jRecord, jRecord.getString("id")));
            }
            List<String> mysqlData = mysqlDatabase.executeQuery("select * from `group`");
            int i = 1;
            for (String mysql:mysqlData){
                JSONObject jRecord = new JSONObject(mysql);
                couchbaseDatabase.setBucket("getting-started-db");
                List<String> cb = couchbaseDatabase.query(" WHERE id =\""+jRecord.getString("id")+"\"");
                if(cb == null || cb.isEmpty()) {
                    mysqlDatabase.executeUpdate("DELETE FROM group WHERE id = \"" + jRecord.getString("id") + "\"");
                    System.out.println("Deleted");
                }
            }
        } finally {
            mysqlDatabase.close();
            couchbaseDatabase.disconnect();
        }
    }

    @Override
    public void upsertItemToMysql(List<String> data) {
        CouchbaseDatabaseImplementation couchbaseDatabase = null;
        MysqlDatabaseImplementation mysqlDatabase = null;

        try{
            couchbaseDatabase = new CouchbaseDatabaseImplementation();
            mysqlDatabase = new MysqlDatabaseImplementation();
            for (String record : data){
                JSONObject jRecord = new JSONObject(record);
                mysqlDatabase.executeUpdate("INSERT INTO " + "menu_item " + Util.buildInsertItem(jRecord)
                        + " ON DUPLICATE KEY UPDATE " + Util.buildUpdateItem(jRecord, jRecord.getString("id")));
            }
            List<String> mysqlData = mysqlDatabase.executeQuery("select * from `menu_item`");
            int i = 1;
            for (String mysql:mysqlData){
                JSONObject jRecord = new JSONObject(mysql);
                couchbaseDatabase.setBucket("getting-started-db");
                List<String> cb = couchbaseDatabase.query(" WHERE id =\""+jRecord.getString("id")+"\"");
                if(cb == null || cb.isEmpty()) {
                    mysqlDatabase.executeUpdate("DELETE FROM menu_item WHERE id = \"" + jRecord.getString("id") + "\"");
                    System.out.println("Deleted");
                }
            }
        } finally {
            mysqlDatabase.close();
            couchbaseDatabase.disconnect();
        }
    }

    @Override
    public void upsertOrderToMysql(List<String> data) {
        CouchbaseDatabaseImplementation couchbaseDatabase = null;
        MysqlDatabaseImplementation mysqlDatabase = null;

        try{
            couchbaseDatabase = new CouchbaseDatabaseImplementation();
            mysqlDatabase = new MysqlDatabaseImplementation();
            for (String record : data){
                JSONObject jRecord = new JSONObject(record);
                mysqlDatabase.executeUpdate("INSERT INTO `order` " + Util.buildInsertOrder(jRecord)
                        + " ON DUPLICATE KEY UPDATE " + Util.buildUpdateOrder(jRecord, jRecord.getString("id")));
            }
            List<String> mysqlData = mysqlDatabase.executeQuery("select * from `order`");
            int i = 1;
            for (String mysql:mysqlData){
                JSONObject jRecord = new JSONObject(mysql);
                couchbaseDatabase.setBucket("getting-started-db");
                List<String> cb = couchbaseDatabase.query(" WHERE id =\""+jRecord.getString("id")+"\"");
                if(cb == null || cb.isEmpty()) {
                    mysqlDatabase.executeUpdate("DELETE FROM order WHERE id = \"" + jRecord.getString("id") + "\"");
                    System.out.println("Deleted");
                }
            }
        } finally {
            mysqlDatabase.close();
            couchbaseDatabase.disconnect();
        }
    }

    @Override
    public void upsertTablesCategoryToMysql(List<String> data) {
        CouchbaseDatabaseImplementation couchbaseDatabase = null;
        MysqlDatabaseImplementation mysqlDatabase = null;

        try{
            couchbaseDatabase = new CouchbaseDatabaseImplementation();
            mysqlDatabase = new MysqlDatabaseImplementation();
            for (String record : data){
                JSONObject jRecord = new JSONObject(record);
                mysqlDatabase.executeUpdate("INSERT INTO table_category" + Util.buildInsertTableCategory(jRecord)
                        + " ON DUPLICATE KEY UPDATE " + Util.buildUpdateTableCategory(jRecord, jRecord.getString("id")));
            }
            List<String> mysqlData = mysqlDatabase.executeQuery("select * from `table_category`");
            int i = 1;
            for (String mysql:mysqlData){
                JSONObject jRecord = new JSONObject(mysql);
                couchbaseDatabase.setBucket("getting-started-db");
                List<String> cb = couchbaseDatabase.query(" WHERE id =\""+jRecord.getString("id")+"\"");
                if(cb == null || cb.isEmpty()) {
                    mysqlDatabase.executeUpdate("DELETE FROM table_category WHERE id = \"" + jRecord.getString("id") + "\"");
                    System.out.println("Deleted");
                }
            }
        } finally {
            mysqlDatabase.close();
            couchbaseDatabase.disconnect();
        }
    }
}
