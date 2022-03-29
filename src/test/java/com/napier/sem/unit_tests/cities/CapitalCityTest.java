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

  @Mock
  private SqlManager sqlManager;

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
    subject = new CapitalCity(sqlManager);
  }

  @Test
  void worldCapitalCitiesTest() {
    String stmt = "SELECT city.Name AS City, country.name AS Country, District, city.Population "
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
    String stmt = "SELECT city.Name AS City, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE country.capital = city.id AND country.continent = 'Africa' "
        + "ORDER BY city.population DESC;";

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.continentCapitalCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void regionCapitalCitiesTest() {
    String stmt = "SELECT city.Name AS City, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE country.capital = city.id AND country.region = 'Middle East' "
        + "ORDER BY city.population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.regionCapitalCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }
}
