package com.xebia.fs101.xtable;

public class Table {

    private int rows;
    private int columns;
    private int columnWidth;

    public Table(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        columnWidth = 10 * columns;
    }

    public void printTable() {
        printTopLine();
        printMidLines();
        printBottomLine();
    }

    public void printTopLine() {
        int columnWidth = 10 * columns;
        for (int i = 1; i <= columnWidth; i++) {
            if (i == 1)
                System.out.print(Consts.topLeft);
            if (i == columnWidth)
                System.out.print(Consts.topRight);
            else if (i % 10 == 0)
                System.out.print(Consts.topMiddle);
            else
                System.out.print(Consts.mid);
        }
    }

    public void printMidLines() {

        for (int j = 1; j < rows; j++) {
            System.out.print("\n" + Consts.leftMid);
            for (int i = 1; i < columnWidth; i++) {
                if (i % 10 == 0)
                    System.out.print(Consts.midMid);
                else
                    System.out.print(Consts.mid);
            }
            System.out.print(Consts.rightMid);
        }
        System.out.println();
    }

    public void printBottomLine() {

        for (int i = 1; i <= columnWidth; i++) {
            if (i == 1)
                System.out.print(Consts.bottomLeft);
            if (i == columnWidth)
                System.out.print(Consts.bottomRight);
            else if (i % 10 == 0)
                System.out.print(Consts.bottomMiddle);
            else
                System.out.print(Consts.mid);
        }
    }
}