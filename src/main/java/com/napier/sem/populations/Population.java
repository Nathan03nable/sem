package com.napier.sem.populations;

import com.napier.sem.SqlManager;

public class Population {
  SqlManager sqlManager;

  public Population(SqlManager sqlManager){
    this.sqlManager = sqlManager;
  }

  public String worldPopulation(){
    String stmt = "SELECT SUM(DISTINCT(country.population)) AS 'World Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code;";

    return sqlManager.executeStatement(stmt);
  }

  public String continentPopulation(){
    String stmt = "SELECT country.Continent,"
            + "SUM(DISTINCT(country.population)) AS 'Continent Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.Continent LIKE 'Europe' "
            + "Group BY country.Continent;";

    return sqlManager.executeStatement(stmt);
  }

  public String regionPopulation() {
    String stmt = "SELECT country.region, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.region LIKE 'Caribbean' "
            + "Group BY country.region;";

    return sqlManager.executeStatement(stmt);
  }

  public String countryPopulation() {
    String stmt = "SELECT country.name, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.name LIKE 'United Kingdom' "
            + "Group BY country.code;";

    return sqlManager.executeStatement(stmt);
  }

  public String districtPopulation() {
    String stmt = "SELECT city.district, "
            + "SUM(DISTINCT(city.population)) AS 'District Population' "
            + "FROM city "
            + "WHERE city.district LIKE 'Michigan' "
            + "Group BY city.district;";

    return sqlManager.executeStatement(stmt);
  }

  public String cityPopulation() {
    String stmt = "SELECT city.name, "
            + "SUM(DISTINCT(city.population)) AS 'City Population' "
            + "FROM city "
            + "WHERE city.name LIKE 'Edinburgh' "
            + "Group BY city.name;";

    return sqlManager.executeStatement(stmt);
  }
}
