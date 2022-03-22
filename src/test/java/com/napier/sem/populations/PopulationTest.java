package com.napier.sem.populations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.napier.sem.SqlManager;
import com.napier.sem.populations.Population;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PopulationTest {

  private Population subject;

  @Mock
  private SqlManager sqlManager;

  private static final String CONTINENT = "Africa";
  private static final String REGION = "Caribbean";
  private static final String COUNTRY = "Ukraine";
  private static final String DISTRICT = "Kabol";
  private static final String CITY = "Edinburgh";

  @BeforeEach
  public void init(){
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
    assertEquals(sqlManager.executeStatement(stmt), result);
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

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.continentPopulation(CONTINENT);
    assertEquals(sqlManager.executeStatement(stmt), result);
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

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.regionPopulation(REGION);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void countryPopulationTest()
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

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.countryPopulation(COUNTRY);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void districtPopulationTest()
  {
    String stmt = "SELECT city.district, "
        + "SUM(DISTINCT(city.population)) AS 'District Population' "
        + "FROM city "
        + "WHERE city.district LIKE 'Michigan' "
        + "Group BY city.district;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.districtPopulation(DISTRICT);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void cityPopulationTest()
  {
    String stmt = "SELECT city.name, "
        + "SUM(DISTINCT(city.population)) AS 'City Population' "
        + "FROM city "
        + "WHERE city.name LIKE 'Edinburgh' "
        + "Group BY city.name;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.cityPopulation(CITY);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }
}
