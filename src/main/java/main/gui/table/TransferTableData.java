package main.gui.table;

import main.gui.handler.FunctionsHandler;
import main.gui.table.model.TransferTableModel;
import main.gui.table.renderer.MainTableCellRenderer;
import main.settings.Text;

import javax.swing.*;
import java.awt.*;

import static main.settings.Style.*;

public class TransferTableData extends TableData {

    private static final String[] columns = new String[]{"DATE", "FROM_ACCOUNT", "TO_ACCOUNT", "FROM_AMOUNT", "TO_AMOUNT", "NOTICE"};
    private static final ImageIcon[] icons = new ImageIcon[]{ICON_DATE, ICON_ACCOUNT, ICON_ACCOUNT,ICON_AMOUNT, ICON_AMOUNT, ICON_NOTICE};

    public TransferTableData(FunctionsHandler handler) {
        super(new TransferTableModel(columns), handler, columns, icons);
        init();
    }

    @Override
    protected final void init() {
        getColumn(Text.get("FROM_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(COLOR_EXP));
        getColumn(Text.get("TO_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(COLOR_INCOME));
    }

    private static class TableCellAmountRenderer extends MainTableCellRenderer {

        private final Color color;

        public TableCellAmountRenderer(Color color) {
            this.color = color;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            renderer.setForeground(color);
            return renderer;
        }
    }
}
