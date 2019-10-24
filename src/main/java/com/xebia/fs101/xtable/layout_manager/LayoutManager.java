package com.xebia.fs101.xtable.layout_manager;

import java.util.List;

import static com.xebia.fs101.xtable.layout_manager.TableConstants.*;

public abstract class LayoutManager {
    protected int rowCount;
    protected int colCount;
    protected int[] columnWidths;
    protected final int START_POSITION = 0;

    public abstract String createTableWithHeadersOnly(String[] headers);


    public abstract String createDataTable(List<String[]> rows);

    public String createTable() {
        validate();
        return this.createTopLine() + this.createTableStructure() + this.createBottomLine();
    }

    protected String createTopLine() {
        StringBuilder top = new StringBuilder();
        top.append(TOP_LEFT);
        for (int i = 0; i < colCount; i++) {
            for (int j = 0; j < columnWidths[i] - 1; j++) {
                top.append(MID);
            }
            if (i != colCount - 1)
                top.append(TOP_MIDDLE);
        }
        top.append(TOP_RIGHT);
        return top.toString();

    }

    protected String createBottomLine() {
        StringBuilder bottom = new StringBuilder();
        bottom.append("\n");
        bottom.append(BOTTOM_LEFT);
        for (int i = 0; i < colCount; i++) {
            for (int j = 0; j < columnWidths[i] - 1; j++) {
                bottom.append(MID);
            }
            if (i != colCount - 1)
                bottom.append(BOTTOM_MIDDLE);
        }
        bottom.append(BOTTOM_RIGHT);
        return bottom.toString();
    }

    protected String createTableStructure() {
        StringBuilder tableData = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            tableData.append("\n" + VERTICAL_SEPARATOR);
            for (int j = 0; j < colCount; j++) {
                for (int k = 0; k < columnWidths[j] - 1; k++) {
                    tableData.append(SPACING_CHARACTERS);
                }
                tableData.append(VERTICAL_SEPARATOR);
            }
            if (i != rowCount - 1)
                tableData.append(createRowSeparator());
        }
        return tableData.toString();

    }

    protected String createRowSeparator() {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("\n" + LEFT_MID);
        for (int i = 0; i < colCount; i++) {
            for (int j = 0; j < columnWidths[i] - 1; j++) {
                rowSeparator.append(MID);
            }
            if (i != colCount - 1)
                rowSeparator.append(MID_MID);
        }
        rowSeparator.append(RIGHT_MID);
        return rowSeparator.toString();
    }

    protected void validate() {
        if (rowCount < 0 || colCount < 0)
            throw new IllegalArgumentException("Rows and columns should be greater than 0");
    }

    protected String replaceWith(String currentData, int colWidth) {
        String trimData = currentData.substring(0, colWidth - 3);
        StringBuilder data = new StringBuilder(trimData);
        data.append(TRUNCATING_CHARACTERS);
        return data.toString();
    }

    protected StringBuilder createCellWithData(String data, int colWidth) {
        StringBuilder cellData = new StringBuilder();
        cellData.append(VERTICAL_SEPARATOR + SPACING_CHARACTERS);
        int spaceLeft = colWidth - data.length();
        cellData.append(data);
        for (int i = 2; i < spaceLeft - 1; i++)
            cellData.append(SPACING_CHARACTERS);
        if (data.length() != colWidth - 2)
            cellData.append(SPACING_CHARACTERS);
        return cellData;
    }

    protected StringBuilder createCellWithoutData(int colWidth) {
        StringBuilder builder = new StringBuilder();
        builder.append(VERTICAL_SEPARATOR);
        for (int i = 1; i <= colWidth - 1; i++)
            builder.append(SPACING_CHARACTERS);
        return builder;
    }
    protected int[] initializeColWidths() {
        validate();
        columnWidths = new int[colCount];
        for (int i = 0; i < colCount; i++) {
            columnWidths[i] = MAX_COL_WIDTH;
        }
        return columnWidths;
    }

    protected int[] setColumnWidthToDefaultForLessThan5(int[] colWidth) {
        for (int i = 0; i < colCount; i++) {
            if (colWidth[i] < 5)
                colWidth[i] = MAX_COL_WIDTH;
        }
        return colWidth;
    }
}