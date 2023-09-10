package main.gui.table;

import main.gui.handler.FunctionsHandler;
import main.gui.table.model.AccountTableModel;
import main.gui.table.model.ArticleTableModel;

import javax.swing.*;

import static main.settings.Style.ICON_AMOUNT;
import static main.settings.Style.ICON_TITLE;

public class ArticleTableData extends TableData {

    private static final String[] columns = new String[]{"TITLE"};
    private static final ImageIcon[] icons = new ImageIcon[]{ICON_TITLE};

    public ArticleTableData(FunctionsHandler handler) {
        super(new ArticleTableModel(columns), handler,columns, icons);
    }
}
