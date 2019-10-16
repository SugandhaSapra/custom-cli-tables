package com.xebia.fs101.xtable;

public class TableGenerator implements TableGraphicsConst{

    private int rows;
    private int columns;
    private int columnWidth;
    public TableGenerator(int rows, int columns)
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
<<<<<<< HEAD
}
=======
}
>>>>>>> f2667c0492f6debdfc63fa7f12ed00fe562fc013
