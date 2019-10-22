package com.xebia.fs101.xtable;

import java.util.List;

public class HorizontalLayoutManager implements LayoutManager {

    private int tableWidth;
    private int rowCount;
    private int colCount;
    private int colWidth;

    HorizontalLayoutManager(int rowCount, int colCount) {

        this.rowCount = rowCount;
        this.colCount = colCount;

    }

    @Override
    public String createDataTable(List<String[]> rows) {

        colWidth = computeWidth(rows) + 2;
        tableWidth = colWidth * colCount;
        return this.createTopLine() + this.createTabularStruct(rows) + this.createBottomLine();
    }

    @Override
    public String createTable() {

        tableWidth = TableConstants.MAX_COL_WIDTH * colCount;
        colWidth = TableConstants.MAX_COL_WIDTH;
        return this.createTopLine() + this.createTabularStruct() + this.createBottomLine();

    }


    @Override
    public String createTableWithOnlyHeaders(String[] headers) {

        colWidth = computeWidth(headers) + 2;
        tableWidth = colWidth * colCount;
        return this.createTopLine() + this.createTabularStruct(headers) + this.createBottomLine();

    }

    private String createTopLine() {
        StringBuilder top = new StringBuilder();
        for (int i = 1; i <= tableWidth; i++) {
            if (i == 1)
                top.append(TableConstants.TOP_LEFT);
            if (i == tableWidth)
                top.append(TableConstants.TOP_RIGHT);
            else if (i % colWidth == 0)
                top.append(TableConstants.TOP_MIDDLE);
            else
                top.append(TableConstants.MID);
        }
        return top.toString();

    }

    private String createBottomLine() {
        StringBuilder bottom = new StringBuilder();
        bottom.append("\n");
        for (int i = 1; i <= tableWidth; i++) {
            if (i == 1)
                bottom.append(TableConstants.BOTTOM_LEFT);
            if (i == tableWidth)
                bottom.append(TableConstants.BOTTOM_RIGHT);
            else if (i % colWidth == 0)
                bottom.append(TableConstants.BOTTOM_MIDDLE);
            else
                bottom.append(TableConstants.MID);
        }
        return bottom.toString();
    }

    private String createRowSeparator() {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("\n" + TableConstants.LEFT_MID);
        for (int i = 1; i < tableWidth; i++) {
            if (i % colWidth == 0)
                rowSeparator.append(TableConstants.MID_MID);
            else
                rowSeparator.append(TableConstants.MID);
        }
        rowSeparator.append(TableConstants.RIGHT_MID);
        return rowSeparator.toString();
    }

    private String createTabularStruct() {
        StringBuilder tableData = new StringBuilder();

        for (int j = 1; j <= rowCount; j++) {
            tableData.append("\n" + TableConstants.VERTICAL_SEPARATOR);
            for (int i = 1; i < tableWidth; i++) {
                if (i % colWidth == 0)
                    tableData.append(TableConstants.VERTICAL_SEPARATOR);
                else
                    tableData.append(" ");
            }
            tableData.append(TableConstants.VERTICAL_SEPARATOR);
            if (j == rowCount)
                break;
            tableData.append(createRowSeparator());
        }

        return tableData.toString();

    }

    private String createTabularStruct(String[] headers) {
        StringBuilder tableHeader = new StringBuilder();
        tableHeader.append("\n" + TableConstants.VERTICAL_SEPARATOR);
        for (int j = 0; j < headers.length; j++) {
            for (int k = 0; k < colWidth; ) {
                tableHeader.append(" ").append(headers[j]);
                k = k + headers[j].length() + 2;
                while (k < colWidth) {
                    tableHeader.append(" ");
                    k++;
                }
                tableHeader.append(TableConstants.VERTICAL_SEPARATOR);
            }
        }
        tableHeader.append(createRowSeparator());
        for (int j = 1; j <= rowCount; j++) {
            tableHeader.append("\n" + TableConstants.VERTICAL_SEPARATOR);
            for (int i = 1; i < tableWidth; i++) {
                if (i % colWidth == 0)
                    tableHeader.append(TableConstants.VERTICAL_SEPARATOR);
                else
                    tableHeader.append(" ");
            }
            tableHeader.append(TableConstants.VERTICAL_SEPARATOR);
            if (j == rowCount - 1)
                break;
            tableHeader.append(createRowSeparator());

            return tableHeader.toString();

        }

        return tableHeader.toString();

    }

    private String createTabularStruct(List<String[]> rows) {
        StringBuilder tableData = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            tableData.append("\n" + TableConstants.VERTICAL_SEPARATOR);
            String[] cells = rows.get(i);
            for (int j = 0; j < cells.length; j++) {
                for (int k = 0; k < colWidth; ) {
                    tableData.append(" ").append(cells[j]);
                    k = k + cells[j].length() + 2;
                    while (k < colWidth) {
                        tableData.append(" ");
                        k++;
                    }

                }
                tableData.append(TableConstants.VERTICAL_SEPARATOR);
            }
            if (i == rowCount - 1)
                break;
            tableData.append(createRowSeparator());
        }
        return tableData.toString();

    }


    private int computeWidth(String[] headers) {
        int maxWidth = Integer.MIN_VALUE;

        for (String header : headers) {
            maxWidth = Math.max(maxWidth, header.length());

        }
        return maxWidth;
    }

    private int computeWidth(List<String[]> rows) {
        int maxWidth = Integer.MIN_VALUE;
        for (String[] cells : rows) {
            for (int i = 0; i < cells.length; i++) {

                maxWidth = Math.max(maxWidth, cells[i].length());
            }
        }
        return maxWidth;
    }

}