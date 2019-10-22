package com.xebia.fs101.xtable;

import java.util.List;
import java.util.Map;

public interface LayoutManager {
    String createTable();
    String createTableWithHeadersOnly(String[] headers);
    String createDataTable(List<String[]> rows);
    //String createTableWithColumnWidth(Map<Integer,Integer> columnWidth);
}
