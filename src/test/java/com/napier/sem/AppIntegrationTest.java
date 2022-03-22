package com.napier.sem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLSyntaxErrorException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AppIntegrationTest
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
        assertEquals("{Name=Kabul}", response);
    }

    @Test
    void testExecuteInvalidSqlStatementShouldReturnnEmptyStringWhenSQLExceptionIsThrown(){
        String invalidStatement = "Select;";

        String result = databaseConnection.executeSQLStatement(invalidStatement);
        assertEquals("", result);
    }

    @Test
    void testDisconnect(){
        databaseConnection.disconnect();
    }
}
