package main.gui.menu;

import main.gui.EnableEditDelete;
import main.gui.MainFrame;
import main.gui.Refresh;
import main.gui.handler.*;
import main.settings.HandlerCode;
import main.settings.Settings;
import main.settings.Style;
import main.settings.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static main.settings.Style.*;

public class MainMenu extends JMenuBar implements Refresh, EnableEditDelete {

    private JMenuItem menuEdit;
    private JMenuItem menuDelete;
    private final MainFrame frame;

    public MainMenu(MainFrame frame) {
        super();
        this.frame = frame;
        init();
    }

    private void init() {
        JMenu file = new JMenu(Text.get("MENU_FILE"));
        JMenu edit = new JMenu(Text.get("MENU_EDIT"));
        JMenu view = new JMenu(Text.get("MENU_VIEW"));
        JMenu settings = new JMenu(Text.get("MENU_SETTINGS"));
        JMenu help = new JMenu(Text.get("MENU_HELP"));

        file.setIcon(Style.ICON_MENU_FILE);
        edit.setIcon(Style.ICON_MENU_EDIT);
        view.setIcon(Style.ICON_MENU_VIEW);
        settings.setIcon(ICON_MENU_SETTINGS);
        help.setIcon(Style.ICON_MENU_HELP);

        add(file);
        add(edit);
        add(view);
        add(settings);
        add(help);

        MenuFileHandler fileHandler = new MenuFileHandler(frame);
        MenuEditHandler editHandler = new MenuEditHandler(frame);
        MenuViewHandler viewHandler = new MenuViewHandler(frame);
        MenuSettingsHandler settingsHandler = new MenuSettingsHandler(frame);
        MenuHelpHandler helpHandler = new MenuHelpHandler(frame);

        addMenuItem(file, fileHandler, Text.get("MENU_FILE_NEW"), Style.ICON_MENU_FILE_NEW, HandlerCode.MENU_FILE_NEW, KeyEvent.VK_N);
        addMenuItem(file, fileHandler, Text.get("MENU_FILE_OPEN"), Style.ICON_MENU_FILE_OPEN, HandlerCode.MENU_FILE_OPEN, KeyEvent.VK_O);
        addMenuItem(file, fileHandler, Text.get("MENU_FILE_SAVE"), Style.ICON_MENU_FILE_SAVE, HandlerCode.MENU_FILE_SAVE, KeyEvent.VK_S);
        addMenuItem(file, fileHandler, Text.get("MENU_FILE_UPDATE_CURRENCIES"), Style.ICON_MENU_FILE_UPDATE_CURRENCIES, HandlerCode.MENU_FILE_UPDATE_CURRENCIES);
        addMenuItem(file, fileHandler, Text.get("MENU_FILE_EXIT"), Style.ICON_MENU_FILE_EXIT, HandlerCode.MENU_FILE_EXIT);

        addMenuItem(edit, editHandler, Text.get("MENU_EDIT_ADD"), ICON_MENU_EDIT_ADD, HandlerCode.MENU_EDIT_ADD);
        menuEdit = addMenuItem(edit, editHandler, Text.get("MENU_EDIT_EDIT"), ICON_MENU_EDIT_EDIT, HandlerCode.MENU_EDIT_EDIT);
        menuDelete = addMenuItem(edit, editHandler, Text.get("MENU_EDIT_DELETE"), ICON_MENU_EDIT_DELETE, HandlerCode.MENU_EDIT_DELETE);

        menuEdit.setEnabled(false);
        menuDelete.setEnabled(false);

        addMenuItem(view, viewHandler, Text.get("MENU_VIEW_OVERVIEW"), Style.ICON_MENU_VIEW_OVERVIEW, HandlerCode.MENU_VIEW_OVERVIEW);
        addMenuItem(view, viewHandler, Text.get("MENU_VIEW_ACCOUNTS"), Style.ICON_MENU_VIEW_ACCOUNTS, HandlerCode.MENU_VIEW_ACCOUNTS);
        addMenuItem(view, viewHandler, Text.get("MENU_VIEW_ARTICLES"), Style.ICON_MENU_VIEW_ARTICLES, HandlerCode.MENU_VIEW_ARTICLES);
        addMenuItem(view, viewHandler, Text.get("MENU_VIEW_TRANSACTIONS"), Style.ICON_MENU_VIEW_TRANSACTIONS, HandlerCode.MENU_VIEW_TRANSACTIONS);
        addMenuItem(view, viewHandler, Text.get("MENU_VIEW_TRANSFERS"), Style.ICON_MENU_VIEW_TRANSFERS, HandlerCode.MENU_VIEW_TRANSFERS);
        addMenuItem(view, viewHandler, Text.get("MENU_VIEW_CURRENCIES"), Style.ICON_MENU_VIEW_CURRENCIES, HandlerCode.MENU_VIEW_CURRENCIES);
        addMenuItem(view, viewHandler, Text.get("MENU_VIEW_STATISTICS"), Style.ICON_MENU_VIEW_STATISTICS, HandlerCode.MENU_VIEW_STATISTICS);

        JMenu language = new JMenu(Text.get("MENU_SETTINGS_LANGUAGE"));
        language.setIcon(ICON_MENU_SETTINGS_LANGUAGE);
        settings.add(language);

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem menuRussian = new JRadioButtonMenuItem(Text.get("MENU_SETTINGS_LANGUAGE_RUSSIAN"));
        JRadioButtonMenuItem menuEnglish = new JRadioButtonMenuItem(Text.get("MENU_SETTINGS_LANGUAGE_ENGLISH"));
        group.add(menuRussian);
        group.add(menuEnglish);

        menuRussian.setIcon(ICON_MENU_SETTINGS_LANGUAGE_RUSSIAN);
        menuEnglish.setIcon(ICON_MENU_SETTINGS_LANGUAGE_ENGLISH);
        menuRussian.setActionCommand(HandlerCode.MENU_SETTINGS_LANGUAGE_RUSSIAN);
        menuEnglish.setActionCommand(HandlerCode.MENU_SETTINGS_LANGUAGE_ENGLISH);

        menuRussian.addActionListener(settingsHandler);
        menuEnglish.addActionListener(settingsHandler);

        if (Settings.getLanguage().equals("ru")) menuRussian.setSelected(true);
        else if (Settings.getLanguage().equals("en")) menuEnglish.setSelected(true);

        language.add(menuRussian);
        language.add(menuEnglish);

        addMenuItem(help, helpHandler, Text.get("MENU_HELP_ABOUT"), Style.ICON_MENU_HELP_ABOUT, HandlerCode.MENU_HELP_ABOUT);

    }

    private JMenuItem addMenuItem(JMenu menu, Handler listener, String title, ImageIcon icon, String action, int key) {
        JMenuItem item = new JMenuItem(title);
        item.setIcon(icon);
        item.setActionCommand(action);
        item.addActionListener(listener);
        if (key != 0) {
            KeyStroke shortKey = KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
            item.setAccelerator(shortKey);
        }

        menu.add(item);
        return item;
    }

    private JMenuItem addMenuItem(JMenu menu, Handler listener, String title, ImageIcon icon, String action) {
        return addMenuItem(menu, listener, title, icon, action, 0);
    }

    @Override
    public void setEnableEditDelete(boolean enable) {
        menuEdit.setEnabled(enable);
        menuDelete.setEnabled(enable);
    }

    @Override
    public void refresh() {
        removeAll();
        init();
    }
}
