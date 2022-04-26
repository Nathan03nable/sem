package com.napier.sem;

public interface IDatabaseConnection {

    String executeSQLStatement(String sqlStatement);

    void disconnect();

    void checkForSqlDriver(String fakeClass);

    boolean tryToConnect(int i);

    void connect();
}
