package com.napier.sem;

import java.util.List;
import java.util.Map;

public interface DatabaseConnection {

    List<Map<String, Object>> executeSQLStatement(String sqlStatement);
}
