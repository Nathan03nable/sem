package com.napier.sem;

import java.sql.*;
import java.sql.ResultSet;

public class App
{
    static final int RETRIES = 10;

    public static void main(String[] args)
    {
        App a = new App();

        a.connect();

        City city = a.getCityById(1);

        System.out.println(city.toString());

        a.disconnect();
    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        checkForSqlDriver();

        for (int i = 0; i < RETRIES; ++i)
        {
            if (tryToConnect(i)){
                break;
            }
        }
    }

    private void checkForSqlDriver() {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
    }

    private boolean tryToConnect(int i) {
        System.out.println("Connecting to database...");
        try
        {
            Thread.sleep(30000);
            // Change url to "jdbc:mysql://db:3306/Citys?useSSL=false" to run on docker
            // Change url to "jdbc:mysql://localhost:33060/Citys?useSSL=false" to run locally
            con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
            System.out.println("Successfully connected");
            return true;
        }
        catch (SQLException sqle)
        {
            System.out.println("Failed to connect to database attempt " + i);
            System.out.println(sqle.getMessage());
        }
        catch (InterruptedException ie)
        {
            System.out.println("Thread interrupted? Should not happen.");
        }
        return false;
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public City getCityById(int id)
    {
        try
        {
            return returnCityIfExists(id);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }


    private City returnCityIfExists(int id) throws SQLException {
        ResultSet resultSet = createAndExecuteSqlStatement(id);

        if (resultSet.next())
        {
            return getCityFromDatabase(resultSet);
        }
        else{
            return null;
        }
    }

    private ResultSet createAndExecuteSqlStatement(int id) throws SQLException {
        Statement stmt = con.createStatement();
        String sqlString = createSqlString(id);
        return stmt.executeQuery(sqlString);
    }

    private String createSqlString(int id) {
        return "SELECT * " + "FROM city " + "WHERE ID = " + id;
    }

    public City getCityFromDatabase(ResultSet rset) throws SQLException {
        City city = new City();
        city.setId(rset.getInt("ID"));
        city.setName(rset.getString("Name"));
        city.setCountryCode(rset.getString("CountryCode"));
        city.setDistrict(rset.getString("District"));
        city.setPopulation(rset.getInt("population"));

        return city;
    }
}