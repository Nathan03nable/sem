package com.napier.sem;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class App
{
    public static void main(String[] args) throws SQLException {
        App a = new App();
        databaseConnectionImpl db = new databaseConnectionImpl();
        List<Map<String, Object>> result = db.executeSQLStatement("select * from city where name like 'A%' order by CountryCode asc");

        for (int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).toString());
        }


        //a.connect();

        //CityDto cityDto = a.getACity(a.connection, 2);

        //System.out.println(cityDto.toString());

        //a.disconnect();
    }

    private CityDto getACity(Connection con, int id){
        City city = new City();
        return city.getCityById(con, id);
    }



}