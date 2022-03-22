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

    @BeforeAll
    static void init()
    {
        subject = new App();
    }

    @Test
    void testExecuteSQLStatement(){
        String location = "localhost:33060";
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location);
        String statement = "Select Name from city where id='1';";
        String response = databaseConnection.executeSQLStatement(statement);
        assertEquals("{Name=Kabul}", response);
    }

    @Test
    void testExecuteInvalidSqlStatementShouldThrowSQLException(){
        String location = "localhost:33060";
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location);
        String invalidStatement = "Select;";

        String result = databaseConnection.executeSQLStatement(invalidStatement);
        assertEquals("", result);
    }

}
