package main.gui.table.model;

import main.model.Account;
import main.saveload.SaveData;
import main.settings.Format;

public class AccountTableModel extends MainTableModel {

    private static final int TITLE = 0;
    private static final int AMOUNT = 1;

    public AccountTableModel(String[] columns) {
        super(SaveData.getInstance().getAccounts(), columns);
    }

    @Override
    protected void updateData() {
        data = SaveData.getInstance().getAccounts();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data.isEmpty()) return null;
        Account account = (Account) data.get(rowIndex);
        return switch (columnIndex) {
            case TITLE -> account.getTitle();
            case AMOUNT -> Format.amount(account.getAmount(), account.getCurrency());
            default -> null;
        };
    }
}
