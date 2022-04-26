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
  static final String limit = "5";
  static final String continent = "'Africa'";
  static final String country = "'Spain'";
  static final String district = "'Fujian'";
  static final String region = "'Middle East'";

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
    String stmt = String.format("SELECT city.Name, country.Name AS 'Country', District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE continent = '%s' "
            + "ORDER BY population DESC;", continent);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.continentCities(continent);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void regionCitiesTest()
  {
    String stmt = String.format("SELECT city.Name, country.Name AS 'Country', District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE region = '%s' "
            + "ORDER BY population DESC;", region);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.regionCities(region);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void countryCitiesTest()
  {
    String stmt = String.format("SELECT city.Name, country.Name AS 'Country', District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.name = '%s' "
            + "ORDER BY city.Population DESC;", country);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.countryCities(country);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void districtCitiesTest()
  {
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE city.district = '%s'"
            + "ORDER BY city.population DESC;", district);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.districtCities(district);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void topNPopulatedCitiesInAContinentTest(){
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.continent = '%s' "
            + "ORDER BY city.population DESC LIMIT %s;", continent, limit);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCitiesInAContinent(continent, limit);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void topNPopulatedCitiesInARegionTest(){
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.region = '%s' "
            + "ORDER BY city.population DESC LIMIT %s;", region, limit);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCitiesInARegion(region, limit);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void topNPopulatedCitiesInACountryTest(){
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.name = '%s' "
            + "ORDER BY city.population DESC LIMIT %s;", country, limit);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCitiesInACountry(country, limit);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

  @Test
  void topNPopulatedCitiesInADistrictTest(){
    String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
            + "FROM city JOIN country ON (country.code=city.countrycode) "
            + "WHERE country.district = '%s' "
            + "ORDER BY city.population DESC LIMIT %s;", district, limit);

    String expected = "String returned";
    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.topNPopulatedCitiesInADistrict(district, limit);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }

}
