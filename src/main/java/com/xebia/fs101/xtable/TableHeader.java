package com.xebia.fs101.xtable;

public class TableHeader {
    private int  columnWidth=10;
    int cellWidth=0;
    String data="Nimrat";



    public String printTopLine(int columns) {
        String header="";
        int columnWidth = 10 * columns;
        for (int i = 1; i <= columnWidth; i++) {
            if (i == 1)
                header+= Consts.topLeft;
                //System.out.print(TableGraphicsConst.topLeft);
            if (i == columnWidth)
                header+=Consts.topRight;
                //System.out.print(TableGraphicsConst.topRight);
            else if (i % 10 == 0)
                header+=Consts.topMiddle;
             //   System.out.print(Consts.topMiddle);
            else
                header+=Consts.mid;
                //System.out.print(Consts.mid);
        }
        //System.out.println();
        return header;
    }
    public void printData(int columns) {
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



    /*public void printMidLines(int rows) {

        for (int j = 1; j < rows; j++) {
            System.out.print("\n" + Consts.leftMid);
            for (int i = 1; i < columnWidth; i++) {
                if (i % 10 == 0)
                    System.out.print(Consts.midMid);
                else
                    System.out.print(Consts.mid);
            }
            System.out.print(Consts.rightMid);
            System.out.println();
            printData();
        }
        System.out.println();
    }*/

    public String printBottomLine(int columns) {
        String footer="";
        columnWidth=columns*columnWidth;
        for (int i = 1; i <= columnWidth; i++) {
            if (i == 1)
              footer+=Consts.bottomLeft;
                //System.out.print(Consts.bottomLeft);
            if (i == columnWidth)
                footer+=Consts.bottomRight;
                //System.out.print(Consts.bottomRight);
            else if (i % 10 == 0)
                footer+=Consts.bottomMiddle;
                //System.out.print(Consts.bottomMiddle);
            else
                footer+=Consts.mid;
                //System.out.print(Consts.mid);
        }
        return footer;
    }
}
