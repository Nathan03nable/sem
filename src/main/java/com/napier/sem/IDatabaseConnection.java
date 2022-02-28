package com.napier.sem;

public interface IDatabaseConnection {

    String executeSQLStatement(String sqlStatement);

    void disconnect();
}
