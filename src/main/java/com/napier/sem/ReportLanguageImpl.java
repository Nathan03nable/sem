package com.napier.sem;

public class ReportLanguageImpl implements ReportGenerator{

    IDatabaseConnection dbConnection;

    public ReportLanguageImpl(IDatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    @Override
    public String generateReport(String sqlStatement){

        return dbConnection.executeSQLStatement(sqlStatement);

    }
}
