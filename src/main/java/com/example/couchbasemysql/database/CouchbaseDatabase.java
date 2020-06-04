package com.example.couchbasemysql.database;



import com.couchbase.client.java.json.JsonObject;

import java.util.List;

public interface CouchbaseDatabase {



    public void setBucket(String bucketName);

    public List<String> query(String query, JsonObject jo);

    public void disconnect();
}
