package com.napier.sem;

public class SqlManager {
    IDatabaseConnection databaseConnection;

    public SqlManager(IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public String executeStatement(String statement){
        return databaseConnection.executeSQLStatement(statement);
    }
}
