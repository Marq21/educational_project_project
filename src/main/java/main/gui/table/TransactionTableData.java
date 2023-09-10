package main.gui.table;

import main.gui.handler.FunctionsHandler;
import main.gui.table.model.TransactionTableModel;
import main.gui.table.renderer.MainTableCellRenderer;
import main.settings.Style;
import main.settings.Text;

import javax.swing.*;
import java.awt.*;

import static main.settings.Style.*;

public class TransactionTableData extends TableData {

    private static final String[] columns = new String[]{"DATE", "ACCOUNT", "ARTICLE", "AMOUNT", "NOTICE"};
    private static final ImageIcon[] icons = new ImageIcon[]{ICON_DATE, ICON_ACCOUNT, ICON_ARTICLE, ICON_AMOUNT, ICON_NOTICE};

    public TransactionTableData(FunctionsHandler handler) {
        super(new TransactionTableModel(columns), handler, columns, icons);
        init();
    }

    public TransactionTableData(FunctionsHandler handler, int count) {
        super(new TransactionTableModel(columns, count), handler, columns, icons);
        init();
    }

    @Override
    protected final void init() {
        getColumn(Text.get("AMOUNT")).setCellRenderer(new TableCellAmountRenderer());
    }

    private static class TableCellAmountRenderer extends MainTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ((value.toString()).contains("-")) renderer.setForeground(Style.COLOR_EXP);
            else renderer.setForeground(Style.COLOR_INCOME);
            return renderer;
        }
    }
}
