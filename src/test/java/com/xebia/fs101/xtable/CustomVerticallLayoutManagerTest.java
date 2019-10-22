package com.xebia.fs101.xtable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class CustomVerticallLayoutManagerTest {
    @Test
    public void should_be_able_to_create_vertical_table_with_customized_column_width(){
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,20);
        columnWidth.put(2,10);
        columnWidth.put(3,30);
        CustomVerticallLayoutManager verticalLayoutManager =new CustomVerticallLayoutManager(2,3);
        String actualResult = verticalLayoutManager.createTable(columnWidth);
        String expectedResult =
                        "┌──────────────────┬────────┬────────────────────────────┐\n" +
                        "│                  │        │                            │\n" +
                        "├──────────────────┼────────┼────────────────────────────┤\n" +
                        "│                  │        │                            │\n" +
                        "└──────────────────┴────────┴────────────────────────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_create_vertical_table_with_customized_column_width_and_with_headers(){
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,20);
        columnWidth.put(2,10);
        columnWidth.put(3,30);
        columnWidth.put(4,10);
        CustomVerticallLayoutManager customVerticallLayoutManager =new CustomVerticallLayoutManager(3,4);
        String[] headers=new String[]{"one","two","three"};
        String actualResult = customVerticallLayoutManager.createTableWithHeadersOnly(headers,columnWidth);
        String expectedResult =
                                "┌──────────────────┬────────┬────────────────────────────┬────────┐\n" +
                                "│ one              │        │                            │        │\n" +
                                "├──────────────────┼────────┼────────────────────────────┼────────┤\n" +
                                "│ two              │        │                            │        │\n" +
                                "├──────────────────┼────────┼────────────────────────────┼────────┤\n" +
                                "│ three            │        │                            │        │\n" +
                                "└──────────────────┴────────┴────────────────────────────┴────────┘";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_create_vertical_table_with_customized_column_width_and_data_with_headers(){
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,20);
        columnWidth.put(2,30);
        columnWidth.put(3,40);
        columnWidth.put(4,50);
        CustomVerticallLayoutManager customVerticalLayoutManager =new CustomVerticallLayoutManager(3,4);
        //String[] headers=new String[]{"one","two","three"};
        String[] row1 = {"Name", "Marks", "Relegious"};
        String[] row2 = {"Supreet", "0.05", "Sometimes"};
        String[] row3 = {"Nishant", ".8", "Depends"};
        String[] row4 = {"Sugandha", "1", "Always"};
        List<String[]> tabledata = new ArrayList<>();
        tabledata.add(row1);
        tabledata.add(row2);
        tabledata.add(row3);
        tabledata.add(row4);
        String actualResult = customVerticalLayoutManager.createTableWithHeadersAndData(tabledata,columnWidth);
        String expectedResult =
                        "┌──────────────────┬────────────────────────────┬──────────────────────────────────────┬────────────────────────────────────────────────┐\n" +
                        "│ Name             │ Supreet                    │ Nishant                              │ Sugandha                                       │\n" +
                        "├──────────────────┼────────────────────────────┼──────────────────────────────────────┼────────────────────────────────────────────────┤\n" +
                        "│ Marks            │ 0.05                       │ .8                                   │ 1                                              │\n" +
                        "├──────────────────┼────────────────────────────┼──────────────────────────────────────┼────────────────────────────────────────────────┤\n" +
                        "│ Relegious        │ Sometimes                  │ Depends                              │ Always                                         │\n" +
                        "└──────────────────┴────────────────────────────┴──────────────────────────────────────┴────────────────────────────────────────────────┘";

        assertThat(actualResult).isEqualTo(expectedResult);
    }
    @Test
    public void should_be_able_to_truncate_data_if_it_is_greater_than_column_width() {
        CustomVerticallLayoutManager customVerticallLayoutManager=new CustomVerticallLayoutManager(3,2);
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,10);
        columnWidth.put(2,8);
        String[] row1 = {"assumption", "great", "reflection"};
        String[] row2 = {"flexible", "pleasant", "wild"};
        List<String[]> tableData=new ArrayList<>();
        tableData.add(row1);
        tableData.add(row2);
        String actualResult=customVerticallLayoutManager.createTableWithHeadersAndData(tableData,columnWidth);
        String expectedResult=
                "┌────────┬──────┐\n" +
                        "│ assum..│ fle..│\n" +
                        "├────────┼──────┤\n" +
                        "│ great  │ ple..│\n" +
                        "├────────┼──────┤\n" +
                        "│ refle..│ wild │\n" +
                        "└────────┴──────┘";
        assertThat(actualResult).isEqualTo(expectedResult);

    }
    @Test
    public void should_be_able_to_truncate_header_if_it_is_greater_than_column_width() {
        CustomVerticallLayoutManager customVerticallLayoutManager=new CustomVerticallLayoutManager(3,2);
        Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth.put(1,10);
        columnWidth.put(2,8);
        String[] header = {"assumption", "great", "reflection"};
        String actualResult=customVerticallLayoutManager.createTableWithHeadersOnly(header,columnWidth);
        String expectedResult=
                "┌────────┬──────┐\n" +
                        "│ assu.. │      │\n" +
                        "├────────┼──────┤\n" +
                        "│ great  │      │\n" +
                        "├────────┼──────┤\n" +
                        "│ refl.. │      │\n" +
                        "└────────┴──────┘";

        assertThat(actualResult).isEqualTo(expectedResult);

    }

}