package com.napier.sem.integration_tests;

import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockedStatic.Verification;
import org.mockito.Mockito;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AppIntegrationTest
{
    private static IDatabaseConnection subject;

    private static final String FAKE_CLASS = "not real";

    @BeforeAll
    static void init()
    {
        String location = "localhost:33060";
        subject = DatabaseConnectionImpl.getInstance(location);
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
    void testTryToConnectThrowsSqlException() throws SQLException {
        MockedStatic<DriverManager> driverManagerMockedStatic = Mockito.mockStatic(DriverManager.class);
        driverManagerMockedStatic.when((Verification) DriverManager.getConnection("aurl", "auser", "pass")).thenThrow(new SQLException());
        assertThrows(SQLException.class, () -> subject.tryToConnect(0));
    }

    @Test
    void testTryToConnectThrowsInterruptedException() throws InterruptedException, SQLException {
        MockedStatic<DriverManager> driverManagerMockedStatic = Mockito.mockStatic(DriverManager.class);
        driverManagerMockedStatic.when((Verification) DriverManager.getConnection("aurl", "auser", "pass")).thenThrow(new InterruptedException());
        assertThrows(SQLException.class, () -> subject.tryToConnect(0));
    }

    @Test
    void testDisconnect(){
        LogCaptor logCaptor = LogCaptor.forClass(DatabaseConnectionImpl.class);
        subject.disconnect();
        String statement = "Select Name from city where id='1';";
        subject.executeSQLStatement(statement);
        List<String> logOutput = logCaptor.getLogs();
        logOutput.contains("No operations allowed after connection closed.");
    }
}
