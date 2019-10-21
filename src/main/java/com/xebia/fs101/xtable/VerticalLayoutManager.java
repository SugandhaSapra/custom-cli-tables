package com.xebia.fs101.xtable;

import java.util.ArrayList;
import java.util.List;

public class VerticalLayoutManager implements LayoutManager {
    private int tableWidth;
    private int rowCount;
    private int colCount;
    private int colWidth;

    VerticalLayoutManager(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    @Override
    public String createTable() {
        tableWidth = TableConstants.maxColWidth * colCount;
        colWidth = TableConstants.maxColWidth;
        return this.createTopLine() + this.createTabularStruct() + this.createBottomLine();
    }

    @Override
    public String createDataTable(List<String[]> rows) {

        colWidth = computeWidth(rows) + 2;
        tableWidth = colWidth * colCount;
        return this.createTopLine() + this.createTabularStruct(rows) + this.createBottomLine();
    }

    @Override
    public String createTableWithOnlyHeaders(String[] headers) {

        colWidth = computeWidth(headers) + 2;
        tableWidth = (colWidth) * colCount;
        return this.createTopLine() + this.createTabularStruct(headers) + this.createBottomLine();

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

    private String createTopLine() {
        StringBuilder top = new StringBuilder();
        for (int i = 1; i <= tableWidth; i++) {
            if (i == 1)
                top.append(TableConstants.topLeft);
            if (i == tableWidth)
                top.append(TableConstants.topRight);
            else if (i % colWidth == 0)
                top.append(TableConstants.topMiddle);
            else
                top.append(TableConstants.mid);
        }
        return top.toString();

    }


    private String createBottomLine() {
        StringBuilder bottom = new StringBuilder();
        bottom.append("\n");
        for (int i = 1; i <= tableWidth; i++) {
            if (i == 1)
                bottom.append(TableConstants.bottomLeft);
            if (i == tableWidth)
                bottom.append(TableConstants.bottomRight);
            else if (i % colWidth == 0)
                bottom.append(TableConstants.bottomMiddle);
            else
                bottom.append(TableConstants.mid);
        }
        return bottom.toString();
    }

    private String createTabularStruct() {
        StringBuilder tableData = new StringBuilder();
        for (int j = 1; j <= rowCount; j++) {
            tableData.append("\n" + TableConstants.verticalSeparator);
            for (int i = 1; i < tableWidth; i++) {
                if (i % colWidth == 0)
                    tableData.append(TableConstants.verticalSeparator);
                else
                    tableData.append(" ");
            }
            tableData.append(TableConstants.verticalSeparator);
            if (j == rowCount)
                break;
            tableData.append(createRowSeparator());
        }

        return tableData.toString();

    }


    private String createTabularStruct(String[] headers) {
        StringBuilder tableHeader = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 1; j <= colCount; j++) {
                if (j == 1) {
                    tableHeader.append("\n" + createCellWithData(headers[count++]));
                } else
                    tableHeader.append(createCellWithoutData());

            }
            tableHeader.append(TableConstants.verticalSeparator);
            if (i != rowCount)
                tableHeader.append(createRowSeparator());
        }
        return tableHeader.toString();
    }

    private String createTabularStruct(List<String[]> data) {
        StringBuilder tableData = new StringBuilder();
        String[] headers = data.get(0);
        int count = 0;
        for (int i = 0; i < rowCount; i++) {
            int k = 0;
            String[] row = data.get(i + 1);
            for (int j = 0; j < colCount; j++) {
                if (j == 0) {
                    tableData.append("\n" + createCellWithData(headers[count++]));
                } else
                    tableData.append(createCellWithData(row[k++]));

            }
            tableData.append(TableConstants.verticalSeparator);
            if (i != rowCount - 1)
                tableData.append(createRowSeparator());
        }
        return tableData.toString();

    }


    public StringBuilder createCellWithData(String data) {
        StringBuilder cellData = new StringBuilder();
        cellData.append(TableConstants.verticalSeparator + " ");
        int spaceLeft = colWidth - data.length();
        cellData.append(data);
        for (int i = 2; i < spaceLeft - 1; i++)
            cellData.append(" ");
        if (data.length() != colWidth - 2)
            cellData.append(" ");
        return cellData;
    }

    public StringBuilder createCellWithoutData() {
        StringBuilder builder = new StringBuilder();
        builder.append(TableConstants.verticalSeparator);
        for (int i = 1; i <= colWidth - 1; i++)
            builder.append(" ");
        return builder;
    }


    private String createRowSeparator() {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("\n" + TableConstants.leftMid);
        for (int i = 1; i < tableWidth; i++) {
            if (i % colWidth == 0)
                rowSeparator.append(TableConstants.midMid);
            else
                rowSeparator.append(TableConstants.mid);
        }
        rowSeparator.append(TableConstants.rightMid);
        return rowSeparator.toString();
    }

   }