package com.napier.sem.integration_tests;

import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidDbConnectionTest {
    private static IDatabaseConnection subject1;
    private static final LogCaptor logCaptor = LogCaptor.forClass(DatabaseConnectionImpl.class);

    @BeforeEach
    void init()
    {
        String location = "";
        subject1 = DatabaseConnectionImpl.getInstance(location, 1);
    }

    @AfterAll
    static void tearDown(){
        subject1.disconnect();
        subject1 = null;
    }

    @Test
    void testTryToConnect(){
        subject1.tryToConnect(1);

        List<String> logOutput = logCaptor.getLogs();

        for(var log : logOutput){
            if(Objects.equals(log, "Failed to connect to database attempt 1")){
                assertEquals("Failed to connect to database attempt 1", log, "log has correct message");
            }
        }
    }
}
