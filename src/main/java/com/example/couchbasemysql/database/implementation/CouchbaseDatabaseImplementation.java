package com.example.couchbasemysql.database.implementation;



import com.couchbase.client.core.msg.query.QueryRequest;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;


import java.util.ArrayList;
import java.util.List;

public class CouchbaseDatabaseImplementation implements com.example.couchbasemysql.database.CouchbaseDatabase {

    private Cluster cluster = null;
    private Bucket bucket = null;
    private String bucketName;
    private Collection collection;


    @Override
    public void setBucket(String bucketName) {
        cluster = Cluster.connect("127.0.0.2", "getting-started-db", "Nepal!23");
        this.bucketName = bucketName;
        bucket = cluster.bucket(bucketName);
        collection = bucket.defaultCollection();
    }

    @Override
    public List<String> query(String query, JsonObject jsonObject) {
        List<String> data = new ArrayList<>();
        QueryResult result = cluster.query("SELECT * FROM `" + bucketName + "`" + query, QueryOptions.queryOptions().parameters(jsonObject));

        for(JsonObject row : result.rowsAsObject()){
            data.add(row.get(bucketName).toString());
        }
        return data;
    }

    public List<String> query(String query) {
        List<String> data = new ArrayList<>();
        QueryResult result = cluster.query("SELECT * FROM `" + bucketName + "`" + query);

        for(JsonObject row : result.rowsAsObject()){
            data.add(row.get(bucketName).toString());
        }
        return data;
    }


    @Override
    public void disconnect() {
        if(cluster != null){
            cluster.disconnect();
        }
    }

    public List<String> getCouchbaseData(String id){
        List<String> data = null;
        CouchbaseDatabaseImplementation couchbaseDatabase = new CouchbaseDatabaseImplementation();
        couchbaseDatabase.setBucket("getting-started-db");
        data = couchbaseDatabase.query(" WHERE type = \"table\"",JsonObject.create().put("id",id));
        return data;
    }
}
