package main.gui.handler;

import main.gui.MainFrame;
import main.gui.dialog.AboutDialog;
import main.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class MenuHelpHandler extends Handler {

    public MenuHelpHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(HandlerCode.MENU_HELP_ABOUT)) {
            new AboutDialog().setVisible(true);
        }
        super.actionPerformed(e);
    }

}

