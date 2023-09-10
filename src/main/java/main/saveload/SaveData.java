package main.saveload;

import main.exception.ModelException;
import main.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class SaveData {

    private static SaveData instance;

    private List<Article> articles = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<Transfer> transfers = new ArrayList<>();
    private final Filter filter;
    private Common oldCommon;
    private boolean saved = true;

    private SaveData() {
        load();
        this.filter = new Filter();
    }

    public void load() {
        SaveLoad.load(this);
        sort();
        for (Account a : accounts) {
            a.setAmountFromTransactionAndTransfer(transactions, transfers);
        }
    }

    public void clear() {
        articles.clear();
        currencies.clear();
        accounts.clear();
        transactions.clear();
        transfers.clear();
    }

    private void sort() {
        this.articles.sort((Article t1, Article t2) -> t1.getTitle().compareToIgnoreCase(t2.getTitle()));
        this.accounts.sort((Account t1, Account t2) -> t1.getTitle().compareToIgnoreCase(t2.getTitle()));
        this.transactions.sort((Transaction t1, Transaction t2) -> (int) (t2.getDate().compareTo(t1.getDate())));
        this.transfers.sort((Transfer t1, Transfer t2) -> (int) (t2.getDate().compareTo(t1.getDate())));
        this.currencies.sort((o1, o2) -> {
            if (o1.isBase()) return -1;
            if (o2.isBase()) return 1;
            if (o1.isOn() ^ o2.isOn()) {
                if (o2.isOn()) return 1;
                else return -1;
            }
            return o1.getTitle().compareToIgnoreCase(o2.getTitle());
        });
    }

    public void save() {
        SaveLoad.save(this);
        saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public static SaveData getInstance() {
        if (instance == null) instance = new SaveData();
        return instance;
    }

    public Filter getFilter() {
        return filter;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public Currency getBaseCurrency () {
        for (Currency currency : currencies) {
            if (currency.isBase()) {
                return currency;
            }
        }
        return new Currency();
    }

    public void setArticles(List<Article> articles) {
        if (articles != null) this.articles = articles;
    }

    public void setAccounts(List<Account> accounts) {
        if (accounts != null) this.accounts = accounts;
    }

    public void setCurrencies(List<Currency> currencies) {
        if (currencies != null) this.currencies = currencies;
    }

    public void setTransactions(List<Transaction> transactions) {
        if (transactions != null) this.transactions = transactions;
    }

    public void setTransfers(List<Transfer> transfers) {
        if (transfers != null) this.transfers = transfers;
    }

    public ArrayList<Currency> getEnabledCurrencies() {
        ArrayList<Currency> list = new ArrayList<>();
        for (Currency c : currencies) {
            if (c.isOn()) list.add(c);
        }
        return list;
    }

    public List<Transaction> getFilterTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();
        for (Transaction t : transactions) {
            if (filter.check(t.getDate())) {
                list.add(t);
            }
        }
        return list;
    }

    public List<Transfer> getFilterTransfers() {
        ArrayList<Transfer> list = new ArrayList<>();
        for (Transfer t : transfers) {
            if (filter.check(t.getDate())) {
                list.add(t);
            }
        }
        return list;
    }

    public List<Transaction> getFilterTransactionsOnCount(int count) {
        return new ArrayList<>(transactions.subList(0, Math.min(count, transactions.size())));
    }

    public Common getOldCommon() {
        return oldCommon;
    }

    public void add(Common c) throws ModelException {
        List ref = getRef(c);
        if (ref.contains(c)) throw new ModelException(ModelException.IS_EXISTS);
        ref.add(c);
        c.postAdd(this);
        sort();
        saved = false;
    }

    public void edit(Common oldC, Common newC) throws ModelException {
        List ref = getRef(oldC);
        if(ref.contains(newC) && oldC != ref.get(ref.indexOf(newC))) throw new ModelException(ModelException.IS_EXISTS);
        ref.set(ref.indexOf(oldC), newC);
        oldCommon = oldC;
        newC.postEdit(this);
        sort();
        saved = false;
    }

    public void remove(Common c) {
        getRef(c).remove(c);
        c.postRemove(this);
        saved = false;
    }

    private List getRef(Common c) {
        if (c instanceof Account) return accounts;
        else if (c instanceof Article) return articles;
        else if (c instanceof Currency) return currencies;
        else if (c instanceof Transaction) return transactions;
        else if (c instanceof Transfer) return transfers;
        return null;
    }

    public void updateCurrencies() throws Exception {
        HashMap<String, Double> rates = RateCurrency.getRates(getBaseCurrency());
        for (Currency c : currencies) {
            c.setRate(rates.get(c.getCode()));
        }
        for (Account a : accounts) {
            a.getCurrency().setRate(rates.get(a.getCurrency().getCode()));
            saved = false;
        }
    }

    @Override
    public String toString() {
        return "SaveData{" +
                "articles=" + articles +
                ", currencies=" + currencies +
                ", accounts=" + accounts +
                ", transactions=" + transactions +
                ", transfers=" + transfers +
                '}';
    }
}
