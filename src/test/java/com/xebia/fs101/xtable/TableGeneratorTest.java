package com.xebia.fs101.xtable;

import org.junit.Test;

public class TableGeneratorTest {

    @Test
    public void should_create_empty_table()
    {
        TableGenerator tableGenerator=new TableGenerator(2,1);
        tableGenerator.printTable();

    }

}