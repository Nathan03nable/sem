package com.napier.sem.populations;

import com.napier.sem.IDatabaseConnection;

public class Population {
  IDatabaseConnection databaseConnection;

  public Population(IDatabaseConnection databaseConnection){
    this.databaseConnection = databaseConnection;
  }

  public void worldPopulation(){
    String stmt = "SELECT SUM(DISTINCT(country.population)) AS 'World Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code;";

    System.out.println(databaseConnection.executeSQLStatement(stmt));
  }

  public void continentPopulation(){
    String stmt = "SELECT country.Continent,"
            + "SUM(DISTINCT(country.population)) AS 'Continent Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.Continent LIKE 'Europe' "
            + "Group BY country.Continent;";

    System.out.println(databaseConnection.executeSQLStatement(stmt));
  }

  public void regionPopulation() {
    String stmt = "SELECT country.region, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.region LIKE 'Caribbean' "
            + "Group BY country.region;";

    System.out.println(databaseConnection.executeSQLStatement(stmt));
  }

  public void countryPopulation() {
    String stmt = "SELECT country.name, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.name LIKE 'United Kingdom' "
            + "Group BY country.code;";

    System.out.println(databaseConnection.executeSQLStatement(stmt));
  }

  public void districtPopulation() {
    String stmt = "SELECT city.district, "
            + "SUM(DISTINCT(city.population)) AS 'District Population' "
            + "FROM city "
            + "WHERE city.district LIKE 'Michigan' "
            + "Group BY city.district;";

    System.out.println(databaseConnection.executeSQLStatement(stmt));
  }

  public void cityPopulation() {
    String stmt = "SELECT city.name, "
            + "SUM(DISTINCT(city.population)) AS 'City Population' "
            + "FROM city "
            + "WHERE city.name LIKE 'Edinburgh' "
            + "Group BY city.name;";

    System.out.println(databaseConnection.executeSQLStatement(stmt));
  }
}
