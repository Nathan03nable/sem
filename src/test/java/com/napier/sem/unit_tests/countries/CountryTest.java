package com.napier.sem.unit_tests.countries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.napier.sem.SqlManager;
import com.napier.sem.countries.Country;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CountryTest {

    private Country subject;
    static final String limit = "5";
    static final String continent = "'Africa'";
    static final String country = "'Spain'";
    static final String district = "'Fujian'";
    static final String region = "'Middle East'";

    @Mock
    private SqlManager sqlManager;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        subject = new Country(sqlManager);
    }

    @Test
    void worldCountriesTest() {
        String stmt = "SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC";

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.worldCountries();
        assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
    }

    @Test
    void continentCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE continent = '%s' ORDER BY population DESC;", continent);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.continentCountries(continent);
        assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
    }

    @Test
    void regionCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE region = '%s' ORDER BY population DESC;", region);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.regionCountries(region);
        assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
    }

    @Test
    void topNPopulatedCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC LIMIT %s", limit);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.topNPopulatedCountries(limit);
        assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
    }

    @Test
    void topNPopulatedContinentCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE continent = '%s' ORDER BY population DESC LIMIT %s", continent, limit);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.topNPopulatedContinentCountries(continent, limit);
        assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
    }

    @Test
    void topNPopulatedRegionalCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE region = '%s' ORDER BY population DESC LIMIT %s", region, limit);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.topNPopulatedRegionalCountries(region, limit);
        assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
    }
}
