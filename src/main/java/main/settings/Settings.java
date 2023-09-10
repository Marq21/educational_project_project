package main.settings;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public final class Settings {

    public static final File FONT_ROBOTO_LIGHT = new File("fonts/Roboto-Light.ttf");
    public static final File SAVE_DIR = new File("saves/");
    public static final String SAVE_FILE_EXT = "myrus";
    public static final String FORMAT_AMOUNT = "%.2f";
    public static final String FORMAT_RATE = "%.4f";
    public static final String FORMAT_DATE = "dd.MM.yyyy";
    public static final String FORMAT_DATE_MONTH = "MMMM yyyy";
    public static final String FORMAT_DATE_YEAR = "yyyy";
    public static final int COUNT_OVERVIEW_ROWS = 10;
    public static final String[] CURRENCIES_CODE = new String[]{
            "RUB", "USD", "UER", "BYN", "UAH"
    };
    private static final File FILE_SETTINGS = new File("saves/settings.ini");

    private static File FILE_SAVE = new File("saves/default.myrus");
    private static String LANGUAGE = "ru";

    public static void init() {
        try {
            Ini ini = new Ini(FILE_SETTINGS);
            Preferences prefs = new IniPreferences(ini);
            String file = prefs.node("Settings").get("FILE_SAVE", null);
            if (file != null) FILE_SAVE = new File(file);
            String language = prefs.node("Settings").get("LANGUAGE", null);
            if (language != null) LANGUAGE = language;
            setLocale();
        } catch (IOException e) {
            save();
        }
    }

    public static File getFileSave() {
        return FILE_SAVE;
    }

    public static void setFileSave(File file) {
        FILE_SAVE = file;
        save();
    }

    public static String getLanguage() {
        return LANGUAGE;
    }

    public static void setLanguage(String language) {
        LANGUAGE = language;
        setLocale();
        save();
    }

    private static void save() {
        try {
            Wini ini = new Wini(FILE_SETTINGS);
            if (FILE_SAVE != null) ini.put("Settings", "FILE_SAVE", FILE_SAVE.getAbsolutePath().replace("\\", "\\\\"));
            ini.put("Settings", "LANGUAGE", LANGUAGE);
            ini.store();
        } catch (IOException e) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void setLocale() {
        if (LANGUAGE.equals("ru")) Locale.setDefault(new Locale("ru"));
        else Locale.setDefault(new Locale("en"));

    }
}
