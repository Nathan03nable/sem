package com.napier.sem;

public class App
{
    public static void main(String[] args) {

        DatabaseConnection db = DatabaseConnectionImpl.getInstance();
        String stmt = "Select * from city where name like 'Q%' order by name desc";
        ReportLanguageImpl languageReport = new ReportLanguageImpl(db);
        System.out.println(languageReport.generateReport(stmt));

        String stmt2 = "Select * from city where name like 'Z%' order by name desc";
        System.out.println(languageReport.generateReport(stmt2));

        db.disconnect();
    }
}