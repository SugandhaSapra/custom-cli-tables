package com.xebia.fs101.xtable;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\n\n          XTable");
            System.out.println("--------------------------------------");
            System.out.println("1. - Press 1 to create an empty table of m rows and n columns");
            System.out.println("2. - Press 2 to create a horizontal table with header");
            System.out.println("3. - Press 3 to create a vertical table with header");
            System.out.println("4. - Press 4 to create a horizontal table with header row and data rows");
            System.out.println("5. - Press 5 to create a vertical table with header row and data rows");
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
        switch (Integer.parseInt(input)) {
            case 1: // create an empty table of m rows and n columns
                Table table = new Table.Builder().withRowCount(rows).withColCount(columns).build();
                table.renderTable();
                break;
            case 2: // create a table with header
                String headers = scanner.next();
                System.out.println("Enter valid number of columns");
                //Table table = new Table.Builder().withRowCount(rows).withColCount(columns).withHeader(headers)
                // .build();
                break;
            case 3: // create a table with header row and data rows
                //Array.sortByVehicleCost(vehicles);
                break;
            case 4: // create the horizontal table
                //displayVehicleData(0);
                break;
            case 5: // create the vertical table

                break;
            default:
                System.out.print("The entered value is unrecognized!");
                break;
        }
    }
}
