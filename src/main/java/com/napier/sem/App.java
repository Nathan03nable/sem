package com.napier.sem;

import java.sql.*;
import java.sql.ResultSet;

public class App
{
    static final int RETRIES = 10;

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Disconnect from database
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
            // Wait a bit for db to start
            // Set sleep to 0 if running locally
            Thread.sleep(30000);
            // Connect to database
            // Change url to "jdbc:mysql://db:3306/employees?useSSL=false" to run on docker
            // Change url to "jdbc:mysql://localhost:33060/employees?useSSL=false" to run locally
            con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
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

    public Employee getEmployee(int ID)
    {
        try
        {
            return returnEmployeeIfExists(ID);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    private Employee returnEmployeeIfExists(int ID) throws SQLException {
        ResultSet resultSet = createAndExecuteSqlStatement(ID);
        // Return new employee if valid.
        // Check one is returned
        if (resultSet.next())
        {
            return getEmployeeFromDatabase(resultSet);
        }
        else{
            return null;
        }
    }

    private ResultSet createAndExecuteSqlStatement(int ID) throws SQLException {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        String sqlString = createSqlString(ID);
        // Execute SQL statement
        return stmt.executeQuery(sqlString);
    }

    private String createSqlString(int ID) {
        return "SELECT emp_no, first_name, last_name "
                + "FROM employees "
                + "WHERE emp_no = " + ID;
    }

    private Employee getEmployeeFromDatabase(ResultSet rset) throws SQLException {
        Employee emp = new Employee();
        emp.setEmpNo(rset.getInt("emp_no"));
        emp.setFirstName(rset.getString("first_name"));
        emp.setLastName(rset.getString("last_name"));
        return emp;
    }
}