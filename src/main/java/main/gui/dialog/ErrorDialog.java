package main.gui.dialog;

import main.gui.MainFrame;
import main.settings.Text;

import javax.swing.*;

public class ErrorDialog {

    public static void show (MainFrame frame, String text) {
        JOptionPane.showMessageDialog(frame, Text.get(text), Text.get("ERROR"), JOptionPane.ERROR_MESSAGE);
    }

}
