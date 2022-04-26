package com.napier.sem.integration_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import java.util.List;
import java.util.Objects;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class invalidDbConnectionTest {
    /**
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
    public void testTryToConnect(){
        subject1.tryToConnect(1);

        List<String> logOutput = logCaptor.getLogs();

        for(var log : logOutput){
            if(Objects.equals(log, "Failed to connect to database attempt 1")){
                assertEquals("Failed to connect to database attempt 1", log);
            }
        }
    }
    */
}
