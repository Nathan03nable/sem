package com.napier.sem.cities;
import com.napier.sem.SqlManager;

public class City {
    SqlManager sqlManager;

    public City(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public String worldCities(){
        String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "ORDER BY Population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    public String continentCities(String continent){
        String stmt = String.format("SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE continent = %s "
                + "ORDER BY population DESC;", continent);

        return sqlManager.executeStatement(stmt);
    }

    public String regionCities(String region){
        String stmt = String.format("SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE region = %s "
                + "ORDER BY population DESC;", region);

        return sqlManager.executeStatement(stmt);
    }

    public String countryCities(String country){
        String stmt = String.format("SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.name = %s "
                + "ORDER BY city.Population DESC;", country);

        return sqlManager.executeStatement(stmt);
    }

    public String districtCities(String district){
        String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE city.district = %s "
        + "ORDER BY city.population DESC;", district);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCitiesInAContinent(String continent, String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.continent = %s "
                + "ORDER BY city.population DESC LIMIT %s;", continent, limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCitiesInACountry(String country, String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
                        + "FROM city JOIN country ON (country.code=city.countrycode) "
                        + "WHERE country.name = %s "
                        + "ORDER BY city.population DESC LIMIT %s;", country, limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCitiesInARegion(String region, String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.region = %s "
                + "ORDER BY city.population DESC LIMIT %s;", region, limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCitiesInADistrict(String district, String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE city.district = %s "
                + "ORDER BY city.population DESC LIMIT %s;", district, limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCitiesInTheWorld(String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "ORDER BY city.population DESC LIMIT %s;", limit);

        return sqlManager.executeStatement(stmt);
    }
}
