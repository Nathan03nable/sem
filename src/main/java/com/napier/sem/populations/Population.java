package com.napier.sem.populations;

import com.napier.sem.DatabaseConnectionImpl;
import com.napier.sem.IDatabaseConnection;
import com.napier.sem.ReportLanguageImpl;

public class Population {
  IDatabaseConnection dbConnection;

  public Population(IDatabaseConnection dbConnection){
    this.dbConnection = dbConnection;
  }

  public void worldPopulation(){
    IDatabaseConnection db = DatabaseConnectionImpl.getInstance();
    String stmt = "SELECT name, code, continent, region, population, capital FROM country ORDER BY population DESC;";
    ReportLanguageImpl languageReport = new ReportLanguageImpl(db);
    System.out.println(languageReport.generateReport(stmt));
  }
}
