package com.napier.sem.unit_tests.cities;

import com.napier.sem.SqlManager;
import com.napier.sem.cities.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityTest {

  private City subject;

  @Mock
  private SqlManager sqlManager;

  @BeforeEach
  void init(){
    MockitoAnnotations.initMocks(this);
    subject = new City(sqlManager);
  }

  @Test
  void worldCitiesTest()
  {
    String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "ORDER BY Population DESC;";

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.worldCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void continentCitiesTest()
  {
    String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE continent = 'Africa' "
            + "ORDER BY population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.continentCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void regionCitiesTest()
  {
    String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE region = 'Middle East' "
            + "ORDER BY population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.regionCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void countryCitiesTest()
  {
    String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.Name = 'Norway' "
            + "ORDER BY city.Population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.countryCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void districtCitiesTest()
  {
    String stmt = "SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE city.district = 'Fujian'"
            + "ORDER BY city.population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.districtCities();
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }
}
