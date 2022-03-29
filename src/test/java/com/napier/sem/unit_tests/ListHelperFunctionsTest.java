package com.napier.sem.unit_tests;

import com.napier.sem.ListHelperFunctions;
import java.util.Map;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListHelperFunctionsTest {

  private ListHelperFunctions subject;

  @Mock
  private ResultSet resultSet;

  @Mock
  private ResultSetMetaData resultSetMetaData;

  private final int RANDOM_COLUMN_NUMBER = 1;
  private final int RANDOM_LOOP_COUNT = 1;

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
    subject = new ListHelperFunctions();
  }

  @Test
  void getRowTest() throws SQLException {
    LinkedHashMap<String, Object> row = new LinkedHashMap<>();
    row.put("Column name", "String");

    Mockito.when(resultSetMetaData.getColumnName(RANDOM_COLUMN_NUMBER)).thenReturn("Column name");
    Mockito.when(resultSet.getObject(RANDOM_COLUMN_NUMBER)).thenReturn("String");

    Map<String, Object> result = subject.getRow(resultSet, resultSetMetaData, RANDOM_LOOP_COUNT);
    assertEquals(row, result, "Should return expected row");
  }

  @Test
  void getRowTestShouldThrowSQLException() throws SQLException {
    Mockito.when(resultSetMetaData.getColumnName(RANDOM_COLUMN_NUMBER))
        .thenThrow(new SQLException());
    Mockito.when(resultSet.getObject(RANDOM_COLUMN_NUMBER)).thenReturn("String");

    assertThrows(SQLException.class, () -> subject.getRow(resultSet, resultSetMetaData, RANDOM_LOOP_COUNT));
  }

  @Test
  void buildStringTest() {
    Map<String, Object> singleResult = new LinkedHashMap<>();
    singleResult.put("Hello", "World");

    List<Map<String, Object>> resultList = new ArrayList<>();
    resultList.add(singleResult);
    resultList.add(singleResult);
    resultList.add(singleResult);

    StringBuilder result = subject.buildString(resultList);

    assertEquals("{Hello=World}{Hello=World}{Hello=World}", result.toString(), "Should build expected string");
  }
}
