package com.xebia.fs101.xtable;

public class Table {

    private TableLayoutManager tableLayoutManager;
    private int rowCount;
    private int colCount;

    public Table(int rowCount, int colCount) {
        tableLayoutManager = new TableLayoutManager(rowCount, colCount);
        this.rowCount=rowCount;
        this.colCount=colCount;

    }

    public String generateTable() {
        return tableLayoutManager.createTable();
    }

    public String shape() {
        return tableLayoutManager.getShape();
    }

}