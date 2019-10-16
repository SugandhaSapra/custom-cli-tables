package com.xebia.fs101.xtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TableLayoutManagerTest {

    @Test
    public void should_create_table_with_no_data_with_for_1_row_and_col() {
        TableLayoutManager table = new TableLayoutManager(1, 1);
        String actualResult = table.createTable();
        String expectedResult =
                        "┌───────────────────┐\n" +
                        "│                   │\n" +
                        "└───────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_create_table_with_no_data_for_2_row_and_cols() {
        TableLayoutManager table = new TableLayoutManager(2, 2);
        String actualResult = table.createTable();
        String expectedResult =
                        "┌───────────────────┬───────────────────┐\n" +
                        "│                   │                   │\n" +
                        "├───────────────────┼───────────────────┤\n" +
                        "│                   │                   │\n" +
                        "└───────────────────┴───────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_create_table_with_no_data_for_2_row_and_3cols() {
        TableLayoutManager table = new TableLayoutManager(2, 3);
        String actualResult = table.createTable();
        String expectedResult =
                        "┌───────────────────┬───────────────────┬───────────────────┐\n" +
                        "│                   │                   │                   │\n" +
                        "├───────────────────┼───────────────────┼───────────────────┤\n" +
                        "│                   │                   │                   │\n" +
                        "└───────────────────┴───────────────────┴───────────────────┘";

        assertThat(actualResult).isEqualTo(expectedResult);
    }



}