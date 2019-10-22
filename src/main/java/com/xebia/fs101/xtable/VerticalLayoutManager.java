package com.xebia.fs101.xtable;

import java.util.List;

import static com.xebia.fs101.xtable.TableConstants.*;

public class VerticalLayoutManager implements LayoutManager {
    private int tableWidth;
    private int rowCount;
    private int colCount;
    private int colWidth;
    private final int START_POSITION=1;

    VerticalLayoutManager(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    @Override
    public String createTable() {
        tableWidth = MAX_COL_WIDTH * colCount;
        colWidth = MAX_COL_WIDTH;
        return this.createTopLine() + this.createTableStructure() + this.createBottomLine();
    }

    @Override
    public String createDataTable(List<String[]> rows) {
        colWidth = computeWidth(rows) + PADDING;
        tableWidth = colWidth * colCount;
        return this.createTopLine() + this.createTableStructure(rows) + this.createBottomLine();
    }

    @Override
    public String createTableWithHeadersOnly(String[] headers) {
        colWidth = computeWidth(headers) + 2;
        tableWidth = (colWidth) * colCount;
        return this.createTopLine() + this.createTableStructure(headers) + this.createBottomLine();
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
            if (i == START_POSITION)
                top.append(TOP_LEFT);
            if (i == tableWidth)
                top.append(TOP_RIGHT);
            else if (i % colWidth == 0)
                top.append(TOP_MIDDLE);
            else
                top.append(MID);
        }
        return top.toString();
    }

    private String createBottomLine() {
        StringBuilder bottom = new StringBuilder();
        bottom.append("\n");
        for (int i = 1; i <= tableWidth; i++) {
            if (i == START_POSITION)
                bottom.append(BOTTOM_LEFT);
            if (i == tableWidth)
                bottom.append(BOTTOM_RIGHT);
            else if (i % colWidth == 0)
                bottom.append(BOTTOM_MIDDLE);
            else
                bottom.append(MID);
        }
        return bottom.toString();
    }

    private String createTableStructure() {
        StringBuilder tableData = new StringBuilder();
        for (int j = 1; j <= rowCount; j++) {
            tableData.append("\n" + VERTICAL_SEPARATOR);
            for (int i = 1; i < tableWidth; i++) {
                if (i % colWidth == 0)
                    tableData.append(VERTICAL_SEPARATOR);
                else
                    tableData.append(" ");
            }
            tableData.append(VERTICAL_SEPARATOR);
            if (j == rowCount)
                break;
            tableData.append(createRowSeparator());
        }
        return tableData.toString();
    }

    private String createTableStructure(String[] headers) {
        StringBuilder tableHeader = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 1; j <= colCount; j++) {
                if (j == START_POSITION) {
                    tableHeader.append("\n" + createCellWithData(headers[count++]));
                } else
                    tableHeader.append(createCellWithoutData());
            }
            tableHeader.append(VERTICAL_SEPARATOR);
            if (i != rowCount)
                tableHeader.append(createRowSeparator());
        }
        return tableHeader.toString();
    }

    private String createTableStructure(List<String[]> data) {
        StringBuilder tableData = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            int count = 0;
            tableData.append("\n");
            for (int j = 0; j < colCount; j++) {
                tableData.append(createCellWithData(data.get(count++)[i]));
            }
            tableData.append(VERTICAL_SEPARATOR);
            if (i != rowCount - 1)
                tableData.append(createRowSeparator());
        }
        return tableData.toString();
    }

    public StringBuilder createCellWithData(String data) {
        StringBuilder cellData = new StringBuilder();
        cellData.append(VERTICAL_SEPARATOR + " ");
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
        builder.append(VERTICAL_SEPARATOR);
        for (int i = 1; i <= colWidth - 1; i++)
            builder.append(" ");
        return builder;
    }

    private String createRowSeparator() {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("\n" + LEFT_MID);
        for (int i = 1; i < tableWidth; i++) {
            if (i % colWidth == 0)
                rowSeparator.append(MID_MID);
            else
                rowSeparator.append(MID);
        }
        rowSeparator.append(RIGHT_MID);
        return rowSeparator.toString();
    }
}