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
    String stmt = "SELECT SUM(DISTINCT(country.population)) AS 'World Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(databaseConnection);
    System.out.println(languageReport.generateReport(stmt));
  }

  public void continentPopulation(){
    IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
    String stmt = "SELECT country.Continent,"
            + "SUM(DISTINCT(country.population)) AS 'Continent Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.Continent LIKE 'Europe' "
            + "Group BY country.Continent;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(databaseConnection);
    System.out.println(languageReport.generateReport(stmt));
  }

  public void regionPopulation() {
    IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
    String stmt = "SELECT country.region, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.region LIKE 'Caribbean' "
            + "Group BY country.region;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(databaseConnection);
    System.out.println(languageReport.generateReport(stmt));
  }

  public void countryPopulation() {
    IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
    String stmt = "SELECT country.name, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.name LIKE 'United Kingdom' "
            + "Group BY country.code;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(databaseConnection);
    System.out.println(languageReport.generateReport(stmt));
  }

  public void districtPopulation() {
    IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
    String stmt = "SELECT city.district, "
            + "SUM(DISTINCT(city.population)) AS 'District Population' "
            + "FROM city "
            + "WHERE city.district LIKE 'Michigan' "
            + "Group BY city.district;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(databaseConnection);
    System.out.println(languageReport.generateReport(stmt));
  }

  public void cityPopulation() {
    IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
    String stmt = "SELECT city.name, "
            + "SUM(DISTINCT(city.population)) AS 'City Population' "
            + "FROM city "
            + "WHERE city.name LIKE 'Edinburgh' "
            + "Group BY city.name;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(databaseConnection);
    System.out.println(languageReport.generateReport(stmt));
  }

}
