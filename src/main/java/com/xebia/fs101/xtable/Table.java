package com.xebia.fs101.xtable;

public class Table {

    private  TableLayoutManager tableLayoutManager;

    public Table(int rowCount, int colCount) {
        tableLayoutManager= new TableLayoutManager(rowCount, colCount);

    }

    public void printTable() {

         System.out.print(tableLayoutManager.createTable());
    }
    public String renderTable()
    {
        return tableLayoutManager.createTable();
    }

}