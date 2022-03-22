package com.napier.sem.cities;

import com.napier.sem.SqlManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityTest {

  private City subject;

  @Mock
  private SqlManager sqlManager;

  @BeforeEach
  public void init(){
    MockitoAnnotations.initMocks(this);
    subject = new City(sqlManager);
  }

  @Test
  void worldCitiesTest()
  {
    String stmt = "SELECT Name, CountryCode, District, Population "
            + "FROM city "
            + "ORDER BY Population DESC;";

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.worldCities();
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void continentCitiesTest()
  {
    String stmt = "SELECT name, code, continent, region, population, capital "
            + "FROM country "
            + "WHERE continent = 'Africa'"
            + "ORDER BY population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.continentCities();
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void regionCitiesTest()
  {
    String stmt = "SELECT name, code, continent, region, population, capital "
            + "FROM country "
            + "WHERE region = 'Middle East'"
            + "ORDER BY population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.regionCities();
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void countryCitiesTest()
  {
    String stmt = "SELECT city.Name, country.Name AS Country, District, city.Population "
            + "FROM city "
            + "JOIN country ON Code = CountryCode "
            + "WHERE country.Name = 'Norway' "
            + "ORDER BY city.Population DESC;";

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.countryCities();
    assertEquals(sqlManager.executeStatement(stmt), result);
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
    assertEquals(sqlManager.executeStatement(stmt), result);
  }
}
