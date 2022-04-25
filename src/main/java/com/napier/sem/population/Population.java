package com.napier.sem.population;

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

  public String continentPopulation(String continent){
    String stmt = String.format(
        "SELECT country.Continent,SUM(DISTINCT(country.population)) AS 'Continent Population',"
            + "sum(city.population) AS 'Cities Population', "
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%%', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%%' "
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.Continent LIKE %s Group BY country.Continent;", continent);

    return sqlManager.executeStatement(stmt);
  }

  public String regionPopulation(String region) {
    String stmt = String.format(
        "SELECT country.region, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population', "
            + "sum(city.population) AS 'Cities Population', "
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%%', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%%' "
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.region LIKE %s Group BY country.region;", region);

    return sqlManager.executeStatement(stmt);
  }

  public String countryPopulation(String country) {
    String stmt = String.format(
        "SELECT country.name, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.name LIKE %s"
            + "Group BY country.code;", country);

    return sqlManager.executeStatement(stmt);
  }

  public String districtPopulation(String district) {
    String stmt = String.format(
        "SELECT city.district, "
            + "SUM(DISTINCT(city.population)) AS 'District Population' "
            + "FROM city "
            + "WHERE city.district LIKE %s "
            + "Group BY city.district;", district);

    return sqlManager.executeStatement(stmt);
  }

  public String cityPopulation(String city) {
    String stmt = String.format(
        "SELECT city.name, "
            + "SUM(DISTINCT(city.population)) AS 'City Population' "
            + "FROM city "
            + "WHERE city.name LIKE %s "
            + "Group BY city.name;", city);

    return sqlManager.executeStatement(stmt);
  }

  public String everyContinentPopulation(){
    String stmt = "SELECT country.Continent,"
        + "SUM(DISTINCT(country.population)) AS 'Continent Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "Group BY country.Continent;";

    return sqlManager.executeStatement(stmt);
  }

  public String everyRegionPopulation() {
    String stmt = "SELECT country.region, "
        + "SUM(DISTINCT(country.population)) AS 'Region Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "Group BY country.region;";

    return sqlManager.executeStatement(stmt);
  }

  public String everyCountryPopulation() {
    String stmt = "SELECT country.name, "
        + "SUM(DISTINCT(country.population)) AS 'Region Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "Group BY country.code;";

    return sqlManager.executeStatement(stmt);
  }

  public String extractWorldPopulation(String worldPopulationQueryResult){
    String firstSplit = worldPopulationQueryResult.split("World Population=")[1];
    return firstSplit.split(",")[0];
  }
}
