package com.xebia.fs101.xtable;

public class TableApi {
    private int rowCount;
    private int colCount;

    public TableApi(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;

    }

    public String createTable() {
        TableLayoutManager tableLayoutManager = new TableLayoutManager(rowCount, colCount);
        return tableLayoutManager.getTopLine() + tableLayoutManager.getTableData() + tableLayoutManager.getBottomLine();
    }


}
