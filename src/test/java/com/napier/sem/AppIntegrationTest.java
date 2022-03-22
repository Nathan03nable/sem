package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void testExecuteSQLStatement(){
        String location = "localhost:33060";
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location);
        String statement = "Select Name from city where id='1';";
        String response = databaseConnection.executeSQLStatement(statement);
        assertEquals(response, "Kabul");
    }

}
