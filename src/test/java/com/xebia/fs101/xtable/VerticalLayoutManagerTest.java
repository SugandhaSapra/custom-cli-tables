package com.xebia.fs101.xtable;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class VerticalLayoutManagerTest {
    @Test
    public void should_create_table_empty_table_with_passed_rows_and_cols() {
        LayoutManager verticalLayoutManager = new VerticalLayoutManager(2,3);
        String actualResult = verticalLayoutManager.createTable(new int[] {10,10,10});
        String expectedResult =
                        "┌─────────┬─────────┬─────────┐\n" +
                        "│         │         │         │\n" +
                        "├─────────┼─────────┼─────────┤\n" +
                        "│         │         │         │\n" +
                        "└─────────┴─────────┴─────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_create_empty_table_with_passed_rows_and_columns_and_custom__column_widths() {
        LayoutManager verticalLayoutManager = new VerticalLayoutManager(2,3);
        String actualResult = verticalLayoutManager.createTable(new int[] {10,20,30});
        String expectedResult =
                        "┌─────────┬───────────────────┬─────────────────────────────┐\n" +
                        "│         │                   │                             │\n" +
                        "├─────────┼───────────────────┼─────────────────────────────┤\n" +
                        "│         │                   │                             │\n" +
                        "└─────────┴───────────────────┴─────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_create_table_with_header() {
        VerticalLayoutManager verticalLayoutManager = new VerticalLayoutManager(2, 3);
        String[] cells = {"one", "two"};
        String actualResult = verticalLayoutManager.createTableWithHeadersOnly(cells,new int[]{10,10,10});
        String expectedResult=
                               "┌─────────┬─────────┬─────────┐\n" +
                               "│ one     │         │         │\n" +
                               "├─────────┼─────────┼─────────┤\n" +
                               "│ two     │         │         │\n" +
                               "└─────────┴─────────┴─────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_create_table_with_header_and_custom_column_width() {
        VerticalLayoutManager verticalLayoutManager = new VerticalLayoutManager(2, 3);
        String[] cells = {"one", "two"};
        String actualResult = verticalLayoutManager.createTableWithHeadersOnly(cells,new int[]{10,20,30});
        String expectedResult=
                        "┌─────────┬───────────────────┬─────────────────────────────┐\n" +
                        "│ one     │                   │                             │\n" +
                        "├─────────┼───────────────────┼─────────────────────────────┤\n" +
                        "│ two     │                   │                             │\n" +
                        "└─────────┴───────────────────┴─────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void hould_create_table_with_data_rows() {
        LayoutManager verticalLayoutManager = new VerticalLayoutManager(3, 4);
        String[] headers = {"Name", "Marks", "Subject"};
        String[] row1 = {"Trump", "10", "Math"};
        String[] row2 = {"Obama", "40", "Math"};
        String[] row3 = {"Jamie", "60", "Math"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(headers);
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        String actualResult = verticalLayoutManager.createDataTable(tableData,new int[]{15,15,15,15});
        String expectedResult=
                        "┌──────────────┬──────────────┬──────────────┬──────────────┐\n" +
                        "│ Name         │ Trump        │ Obama        │ Jamie        │\n" +
                        "├──────────────┼──────────────┼──────────────┼──────────────┤\n" +
                        "│ Marks        │ 10           │ 40           │ 60           │\n" +
                        "├──────────────┼──────────────┼──────────────┼──────────────┤\n" +
                        "│ Subject      │ Math         │ Math         │ Math         │\n" +
                        "└──────────────┴──────────────┴──────────────┴──────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }
    @Test
    public void should_create_table_with_data_rows_and_custom_column_width(){
        LayoutManager verticalLayoutManager = new VerticalLayoutManager(3, 4);
        String[] headers = {"Name", "Marks", "Subject"};
        String[] row1 = {"Trump", "10", "Math"};
        String[] row2 = {"Obama", "40", "Math"};
        String[] row3 = {"Jamie", "60", "Math"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(headers);
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        String actualResult = verticalLayoutManager.createDataTable(tableData,new int[]{15,10,30,20});
        String expectedResult=
                        "┌──────────────┬─────────┬─────────────────────────────┬───────────────────┐\n" +
                        "│ Name         │ Trump   │ Obama                       │ Jamie             │\n" +
                        "├──────────────┼─────────┼─────────────────────────────┼───────────────────┤\n" +
                        "│ Marks        │ 10      │ 40                          │ 60                │\n" +
                        "├──────────────┼─────────┼─────────────────────────────┼───────────────────┤\n" +
                        "│ Subject      │ Math    │ Math                        │ Math              │\n" +
                        "└──────────────┴─────────┴─────────────────────────────┴───────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_truncate_data_if_header_is_larger_as_compared_to_column_length() {
        VerticalLayoutManager verticalLayoutManager = new VerticalLayoutManager(2, 3);
        String[] cells = {"three", "t"};
        String actualResult = verticalLayoutManager.createTableWithHeadersOnly(cells,new int[]{5,5,5});
        String expectedResult=
                        "┌────┬────┬────┐\n" +
                        "│ t..│    │    │\n" +
                        "├────┼────┼────┤\n" +
                        "│ t  │    │    │\n" +
                        "└────┴────┴────┘";

        assertThat(actualResult).isEqualTo(expectedResult);

    }
    @Test
    public void should_be_able_to_truncate_data_if_data_is_larger_as_compared_to_column_length() {
        LayoutManager verticalLayoutManager = new VerticalLayoutManager(3, 4);
        String[] headers = {"Name", "Marks", "Subject"};
        String[] row1 = {"Donald Trump", "10", "Mathematics"};
        String[] row2 = {"Obama", "40", "Mathematics"};
        String[] row3 = {"Jamie Lannister", "60", "Mathematics"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(headers);
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        String actualResult = verticalLayoutManager.createDataTable(tableData,new int[]{10,5,10,10});
        String expectedResult=
                        "┌─────────┬────┬─────────┬─────────┐\n" +
                        "│ Name    │ D..│ Obama   │ Jamie ..│\n" +
                        "├─────────┼────┼─────────┼─────────┤\n" +
                        "│ Marks   │ 10 │ 40      │ 60      │\n" +
                        "├─────────┼────┼─────────┼─────────┤\n" +
                        "│ Subject │ M..│ Mathem..│ Mathem..│\n" +
                        "└─────────┴────┴─────────┴─────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }

}