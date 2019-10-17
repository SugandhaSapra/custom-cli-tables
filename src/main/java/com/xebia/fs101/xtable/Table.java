package com.xebia.fs101.xtable;

public class Table {

    private TableLayoutManager tableLayoutManager;

    public Table(int rowCount, int colCount) {
        tableLayoutManager = new TableLayoutManager(rowCount, colCount);

    }

    public String generateTable() {
        return tableLayoutManager.createTable();
    }

    public String shape() {
        return tableLayoutManager.getShape();
    }

}