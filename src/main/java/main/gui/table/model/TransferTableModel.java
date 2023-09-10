package main.gui.table.model;

import main.model.Transfer;
import main.saveload.SaveData;
import main.settings.Format;

public class TransferTableModel extends MainTableModel {

    private static final int DATE = 0;
    private static final int FROM_ACCOUNT = 1;
    private static final int TO_ACCOUNT = 2;
    private static final int FROM_AMOUNT = 3;
    private static final int TO_AMOUNT = 4;
    private static final int NOTICE = 5;

    public TransferTableModel(String[] columns) {
        super(SaveData.getInstance().getFilterTransfers(), columns);
    }

    @Override
    protected void updateData() {
        data = SaveData.getInstance().getFilterTransfers();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data.isEmpty()) return null;
        Transfer transfer = (Transfer) data.get(rowIndex);
        return switch (columnIndex) {
            case DATE -> Format.date(transfer.getDate());
            case FROM_ACCOUNT -> transfer.getFromAccount().getTitle();
            case TO_ACCOUNT -> transfer.getToAccount().getTitle();
            case FROM_AMOUNT -> Format.amount(transfer.getFromAmount(), transfer.getFromAccount().getCurrency());
            case TO_AMOUNT -> Format.amount(transfer.getToAmount(), transfer.getToAccount().getCurrency());
            case NOTICE -> transfer.getNotice();
            default -> null;
        };
    }
}
