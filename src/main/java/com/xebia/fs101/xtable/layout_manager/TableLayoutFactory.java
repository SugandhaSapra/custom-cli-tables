package com.xebia.fs101.xtable.layout_manager;

import static com.xebia.fs101.xtable.layout_manager.TableLayout.VERTICAL;

public class TableLayoutFactory {

    public LayoutManager getLayoutManager(TableLayout tableLayout, int rowCount, int colCount) {
        if (tableLayout == VERTICAL) {
            return new VerticalLayoutManager(rowCount, colCount);
        }
        return new HorizontalLayoutManager(rowCount, colCount);
    }

    public LayoutManager getLayoutManager(TableLayout tableLayout, int rowCount, int colCount, int[] colWidth) {
        if (tableLayout == VERTICAL) {
            return new VerticalLayoutManager(rowCount, colCount, colWidth);
        }
        return new HorizontalLayoutManager(rowCount, colCount, colWidth);
    }
}
