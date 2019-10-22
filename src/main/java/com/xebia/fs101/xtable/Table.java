package com.xebia.fs101.xtable;

import java.util.List;

public class Table {

    private int rowCount;
    private int colCount;
    private LayoutManager layoutManager;
    private Renderer renderer;
    private String[] headers;
    private List<String[]> rows;
    private TableLayout tableLayout;

    private Table(Builder builder) {
        rowCount = builder.rowCount;
        colCount = builder.colCount;
        renderer = new ConsoleBaseRenderer();
        rows = builder.rows;
        headers = builder.headers;
        tableLayout=builder.tableLayout;
        if(tableLayout==TableLayout.VERTICAL)
            layoutManager = new VerticalLayoutManager(rowCount, colCount);
        else
            layoutManager=new HorizontalLayoutManager(rowCount,colCount);
    }

    public void render() {
        renderer.printTable(generate());
    }

    public String getShape() {
        return rowCount + " rows X " + colCount + " cols";
    }


    public String generate() {

        if (headers != null || rows != null) {
            if (headers != null && rows != null) {
                rows.add(0, headers);
                validateRowsAndCols(layoutManager);
                return layoutManager.createDataTable(rows);
            } else if (headers != null && rows == null) {
                validateRowsAndCols(layoutManager);
                return layoutManager.createTableWithOnlyHeaders(headers);
            } else {
                validateRowsAndCols(layoutManager);
                return layoutManager.createDataTable(rows);
            }

        } else
            validateRowsAndCols(layoutManager);
        return layoutManager.createTable();

    }

    private void validateRowsAndCols(LayoutManager layoutManager) {
        if(layoutManager instanceof  VerticalLayoutManager) {
            System.out.println("This testing  is for vertical table");
            if(rowCount<0 ||colCount<0)
                throw new IllegalArgumentException("Row and col should be greater than 0");
            if(headers!=null && headers.length!=rowCount)
                throw new IllegalArgumentException("Please pass according to number of rows");
            if (rows != null && rows.size() != colCount)
                throw new IllegalArgumentException("Please pass according to the number of cols");
            if (rows != null) {
                for (String cells[] : rows) {
                    if (cells.length != rowCount)
                        throw new IllegalArgumentException("Please pass according to the number of rows");
                }
            }

        }
        else
        {
                if (rowCount < 0 || colCount < 0)
                    throw new IllegalArgumentException("Row and Col should be greater than 0");
                if (headers != null && headers.length != colCount)
                    throw new IllegalArgumentException("Please pass according to the number of cols");
                if (rows != null && rows.size() != rowCount)
                    throw new IllegalArgumentException("Please pass according to the number of rows");
                if (rows != null) {
                    for (String cells[] : rows) {
                        if (cells.length != colCount)
                            throw new IllegalArgumentException("Please pass according to the number of rows");
                    }
                }
        }
    }


    public static final class Builder {
        private int rowCount;
        private int colCount;
        private LayoutManager layoutManager;
        private Renderer renderer;
        private List<String[]> rows;
        private String[] headers;
        private TableLayout tableLayout;

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
        public Builder withTableLayout(TableLayout val) {
            tableLayout = val;
            return this;
        }


        public Table build() {
            return new Table(this);
        }
    }


}