package com.napier.sem.unit_tests.languages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.napier.sem.SqlManager;
import com.napier.sem.languages.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class LanguageTest {

  private static final String WORLD_POPULATION = "6078547450";
  private Language subject;

  @Mock
  private SqlManager sqlManager;

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
    subject = new Language(sqlManager);
  }

  @Test
  void languageReportsTest()
  {
    String stmt = String.format("SELECT countrylanguage.language, "
        + "CASE "
        + "WHEN countrylanguage.Language like 'Chinese' THEN SUM(country.population) "
        + "WHEN countrylanguage.Language like 'English' THEN SUM(country.population) "
        + "WHEN countrylanguage.Language like 'Hindi' THEN SUM(country.population) "
        + "WHEN countrylanguage.Language like 'Spanish' THEN SUM(country.population) "
        + "WHEN countrylanguage.Language like 'Arabic' THEN SUM(country.population) END AS Population, "
        + "SUM(population) / %s * 100 AS '%% of world population' "
        + "FROM country JOIN countrylanguage on country.Code = countrylanguage.CountryCode "
        + "WHERE countrylanguage.language like 'Chinese' "
        + "   OR countrylanguage.language like 'English' "
        + "   OR countrylanguage.language like 'Hindi' "
        + "   OR countrylanguage.language like 'Spanish' "
        + "   OR countrylanguage.language like 'Arabic' "
        + "GROUP BY countrylanguage.Language "
        + "ORDER BY SUM(country.population) DESC; ", WORLD_POPULATION);

    String expected = "String returned";

    Mockito.when(sqlManager.executeStatement(stmt)).thenReturn(expected);

    String result = subject.languagesReport(WORLD_POPULATION);
    assertEquals(sqlManager.executeStatement(stmt), result, "Should return expected string");
  }
}
