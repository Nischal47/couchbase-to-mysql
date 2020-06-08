package com.example.couchbasemysql.database.implementation;

import com.example.couchbasemysql.database.MysqlDatabase;
import com.example.couchbasemysql.utills.Util;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MysqlDatabaseImplementation implements MysqlDatabase {

    private Logger logger = LogManager.getLogger(MysqlDatabase.class);
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public MysqlDatabaseImplementation(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/restro_bytes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(dbURL, "root", "admin");
        } catch (SQLException | ClassNotFoundException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
    }


    @Override
    public List<String> executeQuery(String query) {
        List<String> data = new ArrayList<String>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            ResultSetMetaData md = resultSet.getMetaData();
            int columns = md.getColumnCount();
            HashMap<String, Object> row = null;
            Gson g = Util.createGson();

            while (resultSet.next()) {
                row = new HashMap<String, Object>();
                for (int i = 1; i <= columns; i++) {
                    row.put(md.getColumnName(i), resultSet.getObject(i));
                }
                data.add(g.toJson(row));
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
        return data;
    }

    @Override
    public int executeUpdate(String query) {
        int row = 0;
        try{
            statement = connection.createStatement();
            row = statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
        return row;
    }

    @Override
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
            logger.info("MySql Connection closed.");
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
    }

    public List<String> getMysqlData(String table_name,String id) {
        List<String> data = null;
        MysqlDatabaseImplementation mysqlDatabase = null;
        try {
            mysqlDatabase = new MysqlDatabaseImplementation();
            data = mysqlDatabase.executeQuery("select * from "+table_name+" where id = \""+id+"\"");
        } finally {
            mysqlDatabase.close();
        }
        return data;
    }
}
