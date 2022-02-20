package com.napier.sem;

import java.sql.ResultSet;

public interface databaseConnection {

    ResultSet executeSQLStatement(String sqlStatement);
}
