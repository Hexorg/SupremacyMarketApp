package com.hexorg.SupremacyServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameData {
    private int turn;
    public enum Phase {UPKEEP, HARVEST_RESOURCES, SELL, ATTACK, MOVE, BUILD, PROSPECT, BUY}
    public enum ResourceType {MINERAL, OIL, GRAIN}
    private final int[] marketPricing = {1, 5, 10, 25, 50, 50, 100, 100, 200, 200, 400, 400, 500, 600, 700, 800, 900, 1000};
    private Phase phase;
    private int[] resourcePrices;
    private List<LedgerEntry> ledger;

    public GameData() {
        phase = Phase.UPKEEP;
        turn = 1;
        Random rand = new Random(System.currentTimeMillis());
        resourcePrices = new int[ResourceType.values().length];
        for (int i=0; i<resourcePrices.length; i++) {
            resourcePrices[i] = rand.nextInt(marketPricing.length);
        }
        ledger = new ArrayList<>();
    }

    public void nextTurn() {
        turn += 1;
    }

    public void addLedgerEntry(LedgerEntry entry) {
        ledger.add(entry);
    }

    public void removeLastLedgerEntry(LedgerEntry entry) {
        ledger.remove(ledger.size()-1);
    }

    public void nextPhase() {
        int nextPhase = phase.ordinal() + 1;
        if (nextPhase > Phase.values().length)
            nextPhase = 0;
        phase = Phase.values()[nextPhase];
    }

    public void buyResource(ResourceType type, int units) {
        resourcePrices[type.ordinal()] -= units;
        if (resourcePrices[type.ordinal()] < 0)
            resourcePrices[type.ordinal()] = 0;
    }

    public void sellResource(ResourceType type, int units) {
        resourcePrices[type.ordinal()] += units;
        if (resourcePrices[type.ordinal()] >= marketPricing.length);
            resourcePrices[type.ordinal()] = marketPricing.length-1;
    }

    public int getResourcePrice(ResourceType type) { return marketPricing[resourcePrices[type.ordinal()]]; }


    public Phase getPhase() { return phase; }

    public int getTurn() { return turn; }

    public int getGrainPrice() { return getResourcePrice(ResourceType.GRAIN); }

    public int getMineralPrice() { return getResourcePrice(ResourceType.MINERAL); }

    public int getOilPrice() { return getResourcePrice(ResourceType.OIL); }

    public LedgerEntry getLastLedgerEntry() {
        if (ledger.size() != 0)
            return ledger.get(ledger.size()-1);
        else
            return null;
    }
}
