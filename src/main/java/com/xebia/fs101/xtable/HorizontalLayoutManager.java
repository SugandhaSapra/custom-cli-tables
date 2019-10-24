package com.xebia.fs101.xtable;

import java.util.List;

import static com.xebia.fs101.xtable.TableConstants.PADDING;
import static com.xebia.fs101.xtable.TableConstants.VERTICAL_SEPARATOR;

public class HorizontalLayoutManager extends LayoutManager {


    HorizontalLayoutManager(int rowCount, int colCount) {
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
        for(int i=0;i<rowCount;i++) {
            tableHeader.append("\n");
            for(int j=0;j<colCount;j++){
                if(i==START_POSITION) {
                    if(!(headers[j].length()<columnWidth[j]-1))
                    {
                        currentData=replaceWith(headers[j],columnWidth[j]-1);
                        tableHeader.append( createCellWithData(currentData,columnWidth[j]));
                    }
                    else
                    tableHeader.append(createCellWithData(headers[j], columnWidth[j]));
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

    private String createTableStructure(List<String[]> rows,int[] columnWidth) {
        StringBuilder tableData = new StringBuilder();
        String currentData=null;
        for(int i=0;i<rowCount;i++) {
            tableData.append("\n");
            for(int j=0;j<colCount;j++){
                if(!(rows.get(i)[j].length()<columnWidth[j]-1))
                {
                    currentData=replaceWith(rows.get(i)[j],columnWidth[j]-1);
                    tableData.append(createCellWithData(currentData,columnWidth[j]));
                }
                else

                tableData.append(createCellWithData(rows.get(i)[j],columnWidth[j]));
            }
            tableData.append(VERTICAL_SEPARATOR);
            if(i!=rowCount-1)
                tableData.append(createRowSeparator(columnWidth));
        }
        return tableData.toString();

    }


    private void validate(String[] headers) {
        if (headers != null && headers.length != colCount)
            throw new IllegalArgumentException("Please pass according to number of rows");
    }

    private void validate(List<String[]> rows) {
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