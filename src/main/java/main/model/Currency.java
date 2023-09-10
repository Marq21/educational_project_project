package main.model;

import main.exception.ModelException;
import main.saveload.SaveData;

import java.util.Objects;

public class Currency extends Common {

    private String title;
    private String code;
    private double rate;
    private boolean isOn;
    private boolean base;

    public Currency() {}

    public Currency(String title,
                    String code,
                    double rate,
                    boolean isOn,
                    boolean base) throws ModelException {
        if(title.length() == 0) throw new ModelException(ModelException.TITLE_EMPTY);
        if(code.length() == 0) throw new ModelException(ModelException.CODE_EMPTY);
        if(rate <= 0) throw new ModelException(ModelException.RATE_INCORRECT);
        this.title = title;
        this.code = code;
        this.rate = rate;
        this.isOn = isOn;
        this.base = base;
    }

    @Override
    public String getValueForComboBox() {
        return title;
    }

    public double getRateByCurrency(Currency currency) {
        return rate / currency.rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", rate=" + rate +
                ", isOn=" + isOn +
                ", isBase=" + base +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public void postAdd (SaveData sd) {
        clearBase(sd);
    }

    @Override
    public void postEdit (SaveData sd) {
        clearBase(sd);
        for (Account a : sd.getAccounts()) {
            if (a.getCurrency().equals(sd.getOldCommon())) {
                a.setCurrency(this);
            }
        }
    }

    private void clearBase(SaveData sd) {
        if (base) {
            rate = 1;
            Currency old = (Currency) sd.getOldCommon();
            for (Currency c : sd.getCurrencies()) {
                if (!this.equals(c)) {
                    c.setBase(false);
                    if (old != null) {
                        c.setRate(c.rate / old.rate);
                    }
                }
            }
        }
    }
}
