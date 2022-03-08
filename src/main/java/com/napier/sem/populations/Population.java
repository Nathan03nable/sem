package com.napier.sem.populations;

import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import com.napier.sem.ReportLanguageImpl;

public class Population {
  IDatabaseConnection dbConnection;

  public Population(IDatabaseConnection dbConnection){
    this.dbConnection = dbConnection;
  }

  public void worldPopulation(){
    IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
    String stmt = "SELECT SUM(DISTINCT(country.population)) AS 'World Population ',"
        + "sum(city.population) AS 'Cities Population ',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population% ',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population ',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population% '"
        + "FROM country JOIN city ON CountryCode = Code;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(databaseConnection);
    System.out.println(languageReport.generateReport(stmt));
  }
}
