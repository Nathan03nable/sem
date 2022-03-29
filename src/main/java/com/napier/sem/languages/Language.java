package com.napier.sem.languages;

import com.napier.sem.SqlManager;

public class Language {
  SqlManager sqlManager;

  public Language(SqlManager sqlManager){
    this.sqlManager = sqlManager;
  }

  public String languagesReport(String worldPopulation){
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
        + "ORDER BY SUM(country.population) DESC; ", worldPopulation);

    return sqlManager.executeStatement(stmt);
  }
}
