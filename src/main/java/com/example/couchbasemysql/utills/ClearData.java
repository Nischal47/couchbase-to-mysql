package com.example.couchbasemysql.utills;

import com.example.couchbasemysql.database.implementation.MysqlDatabaseImplementation;

public class ClearData {

    public void mysqlDataClear(){
        MysqlDatabaseImplementation mysqlDatabase = null;
        try {
            mysqlDatabase = new MysqlDatabaseImplementation();
            mysqlDatabase.executeQuery("DELETE FROM ...");
        }finally {
            mysqlDatabase.close();
        }
    }
}
