package com.napier.sem.integration_tests;

import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.mockito.MockedStatic;
import org.mockito.MockedStatic.Verification;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


class validDbConnectionIntegrationTest
{
    private static IDatabaseConnection subject;

    private static final String FAKE_CLASS = "not real";

    private static final LogCaptor logCaptor = LogCaptor.forClass(DatabaseConnectionImpl.class);


    @BeforeAll
    static void init()
    {
        String location = "localhost:33060";
        subject = DatabaseConnectionImpl.getInstance(location, 5);
    }

    @AfterAll
    static void tearDown(){
        subject.disconnect();
        subject = null;
    }

    @Test
    void testExecuteSQLStatement (){
        String statement = "Select Name from city where id='1';";
        String response = subject.executeSQLStatement(statement);
        assertEquals("{Name=Kabul}", response, "Should return Kabul");
    }

    @Test
    void testExecuteInvalidSqlStatementShouldReturnEmptyStringWhenSQLExceptionIsThrown(){
        String invalidStatement = "Select;";

        String result = subject.executeSQLStatement(invalidStatement);
        assertEquals("", result, "Should return an empty string");
    }

    @Test
    void testCheckForSqlDriverThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> subject.checkForSqlDriver(FAKE_CLASS));
    }

    @Test
    void testTryToConnect_SuccessfulConnection(){
        subject.tryToConnect(1);
        logCaptor.getLogs();

        List<String> logOutput = logCaptor.getLogs();

        boolean result = logOutput.contains("Successfully connected");
        assertTrue(result, "TryToConnect: should return true");
    }

    @Test
    void testConnect(){
        subject.connect();

        List<String> logOutput = logCaptor.getLogs();
        boolean result1 = logOutput.contains("Attempting to connect to database");
        boolean result2 = logOutput.contains("Connection established");
        assertTrue(result1, "Attempting to connect: should return true");
        assertTrue(result2, "Connection established: should return true");
    }

    @Test
    void testDisconnect(){
        subject.disconnect();

        String statement = "Select Name from city where id='1';";
        subject.executeSQLStatement(statement);

        List<String> logOutput = logCaptor.getLogs();
        boolean result = logOutput.contains("No operations allowed after connection closed.");
        assertTrue(result, "No operations allowed: should return true");
    }
}
