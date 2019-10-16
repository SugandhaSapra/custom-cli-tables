package com.xebia.fs101.xtable;

public class Table {

    int rows;
    int columns;

    int columnWidth;
    String data="Nimrat";
    int cellWidth=10;
    Table(int rows, int columns)
    {
        this.rows=rows;
        this.columns=columns;
        columnWidth=10*columns;
    }

    public void printTable() {
        printTopLine();
        printMidLines();
        printBottomLine();
    }
    public void printData() {
        int len=cellWidth-data.length()-2;
        for(int i=1;i<columns*cellWidth;i++)
        {
            if(i==1) {
                System.out.print("| " + data);
                int temp=len;
                while(temp>0)
                {
                    temp--;
                    System.out.print(" ");
                }
                System.out.print("");
            }
            else if(i%cellWidth==0) {
                int temp=len;
                System.out.print("| " + data);
                while(temp>0)
                {
                    temp--;
                    System.out.print(" ");
                }
            }

        }
        System.out.print("|");

    }

    public void printTopLine() {
        int columnWidth = 10 * columns;
        for (int i = 1; i <= columnWidth; i++) {
            if (i == 1)
                System.out.print(topLeft);
            if (i == columnWidth)
                System.out.print(topRight);
            else if (i % 10 == 0)
                System.out.print(topMiddle);
            else
                System.out.print(mid);
        }
        System.out.println();
        printData();
    }

    public void printMidLines() {

        for (int j = 1; j < rows; j++) {
            System.out.print("\n" + leftMid);
            for (int i = 1; i < columnWidth; i++) {
                if (i % 10 == 0)
                    System.out.print(midMid);
                else
                    System.out.print(mid);
            }
            System.out.print(rightMid);
            System.out.println();
            printData();
        }
        System.out.println();
    }

    public void printBottomLine() {

        for (int i = 1; i <= columnWidth; i++) {
            if (i == 1)
                System.out.print(bottomLeft);
            if (i == columnWidth)
                System.out.print(bottomRight);
            else if (i % 10 == 0)
                System.out.print(bottomMiddle);
            else
                System.out.print(mid);
        }
    }
}