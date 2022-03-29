package com.napier.sem.unit_tests.countries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.napier.sem.SqlManager;
import com.napier.sem.countries.Country;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CountryTest {

    String limit = "5";
    private Country subject;

    @Mock
    private SqlManager sqlManager;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        subject = new Country(sqlManager);
    }

    @Test
    void worldCountriesTest() {
        String stmt = "SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC";

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.worldCountries();
        assertEquals(sqlManager.executeStatement(stmt), result);
    }

    @Test
    void continentCountriesTest() {
        String stmt = "SELECT name, code, continent, region, population, capital FROM country WHERE continent = 'Africa' ORDER BY population DESC";

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.continentCountries();
        assertEquals(sqlManager.executeStatement(stmt), result);
    }

    @Test
    void regionCountriesTest() {
        String stmt = "SELECT name, code, continent, region, population, capital FROM country WHERE region = 'Middle East' ORDER BY population DESC";

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.regionCountries();
        assertEquals(sqlManager.executeStatement(stmt), result);
    }

    @Test
    void topNPopulatedCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC LIMIT %s", limit);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.topNPopulatedCountries(limit);
        assertEquals(sqlManager.executeStatement(stmt), result);
    }

    @Test
    void topNPopulatedContinentCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE continent = 'Africa' ORDER BY population DESC LIMIT %s", limit);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.topNPopulatedContinentCountries(limit);
        assertEquals(sqlManager.executeStatement(stmt), result);
    }

    @Test
    void topNPopulatedRegionalCountriesTest() {
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE region = 'Middle East' ORDER BY population DESC LIMIT %s", limit);

        String expected = "String returned";

        Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

        String result = subject.topNPopulatedRegionalCountries(limit);
        assertEquals(sqlManager.executeStatement(stmt), result);
    }
}
