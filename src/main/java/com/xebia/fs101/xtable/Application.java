package com.xebia.fs101.xtable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Application {
   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       do {
           System.out.println("\n\n          XTable");
           System.out.println("--------------------------------------");
           System.out.println("1. - Press 1 to create an empty table of m rows and n columns");
           System.out.println("2. - Press 2 to create a horizontal table with header");
           System.out.println("3. - Press 3 to create a vertical table with header ");
           System.out.println("4. - Press 4 to create the horizontal table");
           System.out.println("5. - Press 5 to create the vertical table\n");
           getInput(input.next()); // Get user input from the keyboard
       }
       while (true); // Display the menu until the user closes the program
   }
   private static void getInput(String input) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Enter valid number of rows");
       int rows = scanner.nextInt();
       System.out.println("Enter valid number of columns");
       int columns = scanner.nextInt();
       Table table=null;
       switch (Integer.parseInt(input)) {
           case 1: // create an empty table of m rows and n columns
                table = new Table.Builder().withRowCount(rows).withColCount(columns).build();
               table.renderTable();
               break;
           case 2: // create a table with header
           {
               String[] headers = new String[columns];
               String header;
               for (int i = 0; i < columns; i++) {
                   header = scanner.next();
                   headers[i] = header;
               }
               table = new Table.Builder().withRowCount(rows).withColCount(columns).withHeader(headers).build();
               table.renderTable();
               break;
           }
           case 3: // create a table with header row and data rows
           {
               String[] vertialHeaders = new String[columns];
               String header;
               for (int i = 0; i < columns; i++) {
                   header = scanner.next();
                   vertialHeaders[i] = header;
               }
               table = new Table.Builder().withRowCount(rows).withColCount(columns).withHeader(vertialHeaders).withTableLayout(TableLayout.VERTICAL).build();
               table.renderTable();
               break;
           }
           case 4: { // create the horizontal table
               String[] header1 = {"one", "two", "three"};
               String[] row1 = {"test", "logic", "user"};
               String[] row2 = {"assumption", "great", "reflection"};
               String[] row3 = {"flexible", "pleasant", "wild"};
               List<String[]> rowdata = new ArrayList<>();
               rowdata.add(row1);
               rowdata.add(row2);
               rowdata.add(row3);
               table = new Table.Builder().withRowCount(4).withColCount(3).withHeader(header1).withRows(rowdata).build();
               table.renderTable();
               break;
           }
           case 5: // create the vertical table
           {
               String[] header = {"Name", "Marks", "Subject"};
               String[] row1 = {"Trump", "Obama", "John"};
               String[] row2 = {"10", "40", "60"};
               String[] row3 = {"Math", "Math", "Math"};
               List<String[]> tableData = new ArrayList<>();
               tableData.add(row1);
               tableData.add(row2);
               tableData.add(row3);
               table = new Table.Builder().withRowCount(3).withColCount(4).withHeader(header).withRows(tableData).withTableLayout(TableLayout.VERTICAL).build();
               table.renderTable();
               break;
           }
           default:
               System.out.print("The entered value is unrecognized!");
               break;
       }
   }
}
