package main.gui.table.model;

import main.model.Currency;
import main.saveload.SaveData;
import main.settings.Format;

public class CurrencyTableModel extends MainTableModel {

    private static final int TITLE = 0;
    private static final int CODE = 1;
    private static final int RATE = 2;
    private static final int ON = 3;
    private static final int BASE = 4;

    public CurrencyTableModel(String[] columns) {
        super(SaveData.getInstance().getCurrencies(), columns);
    }

    @Override
    protected void updateData() {
        data = SaveData.getInstance().getCurrencies();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data.isEmpty()) return null;
        Currency currency = (Currency) data.get(rowIndex);
        return switch (columnIndex) {
            case TITLE -> currency.getTitle();
            case CODE -> currency.getCode();
            case RATE -> Format.rate(currency.getRate(), SaveData.getInstance().getBaseCurrency());
            case ON -> Format.yesNo(currency.isOn());
            case BASE -> Format.yesNo(currency.isBase());
            default -> null;
        };
    }
}
