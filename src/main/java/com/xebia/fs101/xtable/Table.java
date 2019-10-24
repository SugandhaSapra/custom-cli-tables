package com.xebia.fs101.xtable;

import com.xebia.fs101.xtable.layout_manager.LayoutManager;
import com.xebia.fs101.xtable.layout_manager.TableLayoutFactory;
import com.xebia.fs101.xtable.renderer.ConsoleBaseRenderer;
import com.xebia.fs101.xtable.renderer.Renderer;

import java.util.List;

import static com.xebia.fs101.xtable.layout_manager.TableLayout.*;

public class Table {

    private int rowCount;
    private int colCount;
    private LayoutManager layoutManager;
    private Renderer renderer;
    private String[] headers;
    private List<String[]> rows;
    private int[] columnWidth;


    private Table(Builder builder) {
        rowCount = builder.rowCount;
        colCount = builder.colCount;
        renderer = new ConsoleBaseRenderer();
        rows = builder.rows;
        headers = builder.headers;
        layoutManager = builder.layoutManager;
        columnWidth = builder.columnWidth;
    }

    public void render() {
        renderer.printTable(generate());
    }

    public String getShape() {
        return rowCount + " rows X " + colCount + " cols";
    }


    public String generate() {

        if (headers != null || rows != null) {
            if (headers != null && rows != null) {
                rows.add(0, headers);
                return layoutManager.createDataTable(rows);
            } else if (headers != null && rows == null) {
                return layoutManager.createTableWithHeadersOnly(headers);
            } else {

                return layoutManager.createDataTable(rows);
            }

        } else

            return layoutManager.createTable();

    }

    public static final class Builder {

        private int rowCount;
        private int colCount;
        private LayoutManager layoutManager;
        private Renderer renderer;
        private List<String[]> rows;
        private String[] headers;
        private TableLayoutFactory tableLayoutFactory;
        private int[] columnWidth;

        public Builder() {
        }

        public Builder withColumnWidth(int[] val) {
            columnWidth = val;
            return this;
        }

        public Builder withRowCount(int val) {
            rowCount = val;
            return this;
        }

        public Builder withColCount(int val) {
            colCount = val;
            return this;
        }

        public Builder withHeader(String[] val) {
            headers = val;
            return this;
        }

        public Builder withRenderer(Renderer val) {
            renderer = val;
            return this;
        }

        public Builder withRows(List<String[]> val) {
            rows = val;
            return this;
        }

        public Builder withHorizontalLayoutManger() {
            tableLayoutFactory = new TableLayoutFactory();
            layoutManager = tableLayoutFactory.getLayoutManager(HORIZONTAL, rowCount, colCount);
            return this;

        }

        public Builder withVerticalLayoutManger() {
            tableLayoutFactory = new TableLayoutFactory();
            layoutManager = tableLayoutFactory.getLayoutManager(VERTICAL, rowCount, colCount);
            return this;
        }
        public Builder withHorizontalLayoutMangerWithColWidth() {
            tableLayoutFactory = new TableLayoutFactory();
            layoutManager = tableLayoutFactory.getLayoutManager(HORIZONTAL, rowCount, colCount,columnWidth);
            return this;

        }

        public Builder withVerticalLayoutMangerWithColWidth() {
            tableLayoutFactory = new TableLayoutFactory();
            layoutManager = tableLayoutFactory.getLayoutManager(VERTICAL, rowCount, colCount,columnWidth);
            return this;
        }

        public Table build() {
            return new Table(this);
        }


    }

}
