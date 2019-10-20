package com.xebia.fs101.xtable;

import java.util.List;

public interface LayoutManager {
    String createTable();
    String createTable(String[] headers);
    String createTable(List<String[]> rows);


}
