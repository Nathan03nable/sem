package com.napier.sem;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface databaseConnection {

    List<Map<String, Object>> executeSQLStatement(String sqlStatement);

    //TODO: add getInstance() method to interface?

}