package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class City {

  public CityDto getCityById(Connection connection, int id)
  {
    try
    {
      return returnCityIfExists(connection, id);
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
      System.out.println("Failed to get City details");
      return null;
    }
  }

  private CityDto returnCityIfExists(Connection connection, int id) throws SQLException {
    ResultSet resultSet = createAndExecuteSqlStatement(connection, id);

    if (resultSet.next())
    {
      return getCityFromDatabase(resultSet);
    }
    else{
      return null;
    }
  }

  private ResultSet createAndExecuteSqlStatement(Connection connection, int id) throws SQLException {
    City city = new City();
    Statement stmt = connection.createStatement();
    String sqlString = city.createSqlString(id);
    return stmt.executeQuery(sqlString);
  }

  public String createSqlString(int id) {
    return "SELECT * " + "FROM city " + "WHERE ID = " + id;
  }

  public CityDto getCityFromDatabase(ResultSet rset) throws SQLException {
    CityDto cityDto = new CityDto();
    cityDto.setId(rset.getInt("ID"));
    cityDto.setName(rset.getString("Name"));
    cityDto.setCountryCode(rset.getString("CountryCode"));
    cityDto.setDistrict(rset.getString("District"));
    cityDto.setPopulation(rset.getInt("population"));

    return cityDto;
  }
}
