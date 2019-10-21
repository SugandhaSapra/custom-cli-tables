package com.xebia.fs101.xtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table {

    private int rowCount;
    private int colCount;
    private LayoutManager layoutManager;
    private Renderer renderer;
    private String[] headers;
    private List<String[]> rows = new ArrayList<>();

    private Table(Builder builder) {
        rowCount = builder.rowCount;
        colCount = builder.colCount;
        layoutManager = new HorizontalLayoutManager(rowCount, colCount);
        renderer = new ConsoleBaseRenderer();
        rows = builder.rows;
        headers = builder.headers;
    }

    public void renderTable() {
        renderer.printTable(generateTable());
    }

    public String getShape() {
        return rowCount + " rows X " + colCount + " cols";
    }


    public String generateTable() {

        if (headers != null || rows != null) {
            if (headers != null && rows != null) {
                rows.add(0, headers);
                validateRowsAndCols();
                return layoutManager.createDataTable(rows);
            } else if (headers != null && rows == null) {
                validateRowsAndCols();
                return layoutManager.createTableWithOnlyHeaders(headers);
            } else {
                validateRowsAndCols();
                return layoutManager.createDataTable(rows);
            }

        } else
            validateRowsAndCols();
        return layoutManager.createTable();

    }

    private void validateRowsAndCols() {
        if (rowCount < 0 || colCount < 0)
            throw new IllegalArgumentException("Row and Col should be greater than 0");
        if (headers != null && headers.length != colCount)
            throw new IllegalArgumentException("Please pass according to the number of cols");
        if (rows != null && rows.size() != rowCount)
            throw new IllegalArgumentException("Please pass according to the number of rows");
        if (rows != null) {
            for (String cells[] : rows) {
                if (cells.length != colCount)
                    throw new IllegalArgumentException("Please pass according to the number of rows");
            }
        }
    }


    public static final class Builder {
        private int rowCount;
        private int colCount;
        private LayoutManager layoutManager;
        private Renderer renderer;
        private List<String[]> rows;
        private String[] headers;

        public Builder() {
        }
        /*public Builder withLayoutManager(LayoutManager val) {
            layoutManager=val;
            return  this;
        }*/
        public Builder withRowCount(int val) {
            rowCount = val;
            return this;
        }

        public Builder withColCount(int val) {
            colCount = val;
            return this;
        }

        public Builder withHeader(String[] val) {
            headers = val;
            return this;
        }

        public Builder withRenderer(Renderer val) {
            renderer = val;
            return this;
        }

        public Builder withRows(List<String[]> val) {
            rows = val;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }

    public static void main(String[] args) {
        System.out.println("Please enter the number of rows and");
        Scanner sc=new Scanner(System.in);
        int rows=sc.nextInt();
        int columns=sc.nextInt();
        System.out.println("Please press 1 for horizontal table and 2 for vertical table");
        int choice=sc.nextInt();
        if(choice==2)
        {
            VerticalLayoutManager verticalLayoutManager=new VerticalLayoutManager(rows,columns);
            System.out.println("Do you want table with data :y/n");
            char withData=sc.next().charAt(0);
            if(withData=='y'|| withData=='Y')
            {


            }
            else{
                 String verticalTable=verticalLayoutManager.createTable();
                System.out.println(verticalTable);
            }

        }
        else {
            HorizontalLayoutManager horizontalLayoutManager=new HorizontalLayoutManager(rows,columns);
            System.out.println("Do you want table with data :y/n");
            char withData=sc.next().charAt(0);
            if(withData=='y'|| withData=='Y')
            {


            }
            else{
                String verticalTable=horizontalLayoutManager.createTable();
                System.out.println(verticalTable);
            }
        }
    }
}