package com.example.couchbasemysql.database;

import java.util.List;

public interface MysqlDatabase {
    public List<String> executeQuery(String query);

    public int executeUpdate(String query);

    public void close();
}
