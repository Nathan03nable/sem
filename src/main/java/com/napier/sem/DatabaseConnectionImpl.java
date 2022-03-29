package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseConnectionImpl implements IDatabaseConnection {

    private static DatabaseConnectionImpl instance;
    private final ListHelperFunctions listHelperFunctions;
    static final int RETRIES = 10;
    /**
     * Connection to MySQL database.
     */
    private Connection connection = null;
    private final String location;

    private DatabaseConnectionImpl(String location){
        listHelperFunctions = new ListHelperFunctions();
        this.location = location;
        this.connect();
    }

    public static IDatabaseConnection getInstance(String location){
        if (instance == null){
            instance = new DatabaseConnectionImpl(location);
        }
        return instance;
    }

    /**
     * Accepts an SQL statement, executes it and returns the result of the query
     * @param request a string in the form of an SQL statement
     * @return List of results from the database. Returns null if there was an error.
     */
    @Override
    public String executeSQLStatement(String request) {
        ResultSet result;
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> row;

        try {
            Statement stmt = connection.createStatement();
            result = stmt.executeQuery(request);

            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (result.next()) {
                row = listHelperFunctions.getRow(result, metaData, columnCount);
                resultList.add(row);
            }
        } catch (SQLException e){
            System.out.println("Failed to execute SQL statement '" + request +"'");
            System.out.println(e.getMessage());
        }

        StringBuilder stringBuilder = listHelperFunctions.buildString(resultList);

        return stringBuilder.toString();
    }

    /**
     * Connect to the MySQL database.
     */
    private void connect()
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
            System.out.println("jdbc:mysql://" + location
                    + "/world?allowPublicKeyRetrieval=true&useSSL=false");
            // Change url to "jdbc:mysql://db:3306/world?useSSL=false" to run on docker
            // Change url to "jdbc:mysql://localhost:33060/world?useSSL=false" to run locally
            // Original: connection = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
            connection = DriverManager.getConnection("jdbc:mysql://" + location
                            + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                    "root", "example");
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
    @Override
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
