package com.napier.sem.integration_tests;

import com.napier.sem.App;
import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


class AppIntegrationTest
{
    static App subject;

    private static IDatabaseConnection databaseConnection;

    private static final String FAKE_CLASS = "com.mysql.cj.jdbc.Driver";
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
    void testExecuteInvalidSqlStatementShouldReturnEmptyStringWhenSQLExceptionIsThrown(){
        String invalidStatement = "Select;";

        String result = databaseConnection.executeSQLStatement(invalidStatement);
        assertEquals("", result, "Should return an empty string");
    }

    @Test
    void testTryToConnectThrowsSqlException() throws ClassNotFoundException {
        Mockito.when(Class.forName(FAKE_CLASS)).thenThrow(new ClassNotFoundException());

        assertThrows(ClassNotFoundException.class, () -> databaseConnection.checkForSqlDriver());
    }
    @Test
    void testDisconnect(){
        databaseConnection.disconnect();
    }
}
