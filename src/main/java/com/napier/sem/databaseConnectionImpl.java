package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnectionImpl implements databaseConnection{

    static final int RETRIES = 10;

    /**
     * Connection to MySQL database.
     */
    private Connection connection = null;

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
            connection = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
