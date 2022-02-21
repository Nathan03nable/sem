package com.napier.sem;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface databaseConnection {

    //TODO: don't return a ResultSet, return a map(?) instead
    List<Map<String, Object>> executeSQLStatement(String sqlStatement);
}
