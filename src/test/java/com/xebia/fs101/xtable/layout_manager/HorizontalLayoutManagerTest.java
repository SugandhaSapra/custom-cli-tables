package com.xebia.fs101.xtable.layout_manager;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HorizontalLayoutManagerTest {

    @Test
    public void should_create_empty_table_with_valid_rows_and_cols() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(1, 3);
        String actualResult = horizontalLayoutManager.createTable();
        String expectedResult =
                                "┌───────────────────┬───────────────────┬───────────────────┐\n" +
                                "│                   │                   │                   │\n" +
                                "└───────────────────┴───────────────────┴───────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }


    @Test

    public void should_create_empty_table_with_passed_rows_and_columns_with_custom_column_widths() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(2, 3,new int[]{10,20,30});
        String actualResult = horizontalLayoutManager.createTable();
        String expectedResult=
                        "┌─────────┬───────────────────┬─────────────────────────────┐\n" +
                        "│         │                   │                             │\n" +
                        "├─────────┼───────────────────┼─────────────────────────────┤\n" +
                        "│         │                   │                             │\n" +
                        "└─────────┴───────────────────┴─────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    public void should_create_table_with_header() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(2, 3);
        String[] headers = {"one", "two", "three"};

        String actualResult = horizontalLayoutManager.createTableWithHeadersOnly(headers);
        String expectedResult =
                        "┌───────────────────┬───────────────────┬───────────────────┐\n" +
                        "│ one               │ two               │ three             │\n" +
                        "├───────────────────┼───────────────────┼───────────────────┤\n" +
                        "│                   │                   │                   │\n" +
                        "└───────────────────┴───────────────────┴───────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_create_table_with_header_and_custom_column_width() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(2, 3,new int[]{10,20,30});
        String[] headers = {"one", "two", "three"};

        String actualResult = horizontalLayoutManager.createTableWithHeadersOnly(headers);
        String expectedResult =
                        "┌─────────┬───────────────────┬─────────────────────────────┐\n" +
                        "│ one     │ two               │ three                       │\n" +
                        "├─────────┼───────────────────┼─────────────────────────────┤\n" +
                        "│         │                   │                             │\n" +
                        "└─────────┴───────────────────┴─────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_create_table_with_data_rows() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(4, 3);
        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        String[] row4 = {"flexible", "pleasant", "wild"};
        List<String[]> tabledata = new ArrayList<>();
        tabledata.add(row1);
        tabledata.add(row2);
        tabledata.add(row3);
        tabledata.add(row4);
        String actualResult = horizontalLayoutManager.createDataTable(tabledata);
        String expectedResult =
                        "┌───────────────────┬───────────────────┬───────────────────┐\n" +
                        "│ one               │ two               │ three             │\n" +
                        "├───────────────────┼───────────────────┼───────────────────┤\n" +
                        "│ test              │ logic             │ user              │\n" +
                        "├───────────────────┼───────────────────┼───────────────────┤\n" +
                        "│ assumption        │ great             │ reflection        │\n" +
                        "├───────────────────┼───────────────────┼───────────────────┤\n" +
                        "│ flexible          │ pleasant          │ wild              │\n" +
                        "└───────────────────┴───────────────────┴───────────────────┘";

        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_create_table_with_data_rows_and_custom_column_width() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(4, 3,new int[]{20,40,60});
        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        String[] row4 = {"flexible", "pleasant", "wild"};
        List<String[]> tabledata = new ArrayList<>();
        tabledata.add(row1);
        tabledata.add(row2);
        tabledata.add(row3);
        tabledata.add(row4);
        String actualResult = horizontalLayoutManager.createDataTable(tabledata);
        String expectedResult =
                        "┌───────────────────┬───────────────────────────────────────┬───────────────────────────────────────────────────────────┐\n" +
                        "│ one               │ two                                   │ three                                                     │\n" +
                        "├───────────────────┼───────────────────────────────────────┼───────────────────────────────────────────────────────────┤\n" +
                        "│ test              │ logic                                 │ user                                                      │\n" +
                        "├───────────────────┼───────────────────────────────────────┼───────────────────────────────────────────────────────────┤\n" +
                        "│ assumption        │ great                                 │ reflection                                                │\n" +
                        "├───────────────────┼───────────────────────────────────────┼───────────────────────────────────────────────────────────┤\n" +
                        "│ flexible          │ pleasant                              │ wild                                                      │\n" +
                        "└───────────────────┴───────────────────────────────────────┴───────────────────────────────────────────────────────────┘";

        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_truncate_data_if_header_is_larger_as_compared_to_column_length() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(2, 3,new int[]{10,5,5});
        String[] headers = {"one", "two", "three"};

        String actualResult = horizontalLayoutManager.createTableWithHeadersOnly(headers);
        String expectedResult =
                        "┌─────────┬────┬────┐\n" +
                        "│ one     │ two│ t..│\n" +
                        "├─────────┼────┼────┤\n" +
                        "│         │    │    │\n" +
                        "└─────────┴────┴────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }
    @Test
    public void should_be_able_to_truncate_data_if_data_is_larger_as_compared_to_column_length() {
        LayoutManager horizontalLayoutManager = new HorizontalLayoutManager(3, 3,new int[]{10,20,10});
        String[] row1 = {"test", "logic", "user"};
        String[] row2 = {"assumption", "great", "reflection"};
        String[] row3 = {"flexible", "pleasant", "wild"};
        List<String[]> tabledata = new ArrayList<>();
        tabledata.add(row1);
        tabledata.add(row2);
        tabledata.add(row3);
        String actualResult = horizontalLayoutManager.createDataTable(tabledata);
        String expectedResult =
                        "┌─────────┬───────────────────┬─────────┐\n" +
                        "│ test    │ logic             │ user    │\n" +
                        "├─────────┼───────────────────┼─────────┤\n" +
                        "│ assump..│ great             │ reflec..│\n" +
                        "├─────────┼───────────────────┼─────────┤\n" +
                        "│ flexible│ pleasant          │ wild    │\n" +
                        "└─────────┴───────────────────┴─────────┘";

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}