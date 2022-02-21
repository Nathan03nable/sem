package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args) throws SQLException {
        /*
        App a = new App();
        databaseConnectionImpl db = new databaseConnectionImpl();
        List<Map<String, Object>> result = db.executeSQLStatement("select * from city where name like 'A%' order by CountryCode asc");

        for (int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).toString());
        }
        */

        DatabaseConnection db = DatabaseConnectionImpl.getInstance();
        ReportLanguage languageReport = new ReportLanguage(db);
        System.out.println(languageReport.generateReport("some string that doesn't do anything yet"));

    }
}