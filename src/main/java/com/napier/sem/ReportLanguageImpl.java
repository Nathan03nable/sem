package com.napier.sem;

import java.util.List;
import java.util.Map;

public class ReportLanguageImpl implements ReportGenerator{

    DatabaseConnection dbConnection;

    public ReportLanguageImpl(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    @Override
    public String constructSqlStatement(){
        return "some string";
    }

    @Override
    public String generateReport(String sqlStatement){
        String stmt = "Select * from city where name like 'D%' order by name desc";
        List<Map<String, Object>> result = dbConnection.executeSQLStatement(stmt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++){
            sb.append(result.get(i).toString()+ "\n");
        }
        return sb.toString();
    }
}
