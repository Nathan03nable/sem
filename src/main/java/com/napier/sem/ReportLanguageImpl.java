package com.napier.sem;

public class ReportLanguageImpl implements ReportGenerator{

    DatabaseConnection dbConnection;

    public ReportLanguageImpl(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    @Override
    public String generateReport(String sqlStatement){

        return dbConnection.executeSQLStatement(sqlStatement);

        /*
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++){
            sb.append(result.get(i).toString()+ "\n");
        }
        return sb.toString();
         */
    }
}
