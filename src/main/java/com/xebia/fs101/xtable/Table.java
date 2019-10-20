package com.xebia.fs101.xtable;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private int rowCount;
    private int colCount;
    private HorizontalLayoutManager horizontalLayoutManager;
    private Renderer renderer;
    private String[] headers;
    private List<String[]> rows = new ArrayList<>();


    public Table(int rowCount, int colCount) {

        horizontalLayoutManager = new HorizontalLayoutManager(rowCount, colCount);
        if (rowCount <= 0 || colCount <= 0)
            throw new IllegalArgumentException("Row and Col should be greater than 0");
        this.rowCount = rowCount;
        this.colCount = colCount;
        renderer = new ConsoleBaseRenderer();


    }

    private Table(Builder builder) {
        rowCount = builder.rowCount;
        colCount = builder.colCount;
        horizontalLayoutManager = new HorizontalLayoutManager(rowCount, colCount);
        renderer = builder.renderer;
        rows = builder.rows;
        headers = builder.headers;
    }


    public String getShape() {
        return rowCount + " rows X " + colCount + " cols";
    }


    public String generateTable() {

        return horizontalLayoutManager.createTable();

    }

    public String generateTable(String[] headers) {

        if(headers.length!=colCount)
            throw new IllegalArgumentException("Please pass according to the number of columns.");
        return horizontalLayoutManager.createTable(headers);

    }

    public String generateTable(List<String[]> rows) {
        if(rows.size()!=rowCount)
            throw new IllegalArgumentException("Please pass according to the number of rows");
        for(String cells[]:rows){
            if(cells.length!=colCount)
                throw new IllegalArgumentException("Please pass according to the number of columns");}
        return horizontalLayoutManager.createTable(rows);

    }

    public String generateTable(List<String[]> rows, String[] headers) {

        rows.add(0, headers);
        return horizontalLayoutManager.createTable(rows);

    }


    public void renderTable() {
        renderer.printTable(generateTable());
    }


    public static final class Builder {
        private int rowCount;
        private int colCount;
        private HorizontalLayoutManager horizontalLayoutManager;
        private Renderer renderer;
        private List<String[]> rows;
        private String[] headers;

        public Builder() {
        }

        public Builder withRowCount(int val) {
            rowCount = val;
            return this;
        }

        public Builder withColCount(int val) {
            colCount = val;
            return this;
        }

        public Builder withHeader(String[] val) {
            headers = val;
            return this;
        }

        public Builder withRenderer(Renderer val) {
            renderer = val;
            return this;
        }

        public Builder withRows(List<String[]> val) {
            rows = val;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }
}