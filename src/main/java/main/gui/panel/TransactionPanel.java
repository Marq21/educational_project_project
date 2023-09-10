package main.gui.panel;

import main.gui.MainFrame;
import main.gui.dialog.AccountAddEditDialog;
import main.gui.dialog.TransactionAddEditDialog;
import main.gui.handler.FunctionsHandler;
import main.gui.table.TransactionTableData;
import main.gui.toolbar.FunctionsToolBar;
import main.settings.Style;

import javax.swing.*;

public class TransactionPanel extends RightPanel {

    public TransactionPanel(MainFrame frame) {
        super(frame, new TransactionTableData(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))),
                "TRANSACTIONS",
                Style.ICON_PANEL_TRANSACTIONS,
                new JPanel[]{new FunctionsToolBar(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))), new FilterPanel(frame)});
    }


}
