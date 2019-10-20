package com.xebia.fs101.xtable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTest {

    @Test
    public void should_return_shape_of_table_when_given_valid_data() {
        Table table = new Table.Builder().withRowCount(3).withColCount(4).build();
        String actualResult = table.getShape();
        String expectedResult = "3 rows X 4 cols";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_row_count_or_col_count_is_less_than_0() {
        Table table = new Table(-1, 1);
    }

    @Test
    public void should_create_table_with_no_data_with_for_1_row_and_col() {
        Table table = new Table.Builder().withRowCount(1).withColCount(1).build();
        String actualResult = table.generateTable();
        System.out.println(actualResult);
        String expectedResult =
                        "┌───────────────────┐\n" +
                        "│                   │\n" +
                        "└───────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_create_table_with_only_header() {
        String[] cells={"one","two","three"};
        Table table = new Table.Builder().withRowCount(2).withColCount(3).withHeader(cells).build();
        String actualResult = table.generateTable(cells);
        String expectedResult =
                        "┌──────┬──────┬──────┐\n" +
                        "│ one  │ two  │ three│\n" +
                        "├──────┼──────┼──────┤\n" +
                        "│      │      │      │\n" +
                        "└──────┴──────┴──────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    public void should_create_table_with_data_rows() {

        String[] celldata1 = {"one", "two", "three"};
        String[] celldata2 = {"test", "logic", "user"};
        String[] celldata3 = {"assumption", "great", "reflection"};
        String[] celldata4 = {"flexible", "pleasant", "wild"};
        List<String[]> rowdata = new ArrayList<>();
        rowdata.add(celldata1);
        rowdata.add(celldata2);
        rowdata.add(celldata3);
        rowdata.add(celldata4);
        Table table = new Table.Builder().withRowCount(4).withColCount(3).withRows(rowdata).build();
        String actualResult = table.generateTable(rowdata);
        String expectedResult =
                        "┌───────────┬───────────┬───────────┐\n" +
                        "│ one       │ two       │ three     │\n" +
                        "├───────────┼───────────┼───────────┤\n" +
                        "│ test      │ logic     │ user      │\n" +
                        "├───────────┼───────────┼───────────┤\n" +
                        "│ assumption│ great     │ reflection│\n" +
                        "├───────────┼───────────┼───────────┤\n" +
                        "│ flexible  │ pleasant  │ wild      │\n" +
                        "└───────────┴───────────┴───────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }


}