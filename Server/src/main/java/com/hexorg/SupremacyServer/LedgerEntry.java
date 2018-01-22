package com.hexorg.SupremacyServer;

public class LedgerEntry {
    public enum CountryTarget {RUSSIA, CHINA, USA, CANADA, AFRICA, AUSTRALIA, MARKET}
    public CountryTarget from, to;
    public GameData.ResourceType type;
    public int amount, price;

    public LedgerEntry() { }

    public CountryTarget getFrom() {
        return from;
    }

    public CountryTarget getTo() {
        return to;
    }

    public GameData.ResourceType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }
}
