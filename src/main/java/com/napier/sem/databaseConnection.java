package com.napier.sem;

import java.sql.ResultSet;

public interface databaseConnection {

    //TODO: don't return a ResultSet, return a map(?) instead
    ResultSet executeSQLStatement(String sqlStatement);
}
