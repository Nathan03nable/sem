package com.napier.sem.cities;
import com.napier.sem.IDatabaseConnection;

public class City {
    IDatabaseConnection databaseConnection;

    public City(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public String worldCities(){
        String stmt = "SELECT Name, country, district, population, capital "
                + "FROM city "
                + "ORDER BY population DESC;";
        return databaseConnection.executeSQLStatement(stmt);
    }
}



