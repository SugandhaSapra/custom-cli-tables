package com.xebia.fs101.xtable;

public class ConsoleBaseRenderer implements Renderer {
    @Override
    public void printTable(String tableStructure) {
        System.out.println(tableStructure);
    }
}
