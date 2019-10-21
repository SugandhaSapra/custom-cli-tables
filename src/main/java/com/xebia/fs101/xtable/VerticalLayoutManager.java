package com.xebia.fs101.xtable;

import java.util.ArrayList;
import java.util.List;

public class VerticalLayoutManager implements LayoutManager {
    private int tableWidth;
    private int rowCount;
    private int colCount;
    private int colWidth;

    VerticalLayoutManager (int rowCount,int colCount){
        this.rowCount=rowCount;
        this.colCount=colCount;
    }

    @Override
    public String createTable() {
        tableWidth = TableConstants.maxColWidth * colCount;
        colWidth = TableConstants.maxColWidth;
        return this.createTopLine() + this.createTabularStruct() + this.createBottomLine();
    }
    public String createTable(List<String[]> rows) {

        colWidth = computeWidth(rows) + 2;
        System.out.println(colWidth);
        tableWidth = colWidth * colCount;
        return this.createTopLine() + this.createTabularStruct(rows,colWidth) + this.createBottomLine();
    }
    public String createTable(String[] headers) {

        colWidth = computeWidth(headers) + 2;
        tableWidth = (colWidth) * colCount;
        return this.createTopLine() + this.createTabularStruct(headers,colWidth) + this.createBottomLine();

    }



    private int computeWidth(String[] headers) {
        int maxWidth = Integer.MIN_VALUE;

        for (String header : headers) {
            maxWidth = Math.max(maxWidth, header.length());

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
    private String createTopLine(int cellWidth)
    {
        StringBuilder top=new StringBuilder();
        top.append(TableConstants.topLeft);
        for(int i=1;i<=colCount;i++) {
            for(int j=1;j<cellWidth;j++) {
                    top.append(TableConstants.mid);
            }
            if(i!=colCount)
            top.append(TableConstants.topMiddle);
        }
        top.append(TableConstants.topRight);

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

    private int computeWidth(List<String[]> rows) {
        int maxWidth = Integer.MIN_VALUE;
        for (String[] cells : rows) {
            for (int i = 0; i < cells.length; i++) {

                maxWidth = Math.max(maxWidth, cells[i].length());
            }
        }
        return maxWidth;
    }
    private String createTabularStruct(String[] headers,int cellWidth) {
        StringBuilder tableHeader = new StringBuilder();
        //tableHeader.append("\n" + TableConstants.verticalSeparator);
        //int cellWidth=computeWidth(headers);
        int count=0;
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 1; j <= colCount; j++) {
                if(j==1)
                {
                    tableHeader.append("\n" +createCellWithData(cellWidth,headers[count++]));
                }
                else
                    tableHeader.append(createCellWithoutData(cellWidth));

            }
            tableHeader.append(TableConstants.verticalSeparator);
            if(i!=rowCount)
            tableHeader.append(createRowSeparator());
        }
        return tableHeader.toString();
    }

    private String createTabularStruct(List<String[]> data,int cellWidth) {
        StringBuilder tableData = new StringBuilder();
        //int cellWidth=computeWidth(data.get(0));
        //System.out.println("Cell width is "+cellWidth);
        String[] headers=data.get(0);
        int count=0;
        for(int i=0;i<rowCount;i++)
        {
            int k=0;
            String[] row=data.get(i+1);
            for(int j=0;j<colCount;j++)
            {
                if(j==0)
                {
                    tableData.append("\n" +createCellWithData(cellWidth,headers[count++]));
                }
                else
                    tableData.append(createCellWithData(cellWidth,row[k++]));

            }
            tableData.append(TableConstants.verticalSeparator);
            if(i!=rowCount-1)
                tableData.append(createRowSeparator());
        }
        return tableData.toString();

    }
    public StringBuilder createCellWithData(int cellWidth,String data) {
        StringBuilder builder=new StringBuilder();
        builder.append(TableConstants.verticalSeparator+" ");
        int spaceLeft=cellWidth-data.length();
        builder.append(data);
        for(int i=2;i<spaceLeft-1;i++)
            builder.append(" ");
        /*for(int i=data.length();i<=cellWidth;i++)
        {
            builder.append(" ");
        }*/
        if(data.length()!=cellWidth-2)
          builder.append(" ");
       // builder.append(TableConstants.verticalSeparator);
        return builder;
    }
    public StringBuilder createCellWithoutData(int cellWidth) {
        StringBuilder builder=new StringBuilder();
        builder.append(TableConstants.verticalSeparator);
        for(int i=1;i<=cellWidth-1;i++)
            builder.append(" ");
       // builder.append(TableConstants.verticalSeparator);
        return  builder;
    }


    public static void main(String[] args) {
        VerticalLayoutManager verticalLayoutManager=new VerticalLayoutManager(3,4);

        String table=verticalLayoutManager.createTable(new String[]{"one","two","three"});
        System.out.println(table);
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
