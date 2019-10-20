package com.xebia.fs101.xtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableLayoutManager {

    private int tableWidth;
    private int rowCount;
    private int colCount;
    private int colWidth;
    private List<String[]> rows=new ArrayList<>();

    TableLayoutManager(int rowCount, int colCount) {
        //tableWidth = TableConstants.maxColWidth * colCount;
        this.rowCount = rowCount;
        this.colCount = colCount;

    }

    private String createTopLine(int tableWidth) {
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

    private String createBottomLine(int tableWidth) {
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

    private String createRowSeparator(int tableWidth) {
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
    private String createTabularStruct(List<String[]> rows,int tableWidth) {
        StringBuilder tableData = new StringBuilder();
        boolean flag=false;
        for(int i=0;i<rowCount;i++)
        {
            tableData.append("\n" + TableConstants.verticalSeparator);
            if(rows.size()==0) {
                for(int j=1;j<=colCount;j++){
                    tableData.append(createRowWithoutData());
                }
                if(i== rowCount-1)
                    break;
                tableData.append(createRowSeparator(tableWidth));
                continue;
            }
            String[] cells=rows.get(i);
            for(int j=0;j<cells.length;j++)
            {
                for(int k=0;k<colWidth;)
                {
                    tableData.append(" ").append(cells[j]);
                    k=k+cells[j].length()+2;
                    while(k<colWidth){
                        tableData.append(" ");
                            k++;
                    }
                }
                tableData.append(TableConstants.verticalSeparator);
            }
            if(i== rowCount-1)
                break;
            tableData.append(createRowSeparator(tableWidth));
        }
        return tableData.toString();
    }



    private  StringBuilder createRowWithoutData() {
        StringBuilder cellData=new StringBuilder();
        for(int k=0;k<colWidth-1;k++)
        {
            cellData.append(" ");
        }
        cellData.append(TableConstants.verticalSeparator);
        return cellData;

    }

    public String createHorizontalTable(List<String[]> rows) {
        if(rows.size()==0)

            colWidth=20;
        else
            colWidth=computeWidth(rows)+2;
        tableWidth=(colWidth)*colCount;
        //return  this.createTopLine(tableWidth)+this.createRowSeparator(tableWidth)+this.createBottomLine(tableWidth);
        return this.createTopLine(tableWidth) + this.createTabularStruct(rows,tableWidth) + this.createBottomLine(tableWidth);
    }

    public int computeWidth(List<String[]> rows)
    {
        int maxWidth=Integer.MIN_VALUE;

        for (String[] cells:rows)
        {
            for(int i=0;i<cells.length;i++)
            {

               maxWidth=Math.max(maxWidth,cells[i].length());
            }
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        TableLayoutManager tableLayoutManager=new TableLayoutManager(4,3);
        String[] celldata1={"one", "two", "three"};
        String[] celldata2={"super", "broccoli", "flexible"};
        String[] celldata3={"assumption", "announcement", "reflection"};
        String[] celldata4={"logic", "pleasant", "wild"};
        List<String[]> rowdata=new ArrayList<>();
        rowdata.add(celldata1);
        rowdata.add(celldata2);
        rowdata.add(celldata3);
        rowdata.add(celldata4);
        String table=tableLayoutManager.createHorizontalTable(rowdata);
        System.out.println(table);
           }

    public String createVerticalTable(List<String[]> rows) {
        return "";
    }
}