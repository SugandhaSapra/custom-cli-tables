package com.xebia.fs101.xtable;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class CustomHorizontalLayoutManagerTest {
    @Test
    public void should_be_able_to_create_table_with_customized_column_width(){
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,20);
        columnWidth.put(2,10);
        columnWidth.put(3,30);
        CustomHorizontalLayoutManager customHorizontalLayoutManager =new CustomHorizontalLayoutManager(2,3);
        String actualResult = customHorizontalLayoutManager.createTable(columnWidth);
        String expectedResult =
                        "┌──────────────────┬────────┬────────────────────────────┐\n" +
                        "│                  │        │                            │\n" +
                        "├──────────────────┼────────┼────────────────────────────┤\n" +
                        "│                  │        │                            │\n" +
                        "└──────────────────┴────────┴────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_be_able_to_create_table_with_customized_column_width_and_with_headers(){
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,20);
        columnWidth.put(2,10);
        columnWidth.put(3,30);
        CustomHorizontalLayoutManager customHorizontalLayoutManager =new CustomHorizontalLayoutManager(4,3);
        String[] headers=new String[]{"one","two","three"};
        String actualResult = customHorizontalLayoutManager.createTableWithHeadersOnly(headers,columnWidth);
        String expectedResult =
                        "┌──────────────────┬────────┬────────────────────────────┐\n" +
                        "│ one              │ two    │ three                      │\n" +
                        "├──────────────────┼────────┼────────────────────────────┤\n" +
                        "│                  │        │                            │\n" +
                        "├──────────────────┼────────┼────────────────────────────┤\n" +
                        "│                  │        │                            │\n" +
                        "├──────────────────┼────────┼────────────────────────────┤\n" +
                        "│                  │        │                            │\n" +
                        "└──────────────────┴────────┴────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_be_able_to_create_table_with_customized_column_width_and_data_with_headers(){
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,20);
        columnWidth.put(2,30);
        columnWidth.put(3,30);
        CustomHorizontalLayoutManager customHorizontalLayoutManager =new CustomHorizontalLayoutManager(4,3);
        //String[] headers=new String[]{"one","two","three"};
        String[] row1 = {"one", "two", "three"};
        String[] row2 = {"test", "logic", "user"};
        String[] row3 = {"assumption", "great", "reflection"};
        String[] row4 = {"flexible", "pleasant", "wild"};
        List<String[]> tabledata = new ArrayList<>();
        tabledata.add(row1);
        tabledata.add(row2);
        tabledata.add(row3);
        tabledata.add(row4);
        String actualResult = customHorizontalLayoutManager.createTableWithHeadersAndData(tabledata,columnWidth);
        String expectedResult =
                        "┌──────────────────┬────────────────────────────┬────────────────────────────┐\n" +
                        "│ one              │ two                        │ three                      │\n" +
                        "├──────────────────┼────────────────────────────┼────────────────────────────┤\n" +
                        "│ test             │ logic                      │ user                       │\n" +
                        "├──────────────────┼────────────────────────────┼────────────────────────────┤\n" +
                        "│ assumption       │ great                      │ reflection                 │\n" +
                        "├──────────────────┼────────────────────────────┼────────────────────────────┤\n" +
                        "│ flexible         │ pleasant                   │ wild                       │\n" +
                        "└──────────────────┴────────────────────────────┴────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_truncate_data_if_it_is_greater_than_column_width() {
        CustomHorizontalLayoutManager customHorizontalLayoutManager=new CustomHorizontalLayoutManager(2,3);
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,10);
        columnWidth.put(2,8);
        columnWidth.put(3,15);
        String[] row1 = {"assumption", "great", "reflection"};
        String[] row2 = {"flexible", "pleasant", "wild"};
        List<String[]> tableData=new ArrayList<>();
        tableData.add(row1);
        tableData.add(row2);
        String actualResult=customHorizontalLayoutManager.createTableWithHeadersAndData(tableData,columnWidth);
        String expectedResult=
                        "┌────────┬──────┬─────────────┐\n" +
                        "│ assum..│ great│ reflection  │\n" +
                        "├────────┼──────┼─────────────┤\n" +
                        "│ flexi..│ ple..│ wild        │\n" +
                        "└────────┴──────┴─────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }
    @Test
    public void should_be_able_to_truncate_header_if_it_is_greater_than_column_width() {
        CustomHorizontalLayoutManager customHorizontalLayoutManager=new CustomHorizontalLayoutManager(2,3);
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,10);
        columnWidth.put(2,8);
        columnWidth.put(3,15);
        String[] header = {"assumption", "great", "reflection"};
        String actualResult=customHorizontalLayoutManager.createTableWithHeadersOnly(header,columnWidth);
        String expectedResult=
                               "┌────────┬──────┬─────────────┐\n" +
                               "│ assum..│ great│ reflection  │\n" +
                               "├────────┼──────┼─────────────┤\n" +
                               "│        │      │             │\n" +
                               "└────────┴──────┴─────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }
}