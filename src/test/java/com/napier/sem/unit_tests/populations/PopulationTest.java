package com.napier.sem.unit_tests.populations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.napier.sem.population.Population;
import com.napier.sem.SqlManager;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class PopulationTest {

  private Population subject;

  @Mock
  private SqlManager sqlManager;

  private static final String CONTINENT = "'Europe'";
  private static final String REGION = "'Caribbean'";
  private static final String COUNTRY = "'Ukraine'";
  private static final String DISTRICT = "'Kabol'";
  private static final String CITY = "'Edinburgh'";

  @BeforeEach
  void init(){
    MockitoAnnotations.initMocks(this);
    subject = new Population(sqlManager);
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

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.worldPopulation();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void continentPopulationTest()
  {
    String stmt = String.format(
        "SELECT country.Continent,SUM(DISTINCT(country.population)) AS 'Continent Population',"
            + "sum(city.population) AS 'Cities Population', "
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%%', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%%' "
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.Continent LIKE %s Group BY country.Continent;", CONTINENT);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.continentPopulation(CONTINENT);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void regionPopulationTest()
  {
    String stmt = String.format(
        "SELECT country.region, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population', "
            + "sum(city.population) AS 'Cities Population', "
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%%', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population', "
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%%' "
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.region LIKE %s Group BY country.region;", REGION);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.regionPopulation(REGION);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void countryPopulationTest()
  {
    String stmt = String.format(
        "SELECT country.name, "
            + "SUM(DISTINCT(country.population)) AS 'Region Population',"
            + "sum(city.population) AS 'Cities Population',"
            + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%%',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
            + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%%'"
            + "FROM country JOIN city ON CountryCode = Code "
            + "WHERE country.name LIKE %s"
            + "Group BY country.code;", COUNTRY);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.countryPopulation(COUNTRY);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void districtPopulationTest()
  {
    String stmt = String.format(
        "SELECT city.district, "
            + "SUM(DISTINCT(city.population)) AS 'District Population' "
            + "FROM city "
            + "WHERE city.district LIKE %s "
            + "Group BY city.district;", DISTRICT);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.districtPopulation(DISTRICT);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void cityPopulationTest()
  {
    String stmt = String.format(
        "SELECT city.name, "
            + "SUM(DISTINCT(city.population)) AS 'City Population' "
            + "FROM city "
            + "WHERE city.name LIKE %s "
            + "Group BY city.name;", CITY);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.cityPopulation(CITY);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void everyContinentPopulationTest()
  {
    String stmt = "SELECT country.Continent,"
        + "SUM(DISTINCT(country.population)) AS 'Continent Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "Group BY country.Continent;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.everyContinentPopulation();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void everyRegionPopulationTest()
  {
    String stmt = "SELECT country.region, "
        + "SUM(DISTINCT(country.population)) AS 'Region Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "Group BY country.region;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.everyRegionPopulation();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void everyCountryPopulationTest()
  {
    String stmt = "SELECT country.name, "
        + "SUM(DISTINCT(country.population)) AS 'Region Population',"
        + "sum(city.population) AS 'Cities Population',"
        + "(sum(city.population) / SUM(DISTINCT(country.population))) * 100 AS 'Cities Population%',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) AS 'Rural Population',"
        + "(SUM(DISTINCT(country.population)) - sum(city.population)) / SUM(DISTINCT(country.population)) * 100 AS 'Rural Population%'"
        + "FROM country JOIN city ON CountryCode = Code "
        + "Group BY country.code;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.everyCountryPopulation();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void TestExtractPopulation(){
    String worldPopulationQuery = "{World Population=6078547450, Cities Population=1429559884, Cities Population%=23.5181, Rural Population=4648987566, Rural Population%=76.4819}";

    String expectedWorldPopulation = "6078547450";
    String resutlt = subject.extractWorldPopulation(worldPopulationQuery);

    assertEquals(expectedWorldPopulation, resutlt);
  }
}
