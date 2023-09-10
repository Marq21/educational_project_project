package main.gui.panel;

import main.gui.MainFrame;
import main.gui.dialog.CurrencyAddEditDialog;
import main.gui.handler.FunctionsHandler;
import main.gui.table.CurrencyTableData;
import main.gui.toolbar.FunctionsToolBar;
import main.settings.Style;

public class CurrencyPanel extends RightPanel {

    public CurrencyPanel(MainFrame frame) {
        super(frame,
                new CurrencyTableData(
                        new FunctionsHandler(frame,
                                new CurrencyAddEditDialog(frame))),
                "CURRENCIES", Style.ICON_PANEL_CURRENCIES,
                new FunctionsToolBar
                        (new FunctionsHandler(frame, new CurrencyAddEditDialog(frame))));
    }
}
