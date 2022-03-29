package com.napier.sem;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListHelperFunctions {

    public Map<String, Object> getRow(ResultSet result, ResultSetMetaData metaData, int columnCount) throws SQLException {
        HashMap<String, Object> row = new LinkedHashMap<>();
        for (int i = 1; i <= columnCount; i++) {
            row.put(metaData.getColumnName(i), result.getObject(i));
        }
        return row;
    }

    public StringBuilder buildString(List<Map<String, Object>> resultList) {
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> stringObjectMap : resultList) {
            sb.append(stringObjectMap.toString());
            if(stringObjectMap != resultList.get(resultList.size() - 1)) {
                sb.append("\n");
            }
        }
        return sb;
    }

}
