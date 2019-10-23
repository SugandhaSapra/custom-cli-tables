package com.xebia.fs101.xtable;

import java.util.List;
import static com.xebia.fs101.xtable.TableConstants.*;

public abstract class LayoutManager {
    protected int tableWidth;
    public int rowCount;
    public int colCount;
    protected int colWidth;
    protected final int START_POSITION=0;

    public String createTable(int[]  columnWidth)
    {
        validate();
        return this.createTopLine(columnWidth)+ this.createTableStructure(columnWidth) + this.createBottomLine(columnWidth);
    }
    public abstract String createTableWithHeadersOnly(String[] headers,int[] columnWidth);

    public abstract String createDataTable(List<String[]> rows,int[] columnWidth);

    public String createTopLine(int[] columnWidth) {
        StringBuilder top = new StringBuilder();
        top.append(TOP_LEFT);
        for(int i=0;i<colCount;i++)
        {
            for(int j=0;j<columnWidth[i]-1;j++){
                top.append(MID);
            }
            if(i!=colCount-1)
            top.append(TOP_MIDDLE);
        }
        top.append(TOP_RIGHT);
        return top.toString();

    }
    protected String createBottomLine(int[] columnWidth) {
        StringBuilder bottom = new StringBuilder();
        bottom.append("\n");
        bottom.append(BOTTOM_LEFT);
        for(int i=0;i<colCount;i++)
        {
            for(int j=0;j<columnWidth[i]-1;j++)
            {
                bottom.append(MID);
            }
            if(i!=colCount-1)
                bottom.append(BOTTOM_MIDDLE);
        }
        bottom.append(BOTTOM_RIGHT);
        return bottom.toString();
    }

    protected String createTableStructure(int[] columnWidth) {
        StringBuilder tableData = new StringBuilder();
        for(int i=0;i<rowCount;i++) {
            tableData.append("\n"+VERTICAL_SEPARATOR);
            for(int j=0;j<colCount;j++) {
                for(int k=0;k<columnWidth[j]-1;k++) {
                    tableData.append(" ");
                }
                    tableData.append(VERTICAL_SEPARATOR);
            }
            if(i!=rowCount-1)
            tableData.append(createRowSeparator(columnWidth));
        }
        return tableData.toString();

    }

    protected String createRowSeparator(int[] columnWidth) {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("\n" + LEFT_MID);
        for(int i=0;i<colCount;i++)
        {
            for(int j=0;j<columnWidth[i]-1;j++)
            {
                rowSeparator.append(MID);
            }
            if(i!=colCount-1)
            rowSeparator.append(MID_MID);
        }
        rowSeparator.append(RIGHT_MID);
        return rowSeparator.toString();
    }

    private void validate()
    {
        if(rowCount<0 ||colCount<0)
            throw new IllegalArgumentException("Row and col should be greater than 0");
    }
    protected String replaceWith(String currentData, int colWidth) {
        //the minimum width of column should be five
        String trimData=currentData.substring(0,colWidth-3);
        StringBuilder data=new StringBuilder(trimData);
        data.append("..");
        return data.toString();
    }

}