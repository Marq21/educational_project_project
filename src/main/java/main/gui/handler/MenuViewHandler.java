package main.gui.handler;

import main.gui.MainFrame;
import main.gui.panel.*;
import main.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class MenuViewHandler extends Handler {

    public MenuViewHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HandlerCode.MENU_VIEW_OVERVIEW -> {
                showOverviewPanel();
            }
            case HandlerCode.MENU_VIEW_ACCOUNTS -> {
                showAccountPanel();
            }
            case HandlerCode.MENU_VIEW_ARTICLES -> {
                showArticlePanel();
            }
            case HandlerCode.MENU_VIEW_TRANSACTIONS -> {
                showTransactionPanel();
            }
            case HandlerCode.MENU_VIEW_TRANSFERS -> {
                showTransferPanel();
            }
            case HandlerCode.MENU_VIEW_CURRENCIES -> {
                showCurrencyPanel();
            }
            case HandlerCode.MENU_VIEW_STATISTICS -> {
                showStatisticsPanel();
            }
        }
        super.actionPerformed(e);
    }

    protected void showOverviewPanel() {
        frame.setRightPanel(new OverviewPanel(frame));
    }

    protected void showAccountPanel() {
        frame.setRightPanel(new AccountPanel(frame));
    }

    protected void showArticlePanel() {
        frame.setRightPanel(new ArticlePanel(frame));
    }

    protected void showTransactionPanel() {
        frame.setRightPanel(new TransactionPanel(frame));
    }

    protected void showTransferPanel() {
        frame.setRightPanel(new TransferPanel(frame));
    }

    protected void showCurrencyPanel() {
        frame.setRightPanel(new CurrencyPanel(frame));
    }

    protected void showStatisticsPanel() {
        frame.setRightPanel(new StatisticsPanel(frame));
    }
}

