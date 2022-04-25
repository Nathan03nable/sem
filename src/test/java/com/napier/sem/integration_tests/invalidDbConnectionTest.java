package com.napier.sem.integration_tests;

import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class invalidDbConnectionTest {

    private static IDatabaseConnection subject;
    private static final LogCaptor logCaptor = LogCaptor.forClass(DatabaseConnectionImpl.class);

    @BeforeAll
    static void init()
    {
        String location = "localhost:123456";
        subject = DatabaseConnectionImpl.getInstance(location, 1);
    }

    @Test
    public void testTryToConnect() {
        subject.tryToConnect(1);

        List<String> logOutput = logCaptor.getLogs();

        boolean result = logOutput.contains("Could not create connection to database server.");
        assertTrue(result, "Could not create DB connection: should return true");
    }
}
