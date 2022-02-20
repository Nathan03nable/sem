package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        App a = new App();

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