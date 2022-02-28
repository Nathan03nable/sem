package com.napier.sem;

public class ReportLanguageImpl implements ReportGenerator{

    DatabaseConnection dbConnection;

    public ReportLanguageImpl(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    @Override
    public String generateReport(String sqlStatement){

        return dbConnection.executeSQLStatement(sqlStatement);

    }
}
