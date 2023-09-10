package main.gui.panel;

import main.gui.MainFrame;
import main.gui.dialog.TransferAddEditDialog;
import main.gui.handler.FunctionsHandler;
import main.gui.table.TransferTableData;
import main.gui.toolbar.FunctionsToolBar;
import main.settings.Style;

import javax.swing.*;

public class TransferPanel extends RightPanel {

    public TransferPanel(MainFrame frame) {
        super(frame, new TransferTableData(new FunctionsHandler(frame, new TransferAddEditDialog(frame))),
                "TRANSFERS",
                Style.ICON_PANEL_TRANSFERS,
                new JPanel[]{new FunctionsToolBar(new FunctionsHandler(frame, new TransferAddEditDialog(frame))), new FilterPanel(frame)});
    }
}
