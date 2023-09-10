package main.gui.panel;

import main.gui.MainFrame;
import main.gui.dialog.AccountAddEditDialog;
import main.gui.handler.FunctionsHandler;
import main.gui.table.AccountTableData;
import main.gui.toolbar.FunctionsToolBar;
import main.settings.Style;

public class AccountPanel extends RightPanel {

    public AccountPanel(MainFrame frame) {
        super(frame,
                new AccountTableData(new FunctionsHandler(frame, new AccountAddEditDialog(frame))),
                "ACCOUNTS",
                Style.ICON_PANEL_ACCOUNTS,
                new FunctionsToolBar(new FunctionsHandler(frame, new AccountAddEditDialog(frame))));
    }
}
