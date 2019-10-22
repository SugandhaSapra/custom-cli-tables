package com.xebia.fs101.xtable;

import java.util.List;
import java.util.Map;

import static com.xebia.fs101.xtable.TableConstants.*;

public class CustomHorizontalLayoutManager implements CustomLayoutManager {

    private int tableWidth;
    private int rowCount;
    private int colCount;
    private int colWidth;
    private Map<Integer, Integer> columnWidth;
    private final int START_POSITION = 1;

    CustomHorizontalLayoutManager(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    @Override
    public String createTable(Map<Integer, Integer> columnWidth) {
        return this.createTopLine(columnWidth) + this.createTableStructure(columnWidth) + this.createBottomLine(columnWidth);
    }

    @Override
    public String createTableWithHeadersOnly(String[] headers, Map<Integer, Integer> columnWidth) {
        return this.createTopLine(columnWidth) + this.createTableStructure(headers, columnWidth) + this.createBottomLine(columnWidth);
    }

    @Override
    public String createTableWithHeadersAndData(List<String[]> rows, Map<Integer, Integer> columnWidth) {
        return this.createTopLine(columnWidth) + this.createTableStructure(rows,columnWidth) + this.createBottomLine(columnWidth);
    }

    private String createTableStructure(List<String[]> rows, Map<Integer, Integer> columnWidth) {
        StringBuilder tableData = new StringBuilder();
        String data=null;
        for (int i = 1; i <= rowCount; i++) {
            tableData.append("\n" + VERTICAL_SEPARATOR);
            String[] cells = rows.get(i-1);
            for (int j = 1; j <= colCount; j++) {
                if(! (cells[j-1].length()<columnWidth.get(j)-2))
                {

                    data=replaceWith(cells[j-1],columnWidth.get(j));
                    tableData.append(" "+data);
                }
                else
                {
                    tableData.append(" ").append(cells[j-1]);
                }
                for (int k = 0; k < columnWidth.get(j)-1; ) {


                    k = k + cells[j-1].length() + PADDING;
                    while (k < columnWidth.get(j)-1) {
                        tableData.append(" ");
                        k++;
                    }
                }
                tableData.append(VERTICAL_SEPARATOR);
            }
            if(i!=rowCount)
            tableData.append(createRowSeparator(columnWidth));
        }
        return tableData.toString();
    }

    private String replaceWith(String currentData, int colWidth) {
        String trimData=currentData.substring(0,colWidth-5);
        StringBuilder data=new StringBuilder(trimData);

        data.append("..");
        return data.toString();


    }

    private String createTableStructure(String[] headers, Map<Integer, Integer> columnWidth) {
        StringBuilder tableData = new StringBuilder();
        tableData.append("\n" + VERTICAL_SEPARATOR);
        for(int i=1;i<=headers.length;i++)
        {
            for (int k = 1; k <=columnWidth.get(i)-1; ) {
                if(k==START_POSITION)
                tableData.append(" ").append(headers[i-1]);
                k = k + headers[i-1].length() + 2;
                while (k < columnWidth.get(i)) {
                    tableData.append(" ");
                    k++;
                }

            }
            tableData.append(VERTICAL_SEPARATOR);
        }
        tableData.append(createRowSeparator(columnWidth));
        rowCount--;
        tableData.append(createTableStructure(columnWidth));
        return tableData.toString();
    }

    private String createTableStructure(Map<Integer, Integer> columnWidth) {
        StringBuilder tableData = new StringBuilder();
        for (int i = 1; i <= rowCount; i++) {
            tableData.append("\n" + VERTICAL_SEPARATOR);
            for (int j = 1; j <= colCount; j++) {
                for (int k = 1; k <= columnWidth.get(j) - 2; k++) {
                    tableData.append(" ");
                }
                tableData.append(VERTICAL_SEPARATOR);
            }
            if (i != rowCount)
                tableData.append(createRowSeparator(columnWidth));
        }

        return tableData.toString();
    }


    private String createRowSeparator(Map<Integer, Integer> columnWidth) {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("\n" + LEFT_MID);
        for (int i = 1; i <= colCount; i++) {
            for (int j = 1; j < columnWidth.get(i) - 1; j++) {
                rowSeparator.append(MID);
            }
            if (i != colCount)
                rowSeparator.append(MID_MID);
        }
        rowSeparator.append(RIGHT_MID);

        return rowSeparator.toString();
    }

    private String createBottomLine(Map<Integer, Integer> columnWidth) {
        StringBuilder bottom = new StringBuilder();
        bottom.append("\n");
        bottom.append(BOTTOM_LEFT);
        for (int i = 1; i <= colCount; i++) {
            for (int j = 1; j < columnWidth.get(i) - 1; j++) {
                bottom.append(MID);
            }
            if (i != colCount)
                bottom.append(BOTTOM_MIDDLE);
        }
        bottom.append(BOTTOM_RIGHT);
        return bottom.toString();
    }

    private String createTopLine(Map<Integer, Integer> columnWidth) {
        StringBuilder top = new StringBuilder();
        top.append(TOP_LEFT);
        for (int i = 1; i <= colCount; i++) {
            for (int j = 1; j < columnWidth.get(i) - 1; j++) {
                top.append(MID);
            }
            if (i != colCount)
                top.append(TOP_MIDDLE);
        }
        top.append(TOP_RIGHT);
        return top.toString();
    }

}
