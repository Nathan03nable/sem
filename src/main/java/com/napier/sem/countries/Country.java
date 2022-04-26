package com.napier.sem.countries;

import com.napier.sem.SqlManager;

public class Country {

    SqlManager sqlManager;

    public Country(SqlManager sqlManager){
        this.sqlManager = sqlManager;
    }

    public String worldCountries(){
        String stmt = "SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC";

        return sqlManager.executeStatement(stmt);
    }

    public String continentCountries(String continent){
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE continent = %s ORDER BY population DESC;", continent);

        return sqlManager.executeStatement(stmt);
    }

    public String regionCountries(String region){
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE region = %s ORDER BY population DESC;", region);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCountries(String limit){
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC LIMIT %s", limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedContinentCountries(String continent, String limit){
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE continent = %s ORDER BY population DESC LIMIT %s", continent, limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedRegionalCountries(String region, String limit){
        String stmt = String.format("SELECT name, code, continent, region, population, capital FROM country WHERE region = %s ORDER BY population DESC LIMIT %s", region, limit);

        return sqlManager.executeStatement(stmt);
    }

}
