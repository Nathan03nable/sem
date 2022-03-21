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

    public String continentCountries(){
        String stmt = "SELECT name, code, continent, region, population, capital FROM country WHERE continent = 'Africa' ORDER BY population DESC";

        return sqlManager.executeStatement(stmt);
    }

    public String regionCountries(){
        String stmt = "SELECT name, code, continent, region, population, capital FROM country WHERE region = 'Middle East' ORDER BY population DESC";

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCountries(int n){
        String stmt = "SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC LIMIT <nHolder>";
        stmt = stmt.replace("<nHolder>", String.valueOf(n));
        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedContinentCountries(int n){
        String stmt = "SELECT name, code, continent, region, population, capital FROM country WHERE continent = 'Africa' ORDER BY population DESC LIMIT <nHolder>";
        stmt = stmt.replace("<nHolder>", String.valueOf(n));

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedRegionalCountries(int n){
        String stmt = "SELECT name, code, continent, region, population, capital FROM country WHERE region = 'Middle East' ORDER BY population DESC LIMIT <nHolder>";
        stmt = stmt.replace("<nHolder>", String.valueOf(n));
        return sqlManager.executeStatement(stmt);
    }

}
