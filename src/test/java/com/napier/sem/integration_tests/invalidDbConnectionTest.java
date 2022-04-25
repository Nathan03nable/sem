package com.napier.sem.integration_tests;

public class invalidDbConnectionTest {
/*
    private static IDatabaseConnection subject;
    private static final LogCaptor logCaptor = LogCaptor.forClass(DatabaseConnectionImpl.class);

    @BeforeAll
    static void init()
    {
        String location = "localhost:123456";
        subject = DatabaseConnectionImpl.getInstance(location, 1);
    }

    @Test
    public void testTryToConnect(){
        subject.tryToConnect(1);

        List<String> logOutput = logCaptor.getLogs();

        boolean result = logOutput.contains("Could not create connection to database server.");
        boolean result2 = logOutput.contains("Failed to connect to database attempt 1");
        assertTrue(result, "Could not create DB connection: should return true");
        assertTrue(result2, "Failed to connect to database server: should return true");
    }

    @Test
    public void testExecuteSQLStatement_failedDatabaseConnection() throws NullPointerException{
        Assertions.assertThrows(NullPointerException.class, () -> {
            String statement = "Select Name from city where id='1';";
            subject.executeSQLStatement(statement);
        });

        List<String> logOutput = logCaptor.getLogs();
        boolean result = logOutput.contains("Could not create connection to database server.");
        assertTrue(result, "No connection to database server: should return true");
    }
*/
}
