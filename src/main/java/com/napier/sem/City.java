package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class City {

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
