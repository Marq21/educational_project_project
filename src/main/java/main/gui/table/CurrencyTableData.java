package main.gui.table;

import main.gui.handler.FunctionsHandler;
import main.gui.table.model.CurrencyTableModel;
import main.gui.table.renderer.MainTableCellRenderer;
import main.model.Currency;
import main.settings.Style;
import main.settings.Text;

import javax.swing.*;

import java.awt.*;

import static main.settings.Style.*;

public class CurrencyTableData extends TableData {

    private static final String[] columns = new String[]{"TITLE", "CODE", "RATE", "ON", "BASE"};
    private static final ImageIcon[] icons = new ImageIcon[]{ICON_TITLE, ICON_CODE, ICON_RATE, ICON_ON, ICON_BASE};

    public CurrencyTableData(FunctionsHandler handler) {
        super(new CurrencyTableModel(columns), handler,columns, icons);
        init();
    }

    @Override
    protected final void init() {
        for (String column : columns) {
            getColumn(Text.get(column)).setCellRenderer(new TableCellOnOffRenderer());
        }
    }

    private static class TableCellOnOffRenderer extends MainTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (((Currency)((CurrencyTableModel)table.getModel()).getCommonByRow(row)).isOn())
                renderer.setForeground(Style.COLOR_ON);
            else renderer.setForeground(Style.COLOR_OFF);
            return renderer;
        }
    }
}
