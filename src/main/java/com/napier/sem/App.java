package com.napier.sem;

import java.sql.*;

public class App
{
    static final int RETRIES = 10;
    //Connection to MySql
    private Connection connection = null;

    public static void main(String[] args) throws SQLException {
        App a = new App();

        a.connect();

        CityDto cityDto = a.getACity(3);

        System.out.println(cityDto.toString());

        a.disconnect();
    }

    private CityDto getACity(int id) throws SQLException {
        City city = new City();
        String sqlString = city.createSqlString(id);

        ResultSet resultSet = createAndExecuteSqlStatement(sqlString);
        resultSet.next();
        return city.getCityFromDatabase(resultSet);
    }

    private ResultSet createAndExecuteSqlStatement(String sqlString) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlString);
    }
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
            Thread.sleep(0);
            // Change url to "jdbc:mysql://db:3306/world?useSSL=false" to run on docker
            // Change url to "jdbc:mysql://localhost:33060/world?useSSL=false" to run locally
            connection = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
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
        if (connection != null)
        {
            try
            {
                // Close connection
                connection.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }


}