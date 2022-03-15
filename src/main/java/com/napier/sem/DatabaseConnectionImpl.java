package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnectionImpl implements IDatabaseConnection {

    private static DatabaseConnectionImpl instance;
    static final int RETRIES = 10;
    /**
     * Connection to MySQL database.
     */
    private Connection connection = null;

    private DatabaseConnectionImpl(){
        this.connect();
    }

    public static IDatabaseConnection getInstance(){
        if (instance == null){
            instance = new DatabaseConnectionImpl();
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
        ResultSet result = null;
        List<LinkedHashMap<String, Object>> resultList = new ArrayList<LinkedHashMap<String, Object>>();
        LinkedHashMap<String, Object> row = null;

        try {
            Statement stmt = connection.createStatement();
            result = stmt.executeQuery(request);

            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (result.next()) {
                row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), result.getObject(i));
                }
                resultList.add(row);
            }
        } catch (SQLException e){
            System.out.println("Failed to execute SQL statement '" + request +"'");
            System.out.println(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> stringObjectMap : resultList) {
            sb.append(stringObjectMap.toString());
            sb.append("\n");
        }

        return sb.toString();
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
            Thread.sleep(0);
            // Change url to "jdbc:mysql://db:3306/world?useSSL=false" to run on docker
            // Change url to "jdbc:mysql://localhost:33060/world?useSSL=false" to run locally
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
