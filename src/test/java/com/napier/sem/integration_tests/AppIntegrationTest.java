package com.napier.sem.integration_tests;

import com.napier.sem.App;
import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AppIntegrationTest
{
    static App subject;

    private static IDatabaseConnection databaseConnection;

    @BeforeAll
    static void init()
    {
        subject = new App();
        String location = "localhost:33060";
        databaseConnection = DatabaseConnectionImpl.getInstance(location);
    }

    @Test
    void testExecuteSQLStatement (){
        String statement = "Select Name from city where id='1';";
        String response = databaseConnection.executeSQLStatement(statement);
        assertEquals("{Name=Kabul}", response, "Should return Kabul");
    }

    @Test
    void testExecuteInvalidSqlStatementShouldReturnnEmptyStringWhenSQLExceptionIsThrown(){
        String invalidStatement = "Select;";

        String result = databaseConnection.executeSQLStatement(invalidStatement);
        assertEquals("", result, "Should return an empty string");
    }

    @Test
    void testDisconnect(){
        databaseConnection.disconnect();
        String result = databaseConnection.executeSQLStatement("Select Name from city where id='1';");
        //assertEquals("Failed to execute SQL statement", result, "Should return an error message");
        assertThat(result, CoreMatchers.containsString("Failed to execute SQL statement"));
    }
}
