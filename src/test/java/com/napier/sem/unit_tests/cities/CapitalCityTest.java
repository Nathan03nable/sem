package com.napier.sem.unit_tests.cities;

import com.napier.sem.SqlManager;
import com.napier.sem.cities.CapitalCity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalCityTest {

  private CapitalCity subject;
  static final String limit = "5";
  static final String continent = "'Africa'";
  static final String country = "'Spain'";
  static final String district = "'Fujian'";
  static final String region = "'Middle East'";

  @Mock
  private SqlManager sqlManager;

  private static final String LIMIT = "5";

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
    subject = new CapitalCity(sqlManager);
  }

  @Test
  void topNPopulatedCapitalCitiesInTheWorldTest(){
    String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.capital = city.id "
            + "ORDER BY city.population DESC LIMIT %s;", limit);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCapitalCitiesInTheWorld(limit);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void topNPopulatedCapitalCitiesInAContinent(){
    String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.capital = city.id AND country.continent = '%s' "
            + "ORDER BY city.population DESC LIMIT %s;", continent, limit);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCapitalCitiesInAContinent(continent, limit);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void topNPopulatedCapitalCitiesInARegion(){
    String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.capital = city.id AND country.region = '%s' "
            + "ORDER BY city.population DESC LIMIT %s;", region, limit);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCapitalCitiesInARegion(region, limit);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void worldCapitalCitiesTest() {
    String stmt = "SELECT city.Name, country.name AS Country, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.capital = city.id "
            + "ORDER BY city.population DESC;";

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.worldCapitalCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void continentCapitalCitiesTest() {
    String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.capital = city.id AND country.continent = '%s' "
            + "ORDER BY city.population DESC;", continent);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.continentCapitalCities(continent);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void regionCapitalCitiesTest() {
    String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.capital = city.id AND country.region = '%s' "
            + "ORDER BY city.population DESC;", region);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.regionCapitalCities(region);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }
}
