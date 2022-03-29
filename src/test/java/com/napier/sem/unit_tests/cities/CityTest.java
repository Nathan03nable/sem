package com.napier.sem.unit_tests.cities;

import com.napier.sem.SqlManager;
import com.napier.sem.cities.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityTest {

  private City subject;
  String limit = "5";
  String continent = "Middle East";
  String region = "Europe";
  String country = "France";

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

  @Test
  void topNPopulatedCitiesTest() {
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "ORDER BY city.population DESC %s", limit);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCities(limit);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void topNPopulatedCitiesTesInAContinent() {
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population, "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.Continent = 'Africa' "
            + "ORDER BY city.population DESC %s", limit);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCitiesInAContinent(limit);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void topNPopulatedCitiesTesInARegion() {
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.Region = 'Middle East'"
            + "ORDER BY city.population DESC %s", limit);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCitiesInARegion(limit);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

  @Test
  void topNPopulatedCitiesInACountry() {
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.Name = 'Norway, "
            + "ORDER BY city.population DESC %s", limit);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCitiesInACountry(limit);
    assertEquals(sqlManager.executeStatement(stmt), result);
  }

}
