package main.gui.handler;

import main.gui.MainFrame;
import main.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class MainToolBarHandler extends MenuViewHandler {

    public MainToolBarHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HandlerCode.TOOLBAR_OVERVIEW -> showOverviewPanel();
            case HandlerCode.TOOLBAR_ACCOUNTS -> showAccountPanel();
            case HandlerCode.TOOLBAR_ARTICLES -> showArticlePanel();
            case HandlerCode.TOOLBAR_TRANSACTIONS -> showTransactionPanel();
            case HandlerCode.TOOLBAR_TRANSFERS -> showTransferPanel();
            case HandlerCode.TOOLBAR_CURRENCIES -> showCurrencyPanel();
            case HandlerCode.TOOLBAR_STATISTICS -> showStatisticsPanel();
        }
        super.actionPerformed(e);
    }
}

