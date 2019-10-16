package com.xebia.fs101.xtable;


public class TableLayoutManager {

    private int tableWidth;
    private int rowCount;
    private int colCount;

    TableLayoutManager(int rowCount, int colCount) {
        tableWidth = TableConst.maxColWidth * colCount;
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    public String getTopLine() {
        StringBuilder top = new StringBuilder();
        for (int i = 1; i <= tableWidth; i++) {
            if (i == 1)
                top.append(TableConst.topLeft);
            if (i == tableWidth)
                top.append(TableConst.topRight);
            else if (i % TableConst.maxColWidth == 0)
                top.append(TableConst.topMiddle);
            else
                top.append(TableConst.mid);
        }
        return top.toString();

    }

    public String getBottomLine() {
        StringBuilder bottom = new StringBuilder();
        bottom.append("\n");
        for (int i = 1; i <= tableWidth; i++) {
            if (i == 1)
                bottom.append(TableConst.bottomLeft);
            if (i == tableWidth)
                bottom.append(TableConst.bottomRight);
            else if (i % TableConst.maxColWidth == 0)
                bottom.append(TableConst.bottomMiddle);
            else
                bottom.append(TableConst.mid);
        }
        return bottom.toString();
    }

    public String getRowSeparator() {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("\n" + TableConst.leftMid);
        for (int i = 1; i < tableWidth; i++) {
            if (i % TableConst.maxColWidth == 0)
                rowSeparator.append(TableConst.midMid);
            else
                rowSeparator.append(TableConst.mid);
        }
        rowSeparator.append(TableConst.rightMid);
        return rowSeparator.toString();
    }

    public String getTableData() {
        StringBuilder tableData = new StringBuilder();

        for (int j = 1; j <= rowCount; j++) {
            tableData.append("\n" + TableConst.verticalSeparator);
            for (int i = 1; i < tableWidth; i++) {
                if (i % TableConst.maxColWidth == 0)
                    tableData.append(TableConst.verticalSeparator);
                else
                    tableData.append(" ");
            }
            tableData.append(TableConst.verticalSeparator);
            if (j == rowCount)
                break;
            tableData.append(getRowSeparator());
        }

        return tableData.toString();

    }
    public String renderTable() {
        return this.getTopLine() + this.getTableData() + this.getBottomLine();
    }


}
