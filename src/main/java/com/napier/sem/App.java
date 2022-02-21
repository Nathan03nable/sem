package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args) throws SQLException {
        App a = new App();
        databaseConnectionImpl db = new databaseConnectionImpl();
        ResultSet rset = db.executeSQLStatement("select * from city where id='6'");

        if(rset.next())
        {
            System.out.println("resultSet has next");
            String name = rset.getString("Name");
            System.out.println(name);
        } else {
            System.out.println("empty result");
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