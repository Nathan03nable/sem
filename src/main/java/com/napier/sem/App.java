package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args) {

        DatabaseConnection db = DatabaseConnectionImpl.getInstance();
        ReportLanguageImpl languageReport = new ReportLanguageImpl(db);
        System.out.println(languageReport.generateReport("some string that doesn't do anything yet"));

    }
}