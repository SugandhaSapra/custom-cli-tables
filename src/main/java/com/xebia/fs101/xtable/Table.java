package com.xebia.fs101.xtable;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private int rowCount;
    private int colCount;
    private TableLayoutManager tableLayoutManager;
    private Renderer renderer;
    private List<String[]> rows=new ArrayList<>();;


    Table(int rowCount, int colCount) {

        tableLayoutManager = new TableLayoutManager(rowCount, colCount);
        if (rowCount <= 0 || colCount <= 0)
            throw new IllegalArgumentException("Row and Col should be greater than 0");
        this.rowCount = rowCount;
        this.colCount = colCount;
        renderer = new ConsoleBaseRenderer();


    }

   /* Table(int rowCount, int colCount, Renderer renderer) {
        tableLayoutManager = new TableLayoutManager(rowCount, colCount);
        if (rowCount <= 0 || colCount <= 0)
            throw new IllegalArgumentException("Row and Col should be greater than 0");
        this.rowCount = rowCount;
        this.colCount = colCount;
        renderer = this.renderer;

    }*/

    public String getShape() {
        return rowCount + " rows X " + colCount + " cols";
    }

    public String generateTable() {

        return tableLayoutManager.createTable(rows);

    }

    public void renderTable() {
        renderer.printTable(generateTable());
    }

    public void addRow(String... cells) {
       rows.add(cells);

    }

  }