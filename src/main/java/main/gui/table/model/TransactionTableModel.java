package main.gui.table.model;

import main.model.Transaction;
import main.saveload.SaveData;
import main.settings.Format;

public class TransactionTableModel extends MainTableModel {

    private static final int DATE = 0;
    private static final int ACCOUNT = 1;
    private static final int ARTICLE = 2;
    private static final int AMOUNT = 3;
    private static final int NOTICE = 4;

    private int count = -1;

    public TransactionTableModel(String[] columns) {
        super(SaveData.getInstance().getFilterTransactions(), columns);
    }

    public TransactionTableModel(String[] columns, int count) {
        super(SaveData.getInstance().getFilterTransactionsOnCount(count), columns);
        this.count = count;
    }

    @Override
    protected void updateData() {
        if (count == -1) data = SaveData.getInstance().getFilterTransactions();
        else data = SaveData.getInstance().getFilterTransactionsOnCount(count);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data.isEmpty()) return null;
        Transaction transaction = (Transaction) data.get(rowIndex);
        return switch (columnIndex) {
            case DATE -> Format.date(transaction.getDate());
            case ACCOUNT -> transaction.getAccount().getTitle();
            case ARTICLE -> transaction.getArticle().getTitle();
            case AMOUNT -> Format.amount(transaction.getAmount(), transaction.getAccount().getCurrency());
            case NOTICE -> transaction.getNotice();
            default -> null;
        };
    }
}
