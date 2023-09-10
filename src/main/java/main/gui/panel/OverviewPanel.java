package main.gui.panel;

import main.gui.MainFrame;
import main.gui.dialog.AccountAddEditDialog;
import main.gui.dialog.TransferAddEditDialog;
import main.gui.handler.FunctionsHandler;
import main.gui.table.TransactionTableData;
import main.settings.Settings;
import main.settings.Style;

public class OverviewPanel extends RightPanel {

    public OverviewPanel(MainFrame frame) {
        super(frame,
                new TransactionTableData(new FunctionsHandler(frame, new TransferAddEditDialog(frame)), Settings.COUNT_OVERVIEW_ROWS),
                "LAST_TRANSACTIONS", Style.ICON_PANEL_OVERVIEW);
    }


}
