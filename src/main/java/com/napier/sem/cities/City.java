package com.napier.sem.cities;
import com.napier.sem.SqlManager;

public class City {
    SqlManager sqlManager;

    public City(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public String worldCities(){
        String stmt = "SELECT Name, CountryCode, District, Population "
                + "FROM city "
                + "ORDER BY Population DESC;";

        return sqlManager.executeStatement(stmt);
    }
}



