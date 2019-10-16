package com.xebia.fs101.xtable;

public class XTable {

        final char horizontalSeparator='-';
        final String verticalSeparator="|";
        final String topLeft="┌";
        final String topRight="┐";
        final String bottomLeft="└";
        final String bottomRight="┘";
        final String topMiddle="┬";
        final String bottomMiddle="┴";
        final String mid="─";

        public void createEmptyTable(int rows,int columns) {
            printHeader(columns);
            printMiddle(rows,columns);
            printFooter(columns);

        }
        public void printHeader(int columns) {
            int columnWidth=13*columns;
            for(int i=1;i<=columnWidth;i++){
                if(i==1)
                    System.out.print(topLeft);
                if(i==columnWidth)
                    System.out.print(topRight);
                else if(i%13==0)
                    System.out.print(topMiddle);
                else
                    System.out.print(mid);
            }
        }

        public void printMiddle(int rows,int column){
            int columnWidth=13*column;

            for(int j=1;j<rows;j++)
            {
                System.out.print("\n"+verticalSeparator);
                for(int i=1;i<columnWidth;i++)
                {
                    if(i%13==0)
                        System.out.print(verticalSeparator);
                    else
                        System.out.print(mid);
                }
                System.out.print(verticalSeparator);
            }
            System.out.println();
        }
        public void printFooter(int columns){
            int columnWidth=13*columns;
            for(int i=1;i<=columnWidth;i++){
                if(i==1)
                    System.out.print(bottomLeft);

                if(i==columnWidth)
                    System.out.print(bottomRight);
                else if(i%13==0)
                    System.out.print(bottomMiddle);
                else
                    System.out.print(mid);
            }

        }

        public static void main(String[] args) {
            //System.out.println("\u001B[1m I am bold");
            new com.xebia.fs101.xtable.XTable().createEmptyTable(5,7);


        }
    }


