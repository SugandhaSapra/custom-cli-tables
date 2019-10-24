package com.xebia.fs101.xtable.layout_manager;

import java.util.List;

import static com.xebia.fs101.xtable.layout_manager.TableConstants.*;

public class VerticalLayoutManager extends LayoutManager {

    public VerticalLayoutManager(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.columnWidths = initializeColWidths();
    }

    VerticalLayoutManager(int rowCount, int colCount, int[] colWidth) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.columnWidths = setColumnWidthToDefaultForLessThan5(colWidth);
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
            for (int j = 0; j < colCount; j++) {
                if (j == START_POSITION) {
                    if (!(headers[i].length() < columnWidths[j] - 1)) {
                        currentData = replaceWith(headers[i], columnWidths[j] - 1);
                        tableHeader.append("\n" + createCellWithData(currentData, columnWidths[j]));
                    } else
                        tableHeader.append("\n" + createCellWithData(headers[i], columnWidths[j]));
                } else
                    tableHeader.append(createCellWithoutData(columnWidths[j]));
            }
            tableHeader.append(VERTICAL_SEPARATOR);
            if (i != rowCount - 1)
                tableHeader.append(createRowSeparator());
        }
        return tableHeader.toString();
    }

    private String createTableStructure(List<String[]> data) {
        StringBuilder tableData = new StringBuilder();
        String currentData = null;
        for (int i = 0; i < rowCount; i++) {
            tableData.append("\n");
            for (int j = 0; j < colCount; j++) {
                if (!(data.get(j)[i].length() < columnWidths[j] - 1)) {
                    currentData = replaceWith(data.get(j)[i], columnWidths[j] - 1);
                    tableData.append(createCellWithData(currentData, columnWidths[j]));
                } else

                    tableData.append(createCellWithData(data.get(j)[i], columnWidths[j]));
            }
            tableData.append(VERTICAL_SEPARATOR);
            if (i != rowCount - 1)
                tableData.append(createRowSeparator());
        }
        return tableData.toString();
    }

    private void validate(String[] headers) {
        validate();
        if (headers != null && headers.length != rowCount)
            throw new IllegalArgumentException("Please pass according to number of rows");
    }

    private void validate(List<String[]> rows) {
        validate();
        if (rows != null && rows.size() != colCount)
            throw new IllegalArgumentException("Please pass according to the number of cols");
        if (rows != null) {
            for (String cells[] : rows) {
                if (cells.length != rowCount)
                    throw new IllegalArgumentException("Please pass according to the number of rows");
            }
        }
    }
}
