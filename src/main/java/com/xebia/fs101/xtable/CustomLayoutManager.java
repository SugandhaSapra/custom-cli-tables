package com.xebia.fs101.xtable;

import java.util.List;
import java.util.Map;

public interface CustomLayoutManager {
    String createTable(Map<Integer,Integer> columnWidth);
    String createTableWithHeadersOnly(String[] headers,Map<Integer,Integer> columnWidth);
    String createTableWithHeadersAndData(List<String[]> rows,Map<Integer,Integer> columnWidth);
}