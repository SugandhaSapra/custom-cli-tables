package com.xebia.fs101.xtable;

import java.util.List;

import static com.xebia.fs101.xtable.TableConstants.*;

public class VerticalLayoutManager extends LayoutManager {

    VerticalLayoutManager(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }


    @Override
    public String createDataTable(List<String[]> rows,int[] columnWidth) {
        validate(rows);
        return this.createTopLine(columnWidth) + this.createTableStructure(rows,columnWidth) + this.createBottomLine(columnWidth);
    }

    @Override
    public String createTableWithHeadersOnly(String[] headers,int[] columnWidth) {
        validate(headers);
        return this.createTopLine(columnWidth) + this.createTableStructure(headers,columnWidth) + this.createBottomLine(columnWidth);
    }

    private String createTableStructure(String[] headers,int[] columnWidth) {
        StringBuilder tableHeader = new StringBuilder();
        String currentData=null;
        for (int i = 0; i <rowCount; i++)
        {
            for (int j = 0; j <colCount; j++)
            {
                if (j == START_POSITION) {
                    if(!(headers[i].length()<columnWidth[j]-1))
                    {
                        currentData=replaceWith(headers[i],columnWidth[j]-1);
                        tableHeader.append( "\n" + createCellWithData(currentData,columnWidth[j]));
                    }
                    else
                        tableHeader.append( "\n" + createCellWithData(headers[i],columnWidth[j]));
                }
                else
                    tableHeader.append(createCellWithoutData(columnWidth[j]));
            }
            tableHeader.append(VERTICAL_SEPARATOR);
            if(i!=rowCount-1)
                tableHeader.append(createRowSeparator(columnWidth));
        }
        return tableHeader.toString();
    }

    private String createTableStructure(List<String[]> data,int[] columnWidth) {
        StringBuilder tableData = new StringBuilder();
        String currentData=null;
        for (int i = 0; i < rowCount; i++) {
            tableData.append("\n");
            for (int j = 0; j < colCount; j++) {
                if(!(data.get(j)[i].length()<columnWidth[j]-1))
                {
                    currentData=replaceWith(data.get(j)[i],columnWidth[j]-1);
                    tableData.append(createCellWithData(currentData,columnWidth[j]));
                }
                else

                tableData.append(createCellWithData(data.get(j)[i],columnWidth[j]));
            }
            tableData.append(VERTICAL_SEPARATOR);
            if (i != rowCount - 1)
                tableData.append(createRowSeparator(columnWidth));
        }
        return tableData.toString();
    }

    private  StringBuilder createCellWithData(String data,int colWidth) {
        StringBuilder cellData = new StringBuilder();
        cellData.append(TableConstants.VERTICAL_SEPARATOR + " ");
        int spaceLeft = colWidth - data.length();
        cellData.append(data);
        for (int i = 2; i < spaceLeft - 1; i++)
            cellData.append(" ");
        if (data.length() != colWidth - 2)
            cellData.append(" ");
        return cellData;
    }

    private StringBuilder createCellWithoutData(int colWidth) {
        StringBuilder builder = new StringBuilder();
        builder.append(TableConstants.VERTICAL_SEPARATOR);
        for (int i = 1; i <= colWidth - 1; i++)
            builder.append(" ");
        return builder;
    }

    private void validate(String[] headers) {
        if (headers != null && headers.length != rowCount)
            throw new IllegalArgumentException("Please pass according to number of rows");
    }

    private void validate(List<String[]> rows) {
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