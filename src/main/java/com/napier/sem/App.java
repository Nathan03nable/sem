package com.napier.sem;

import com.napier.sem.cities.CapitalCity;
import com.napier.sem.cities.City;
import com.napier.sem.countries.Country;
import com.napier.sem.populations.Population;

public class App
{
    static final int limit = 5;
  
    public static void main(String[] args) {
        String location;
        if(args.length < 1){
            location = "localhost:33060";
        }else{
            location = args[0];
        }
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location);

        SqlManager sqlManager = new SqlManager(databaseConnection);

        CapitalCity capitalCity = new CapitalCity(sqlManager);
      
        System.out.println(capitalCity.worldCapitalCities());
        System.out.println(capitalCity.continentCapitalCities());
        System.out.println(capitalCity.regionCapitalCities());


        databaseConnection.disconnect();
    }
}