package main.gui.table;

import main.gui.handler.FunctionsHandler;
import main.gui.table.model.AccountTableModel;

import javax.swing.*;

import static main.settings.Style.ICON_AMOUNT;
import static main.settings.Style.ICON_TITLE;

public class AccountTableData extends TableData {

    private static final String[] columns = new String[]{"TITLE", "AMOUNT"};
    private static final ImageIcon[] icons = new ImageIcon[]{ICON_TITLE, ICON_AMOUNT};

    public AccountTableData(FunctionsHandler handler) {
        super(new AccountTableModel(columns), handler, columns, icons);
    }
}
