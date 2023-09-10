package main.gui.panel;

import main.gui.MainFrame;
import main.gui.dialog.AccountAddEditDialog;
import main.gui.dialog.ArticleAddEditDialog;
import main.gui.handler.FunctionsHandler;
import main.gui.table.ArticleTableData;
import main.gui.toolbar.FunctionsToolBar;
import main.settings.Style;

public class ArticlePanel extends RightPanel {

    public ArticlePanel(MainFrame frame) {
        super(frame, new ArticleTableData(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))),
                "ARTICLES", Style.ICON_PANEL_ARTICLES,
                new FunctionsToolBar(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))));
    }
}
