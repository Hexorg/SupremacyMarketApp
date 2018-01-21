package com.hexorg.SupremacyServer;

public class LedgerEntry {
    public enum CountryTarget {RUSSIA, CHINA, USA, CANADA, AFRICA, AUSTRALIA, MARKET}
    public CountryTarget from, to;
    public GameData.ResourceType type;
    public int amount;

}
