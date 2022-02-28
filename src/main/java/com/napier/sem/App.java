package com.napier.sem;

public class App
{
    public static void main(String[] args) {

        IDatabaseConnection db = DatabaseConnectionImpl.getInstance();
        String stmt = "Select * from city where name like 'V%' order by name desc";
        ReportLanguageImpl languageReport = new ReportLanguageImpl(db);
        System.out.println(languageReport.generateReport(stmt));

        String stmt2 = "Select * from city where name like 'Y%' order by name desc";
        System.out.println(languageReport.generateReport(stmt2));

        db.disconnect();
    }
}