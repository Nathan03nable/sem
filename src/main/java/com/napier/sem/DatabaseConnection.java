package com.napier.sem;

public interface DatabaseConnection {

    String executeSQLStatement(String sqlStatement);

    void disconnect();
}
