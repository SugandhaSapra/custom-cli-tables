package com.xebia.fs101.xtable.layout_manager;

import java.util.List;

import static com.xebia.fs101.xtable.layout_manager.TableConstants.*;

public class HorizontalLayoutManager extends LayoutManager {


    public HorizontalLayoutManager(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.columnWidths = initializeColWidths();
    }

    HorizontalLayoutManager(int rowCount, int colCount, int[] colWidth) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.columnWidths = setColumnWidthToDefault(colWidth);
    }

    public String createDataTable(List<String[]> rows) {

        validate(rows);
        return this.createTopLine() + this.createTableStructure(rows) + this.createBottomLine();
    }

    public String createTableWithHeadersOnly(String[] headers) {
        validate(headers);
        return this.createTopLine() + this.createTableStructure(headers) + this.createBottomLine();

    }

    private String createTableStructure(String[] headers) {
        StringBuilder tableHeader = new StringBuilder();
        String currentData = null;
        for (int i = 0; i < rowCount; i++) {
            tableHeader.append("\n");
            for (int j = 0; j < colCount; j++) {
                if (i == START_POSITION) {
                    if (!(headers[j].length() < columnWidths[j] - 1)) {
                        currentData = replaceWith(headers[j], columnWidths[j] - 1);
                        tableHeader.append(createCellWithData(currentData, columnWidths[j]));
                    } else
                        tableHeader.append(createCellWithData(headers[j], columnWidths[j]));
                } else
                    tableHeader.append(createCellWithoutData(columnWidths[j]));
            }
            tableHeader.append(VERTICAL_SEPARATOR);
            if (i != rowCount - 1)
                tableHeader.append(createRowSeparator());
        }
        return tableHeader.toString();

    }

    private String createTableStructure(List<String[]> rows) {
        StringBuilder tableData = new StringBuilder();
        String currentData = null;
        for (int i = 0; i < rowCount; i++) {
            tableData.append("\n");
            for (int j = 0; j < colCount; j++) {
                if (!(rows.get(i)[j].length() < columnWidths[j] - 1)) {
                    currentData = replaceWith(rows.get(i)[j], columnWidths[j] - 1);
                    tableData.append(createCellWithData(currentData, columnWidths[j]));
                } else

                    tableData.append(createCellWithData(rows.get(i)[j], columnWidths[j]));
            }
            tableData.append(VERTICAL_SEPARATOR);
            if (i != rowCount - 1)
                tableData.append(createRowSeparator());
        }
        return tableData.toString();

    }


    private void validate(String[] headers) {
        validate();
        if (headers != null && headers.length != colCount)
            throw new IllegalArgumentException("Please pass according to number of rows");
    }

    private void validate(List<String[]> rows) {
        validate();
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