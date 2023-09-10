package main.gui;

import main.settings.Style;
import main.settings.Text;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.util.Date;
import java.util.Properties;

public class MainDatePicker {

    private final JDatePickerImpl datePicker;

    public MainDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties props = new Properties();
        props.put("text.today", Text.get("TODAY"));
        JDatePanelImpl datePanel = new JDatePanelImpl(model, props);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        model.setValue(new Date());

        JButton button = (JButton) datePicker.getComponent(1);
        button.setIcon(Style.ICON_DATE);
        button.setText("");
    }

    public void setValue(Date date) {
        ((UtilDateModel) datePicker.getModel()).setValue(date);
    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }
}
