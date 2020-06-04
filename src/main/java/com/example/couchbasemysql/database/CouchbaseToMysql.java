package com.example.couchbasemysql.database;

import java.util.List;

public interface CouchbaseToMysql {

    public void couchbaseToMysql();
    void upsertTablesToMysql(List<String> data);
    void upsertCategoryToMysql(List<String> data);
    void upsertGroupToMysql(List<String> data);
    void upsertItemToMysql(List<String> data);
    void upsertOrderToMysql(List<String> data);
    void upsertTablesCategoryToMysql(List<String> data);
}
