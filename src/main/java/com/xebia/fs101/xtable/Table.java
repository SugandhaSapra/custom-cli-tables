package com.xebia.fs101.xtable;

public class Table {

    private int rowCount;
    private int colCount;
    private TableLayoutManager tableLayoutManager;
    private Renderer renderer;
    private String generatedTable;


    Table(int rowCount, int colCount) {
        tableLayoutManager = new TableLayoutManager(rowCount, colCount);
        if (rowCount <= 0 || colCount <= 0)
            throw new IllegalArgumentException("Row and Col should be greater than 0");
        this.rowCount = rowCount;
        this.colCount = colCount;
        renderer = new ConsoleBaseRenderer();
    }

    public String getShape() {
        return rowCount + " rows X " + colCount + " cols";
    }

    public String generateTable() {
        generatedTable = tableLayoutManager.createTable();
        return generatedTable;
    }

    public void renderTable() {
        renderer.printTable(generatedTable);
    }


}