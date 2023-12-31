package main.gui.dialog;

import main.exception.ModelException;
import main.gui.MainFrame;
import main.model.Common;
import main.model.Currency;
import main.settings.Format;
import main.settings.Settings;
import main.settings.Style;
import main.settings.Text;

import javax.swing.*;
import java.util.Objects;

public class CurrencyAddEditDialog extends AddEditDialog{

    public CurrencyAddEditDialog(MainFrame frame) {
        super(frame);
    }

    @Override
    protected void init() {
        components.put("LABEL_TITLE", new JTextField());
        components.put("LABEL_CODE", new JComboBox<>(Settings.CURRENCIES_CODE));
        components.put("LABEL_RATE", new JTextField());
        components.put("LABEL_ON", new JComboBox<>(new String[] {Text.get("YES"), Text.get("NO")}));
        components.put("LABEL_BASE", new JComboBox<>(new String[] {Text.get("YES"), Text.get("NO")}));

        icons.put("LABEL_TITLE", Style.ICON_TITLE);
        icons.put("LABEL_CODE", Style.ICON_CODE);
        icons.put("LABEL_RATE", Style.ICON_RATE);
        icons.put("LABEL_ON", Style.ICON_ON);
        icons.put("LABEL_BASE", Style.ICON_BASE);

        values.put("LABEL_RATE", Format.amount(1));
    }

    @Override
    protected void setValues() {
        Currency currency = (Currency) c;
        values.put("LABEL_TITLE", currency.getTitle());
        values.put("LABEL_CODE", currency.getCode());
        values.put("LABEL_RATE", currency.getRate());
        if (currency.isOn()) values.put("LABEL_ON", Text.get("YES"));
        else values.put("LABEL_ON", Text.get("NO"));
        if (currency.isBase()) values.put("LABEL_BASE", Text.get("YES"));
        else values.put("LABEL_BASE", Text.get("NO"));
    }

    @Override
    public Common getCommonFromForm() throws ModelException {
        try {
            String title = ((JTextField) components.get("LABEL_TITLE")).getText();
            String code = (String) ((JComboBox<?>) components.get("LABEL_CODE")).getSelectedItem();
            String rate = ((JTextField) components.get("LABEL_RATE")).getText();
            boolean isOn = Objects.equals(((JComboBox<?>) components.get("LABEL_ON")).getSelectedItem(), Text.get("YES"));
            boolean isBase = Objects.equals(((JComboBox<?>) components.get("LABEL_BASE")).getSelectedItem(), Text.get("YES"));
            if (!isBase && c != null && ((Currency) c).isBase()) {
                throw new ModelException(ModelException.NO_BASE_CURRENCY);
            }
            return new Currency(title, code, Format.fromAmountToNumber(rate), isOn, isBase);
        } catch (NumberFormatException ex) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }
    }
}
