package com.napier.sem;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface databaseConnection {

    ResultSet executeSQLStatement(String sqlStatement) throws SQLException;
}
