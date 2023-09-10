package main.gui.handler;

import main.gui.MainFrame;
import main.settings.HandlerCode;
import main.settings.Settings;
import main.settings.Text;

import java.awt.event.ActionEvent;

public class MenuSettingsHandler extends Handler {
    public MenuSettingsHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HandlerCode.MENU_SETTINGS_LANGUAGE_RUSSIAN -> Settings.setLanguage("ru");
            case HandlerCode.MENU_SETTINGS_LANGUAGE_ENGLISH -> Settings.setLanguage("en");
        }
        Text.init();
        frame.getMenu().refresh();
        super.actionPerformed(e);
    }
}
