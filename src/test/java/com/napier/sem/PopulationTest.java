package com.napier.sem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class PopulationTest {

  static IDatabaseConnection databaseConnection;

  @BeforeAll
  static void init(){
    databaseConnection = DatabaseConnectionImpl.getInstance();
  }

  @Test
  void worldPopulationTest()
  {
    String stmt = "SELECT SUM(DISTINCT(country.population)) AS 'World Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code;";

    String result = "{World Population=6078547450, Cities Population=1429559884, Cities Population%=23.5181, Rural Population=4648987566, Rural Population%=76.4819}";

    assertEquals(result, databaseConnection.executeSQLStatement(stmt));
  }

  @Test
  void continentPopulationTest()
  {
    String stmt = "SELECT country.Continent,"
        + "SUM(DISTINCT(country.population)) AS 'Continent Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "WHERE country.Continent LIKE 'Europe' "
        + "Group BY country.Continent;";

    String result = "{Continent=Europe, Continent Population=730074600, Cities Population=241942813, Cities Population%=33.1395, Rural Population=488131787, Rural Population%=66.8605}";

    assertEquals(result, databaseConnection.executeSQLStatement(stmt));
  }

  @Test
  void regionPopulationTest()
  {
    String stmt = "SELECT country.region, "
        + "SUM(DISTINCT(country.population)) AS 'Region Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "WHERE country.region LIKE 'Caribbean' "
        + "Group BY country.region;";

    String result = "{Region=Caribbean, Region Population=38102000, Cities Population=11067550, Cities Population%=29.0472, Rural Population=27034450, Rural Population%=70.9528}";

    assertEquals(result, databaseConnection.executeSQLStatement(stmt));
  }

  @Test
  void countryPopulation()
  {
    String stmt = "SELECT country.name, "
        + "SUM(DISTINCT(country.population)) AS 'Region Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "WHERE country.name LIKE 'United Kingdom' "
        + "Group BY country.code;";

    String result = "{Name=United Kingdom, Region Population=59623400, Cities Population=22436673, Cities Population%=37.6307, Rural Population=37186727, Rural Population%=62.3693}";

    assertEquals(result, databaseConnection.executeSQLStatement(stmt));
  }

  @Test
  void districtPopulationTest()
  {
    String stmt = "SELECT city.district, "
        + "SUM(DISTINCT(city.population)) AS 'District Population' "
        + "FROM city "
        + "WHERE city.district LIKE 'Michigan' "
        + "Group BY city.district;";

    String result = "{District=Michigan, District Population=1870428}";

    assertEquals(result, databaseConnection.executeSQLStatement(stmt));
  }

  @Test
  void cityPopulationTest()
  {
    String stmt = "SELECT city.name, "
        + "SUM(DISTINCT(city.population)) AS 'City Population' "
        + "FROM city "
        + "WHERE city.name LIKE 'Edinburgh' "
        + "Group BY city.name;";


    String result = "{Name=Edinburgh, City Population=450180}";

    assertEquals(result, databaseConnection.executeSQLStatement(stmt));
  }
}
