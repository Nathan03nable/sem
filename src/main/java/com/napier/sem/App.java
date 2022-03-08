package com.napier.sem;

import com.napier.sem.populations.Population;

public class App
{
    public static void main(String[] args) {

        /**

        String stmt = "Select * from city where name like 'V%' order by name desc";
        ReportLanguageImpl languageReport = new ReportLanguageImpl(db);
        System.out.println(languageReport.generateReport(stmt));

        String stmt2 = "Select * from city where name like 'Y%' order by name desc";
        System.out.println(languageReport.generateReport(stmt2));
        */

        IDatabaseConnection db = DatabaseConnectionImpl.getInstance();
        Population population = new Population(db);
        population.worldPopulation();

        db.disconnect();
    }
}