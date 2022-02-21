package com.napier.sem;

public interface ReportGenerator {
    String generateReport(String sqlStatement);

    String constructSqlStatement();
}
