package com.xebia.fs101.xtable;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class VerticalLayoutManagerTest {
    @Test
    public void should_be_able_to_create_a_table_strcuture_with_headers() {
        VerticalLayoutManager verticalLayoutManager = new VerticalLayoutManager(3, 4);
        String[] cells = {"one", "two", "three"};
        String setBold = "\033[1mThis keeps it bold.";
        String boldGrayLine = "\033[1mThis is a BOLD line\033[0m";
        System.out.println(setBold);
        System.out.println(boldGrayLine);
        String result = verticalLayoutManager.createTableWithOnlyHeaders(cells);
        System.out.println(result);
        assertThat(result).isEqualTo(
                        "┌──────┬──────┬──────┬──────┐\n" +
                        "│ one  │      │      │      │\n" +
                        "├──────┼──────┼──────┼──────┤\n" +
                        "│ two  │      │      │      │\n" +
                        "├──────┼──────┼──────┼──────┤\n" +
                        "│ three│      │      │      │\n" +
                        "└──────┴──────┴──────┴──────┘"
        );
    }

    @Test
    public void should_be_able_to_create_a_table_with_headers_and_rows() {
        VerticalLayoutManager verticalLayoutManager = new VerticalLayoutManager(3, 4);
        String[] headers = {"Name", "Marks", "Subject"};
        String[] row1 = {"Trump", "Obama", "John"};
        String[] row2 = {"10", "40", "60"};
        String[] row3 = {"Math", "Math", "Math"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(headers);
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        String actualResult = verticalLayoutManager.createDataTable(tableData);
        assertThat(actualResult).isEqualTo(
                        "┌────────┬────────┬────────┬────────┐\n" +
                        "│ Name   │ Trump  │ Obama  │ John   │\n" +
                        "├────────┼────────┼────────┼────────┤\n" +
                        "│ Marks  │ 10     │ 40     │ 60     │\n" +
                        "├────────┼────────┼────────┼────────┤\n" +
                        "│ Subject│ Math   │ Math   │ Math   │\n" +
                        "└────────┴────────┴────────┴────────┘"
        );
    }
}
