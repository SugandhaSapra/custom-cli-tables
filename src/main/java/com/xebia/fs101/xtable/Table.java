package com.xebia.fs101.xtable;

public class Table {

    private int rowCount;
    private int colCount;
    private TableLayoutManager tableLayoutManager;
    private Renderer renderer;
    private String tableStructure;


    Table(int rowCount, int colCount) {
        tableLayoutManager = new TableLayoutManager(rowCount, colCount);
        this.rowCount = rowCount;
        this.colCount = colCount;
        renderer = new ConsoleBaseRenderer();
    }

    public String getShape() {
        return rowCount + "X" + colCount;
    }

    public String generateTable() {
        tableStructure = tableLayoutManager.createTable();
        return tableStructure;
    }

    public void renderTable() {
        renderer.printTable(tableStructure);
    }


}