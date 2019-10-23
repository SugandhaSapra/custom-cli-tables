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
        Table table =new Table.Builder().withRowCount(-1).withColCount(-1).withHorizontalLayoutManger().build();
        table.generate();
    }

    @Test
    public void should_create_table_with_no_data_with_for_1_row_and_col() {
        Table table = new Table.Builder().withRowCount(1).withColCount(1).withHorizontalLayoutManger().withColumnWidth(new int[]{10}).build();
        String actualResult = table.generate();
        String expectedResult =
                        "┌─────────┐\n" +
                                "│         │\n" +
                                "└─────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_create_horizontal_table_with_only_header(){
        String[] header={"one","two","three"};
        Table table = new Table.Builder().withRowCount(2).withColCount(3).withHeader(header).withHorizontalLayoutManger().withColumnWidth(new int[]{10,10,10}).build();
        String actualResult = table.generate();
        String expectedResult =
                        "┌─────────┬─────────┬─────────┐\n" +
                                "│ one     │ two     │ three   │\n" +
                                "├─────────┼─────────┼─────────┤\n" +
                                "│         │         │         │\n" +
                                "└─────────┴─────────┴─────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }
    @Test
    public void should_create_horizontal_table_with_only_header_and_custom_column_width(){
        String[] header={"one","two","three"};
        Table table = new Table.Builder().withRowCount(2).withColCount(3).withHeader(header).withHorizontalLayoutManger().withColumnWidth(new int[]{10,20,30}).build();
        String actualResult = table.generate();
        String expectedResult =
                "┌─────────┬───────────────────┬─────────────────────────────┐\n" +
                        "│ one     │ two               │ three                       │\n" +
                        "├─────────┼───────────────────┼─────────────────────────────┤\n" +
                        "│         │                   │                             │\n" +
                        "└─────────┴───────────────────┴─────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_if_headers_are_less_than_cols() {
        String[] cells={"one","two"};
        Table table = new Table.Builder().withRowCount(2).withColCount(3).withHeader(cells).withHorizontalLayoutManger().build();
        table.generate();

    }


    @Test
    public void should_create_horizontal_table_with_data_rows() {

        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        Table table = new Table.Builder().withRowCount(3).withColCount(3).withRows(tableData).withHorizontalLayoutManger().withColumnWidth(new int[]{15,15,15}).build();
        String actualResult = table.generate();
        String expectedResult =
                        "┌──────────────┬──────────────┬──────────────┐\n" +
                                "│ one          │ two          │ three        │\n" +
                                "├──────────────┼──────────────┼──────────────┤\n" +
                                "│ test         │ logic        │ user         │\n" +
                                "├──────────────┼──────────────┼──────────────┤\n" +
                                "│ assumption   │ great        │ reflection   │\n" +
                                "└──────────────┴──────────────┴──────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_create_horizontal_table_with_data_rows_and_custom_column_width() {

        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        Table table = new Table.Builder().withRowCount(3).withColCount(3).withRows(tableData).withHorizontalLayoutManger().withColumnWidth(new int[]{15,40,12}).build();
        String actualResult = table.generate();
        String expectedResult =
                "┌──────────────┬───────────────────────────────────────┬───────────┐\n" +
                        "│ one          │ two                                   │ three     │\n" +
                        "├──────────────┼───────────────────────────────────────┼───────────┤\n" +
                        "│ test         │ logic                                 │ user      │\n" +
                        "├──────────────┼───────────────────────────────────────┼───────────┤\n" +
                        "│ assumption   │ great                                 │ reflection│\n" +
                        "└──────────────┴───────────────────────────────────────┴───────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_if_colData_is_less_than_colCount() {

        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        String[] row4 = {"flexible", "pleasant", "wild"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        tableData.add(row4);
        Table table = new Table.Builder().withRowCount(4).withColCount(1).withRows(tableData).withHorizontalLayoutManger().build();
        table.generate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_if_rowData_is_less_than_rowCount() {

        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        String[] row4 = {"flexible", "pleasant", "wild"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        tableData.add(row4);
        Table table = new Table.Builder().withRowCount(2).withColCount(3).withRows(tableData).withHorizontalLayoutManger().build();
        table.generate();
    }


    @Test
    public void should_be_able_to_create_a_vertical_table_with_headers() {

        String[] headers = {"Name", "Marks", "Subject"};
        Table table = new Table.Builder().withRowCount(3).withColCount(4).withHeader(headers).withVerticalLayoutManger().withColumnWidth(new int[]{12,12,12,12}).build();
        String actualResult = table.generate();
        String expectResult=
                       "┌───────────┬───────────┬───────────┬───────────┐\n" +
                               "│ Name      │           │           │           │\n" +
                               "├───────────┼───────────┼───────────┼───────────┤\n" +
                               "│ Marks     │           │           │           │\n" +
                               "├───────────┼───────────┼───────────┼───────────┤\n" +
                               "│ Subject   │           │           │           │\n" +
                               "└───────────┴───────────┴───────────┴───────────┘";
        assertThat(actualResult).isEqualTo(expectResult);

    }
    @Test
    public void should_be_able_to_create_a_vertical_table_with_headers_and_custom_column_width() {

        String[] headers = {"Name", "Marks", "Subject"};
        Table table = new Table.Builder().withRowCount(3).withColCount(4).withHeader(headers).withVerticalLayoutManger().withColumnWidth(new int[]{20,5,10,5}).build();
        String actualResult = table.generate();
        String expectResult=
               "┌───────────────────┬────┬─────────┬────┐\n" +
                       "│ Name              │    │         │    │\n" +
                       "├───────────────────┼────┼─────────┼────┤\n" +
                       "│ Marks             │    │         │    │\n" +
                       "├───────────────────┼────┼─────────┼────┤\n" +
                       "│ Subject           │    │         │    │\n" +
                       "└───────────────────┴────┴─────────┴────┘";
        assertThat(actualResult).isEqualTo(expectResult);

    }

    @Test
    public void should_be_able_to_create_a_vertical_table_with_headers_and_rows() {

        String[] headers = {"Name", "Marks", "Subject"};
        String[] row1 = {"Trump", "10", "Math"};
        String[] row2 = {"Obama", "40", "Math"};
        String[] row3 = {"Jamie", "60", "Math"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(headers);
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        Table table = new Table.Builder().withRowCount(3).withColCount(4).withRows(tableData).withVerticalLayoutManger().withColumnWidth(new int[]{12,12,12,12}).build();
        String actualResult = table.generate();
        String expectedResult=
                "┌───────────┬───────────┬───────────┬───────────┐\n" +
                        "│ Name      │ Trump     │ Obama     │ Jamie     │\n" +
                        "├───────────┼───────────┼───────────┼───────────┤\n" +
                        "│ Marks     │ 10        │ 40        │ 60        │\n" +
                        "├───────────┼───────────┼───────────┼───────────┤\n" +
                        "│ Subject   │ Math      │ Math      │ Math      │\n" +
                        "└───────────┴───────────┴───────────┴───────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_create_a_vertical_table_with_headers_and_rows_with_custom_column_length() {

        String[] headers = {"Name", "Marks", "Subject"};
        String[] row1 = {"Trump", "10", "Math"};
        String[] row2 = {"Obama", "40", "Math"};
        String[] row3 = {"Jamie", "60", "Math"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(headers);
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        Table table = new Table.Builder().withRowCount(3).withColCount(4).withRows(tableData).withVerticalLayoutManger().withColumnWidth(new int[]{15,40,20,15}).build();
        String actualResult = table.generate();
        String expectedResult=
                "┌──────────────┬───────────────────────────────────────┬───────────────────┬──────────────┐\n" +
                        "│ Name         │ Trump                                 │ Obama             │ Jamie        │\n" +
                        "├──────────────┼───────────────────────────────────────┼───────────────────┼──────────────┤\n" +
                        "│ Marks        │ 10                                    │ 40                │ 60           │\n" +
                        "├──────────────┼───────────────────────────────────────┼───────────────────┼──────────────┤\n" +
                        "│ Subject      │ Math                                  │ Math              │ Math         │\n" +
                        "└──────────────┴───────────────────────────────────────┴───────────────────┴──────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_truncate_data_if_data_is_larger_as_compared_to_column_length() {
        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        List<String[]> tableData = new ArrayList<>();
        tableData.add(row1);
        tableData.add(row2);
        tableData.add(row3);
        Table table = new Table.Builder().withRowCount(3).withColCount(3).withRows(tableData).withHorizontalLayoutManger().withColumnWidth(new int[]{8,10,10}).build();
        String actualResult = table.generate();
        String expectedResult =
                "┌───────┬─────────┬─────────┐\n" +
                        "│ one   │ two     │ three   │\n" +
                        "├───────┼─────────┼─────────┤\n" +
                        "│ test  │ logic   │ user    │\n" +
                        "├───────┼─────────┼─────────┤\n" +
                        "│ assu..│ great   │ reflec..│\n" +
                        "└───────┴─────────┴─────────┘";

        assertThat(actualResult).isEqualTo(expectedResult);
    }

}