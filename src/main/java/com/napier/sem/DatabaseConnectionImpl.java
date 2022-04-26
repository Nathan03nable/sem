package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DatabaseConnectionImpl implements IDatabaseConnection {

    private static DatabaseConnectionImpl instance;
    private final ListHelperFunctions listHelperFunctions;
    private int retries = 10;
    /**
     * Connection to MySQL database.
     */
    private Connection connection = null;
    private final String location;
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnectionImpl.class.getName());

    private DatabaseConnectionImpl(String location, int connectionAttempts){
        listHelperFunctions = new ListHelperFunctions();
        this.location = location;
        this.retries = connectionAttempts;
        this.connect();
    }

    public static IDatabaseConnection getInstance(String location, int connectionAttempts){
        if (instance == null){
            instance = new DatabaseConnectionImpl(location, connectionAttempts);
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
            LOGGER.severe("Failed to execute SQL statement '" + request +"'");
            LOGGER.severe(e.getMessage());
        }

        StringBuilder stringBuilder = listHelperFunctions.buildString(resultList);

        return stringBuilder.toString();
    }

    /**
     * Connect to the MySQL database.
     */
    @Override
    public void connect()
    {
        checkForSqlDriver("com.mysql.cj.jdbc.Driver");

        for (int i = 0; i < retries; ++i)
        {
            LOGGER.info("Attempting to connect to database");
            if (tryToConnect(i)){
                LOGGER.info("Connection established");
                break;
            }
        }
    }

    @Override
    public void checkForSqlDriver(String driver) {
        try
        {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            throw(new RuntimeException("Could not load SQL driver"));
        }
    }

    @Override
    public boolean tryToConnect(int i) {
        LOGGER.info("Connecting to database...");

        try
        {
            Thread.sleep(30000);
            LOGGER.info("jdbc:mysql://" + location
                    + "/world?allowPublicKeyRetrieval=true&useSSL=false");

            connection = DriverManager.getConnection("jdbc:mysql://" + location
                            + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                    "root", "example");
            LOGGER.info("Successfully connected");
            return true;
        }
        catch (SQLException sqle)
        {
            LOGGER.severe("Failed to connect to database attempt " + i);
            LOGGER.severe(sqle.getMessage());
        }
        catch (InterruptedException ie)
        {
            LOGGER.severe("Thread interrupted? Should not happen.");
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
                connection.close();
            }
            catch (Exception e)
            {
                LOGGER.severe("Error closing connection to database");
            }
        }
    }
}
