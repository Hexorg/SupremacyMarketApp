package com.hexorg.SupremacyServer;

import java.util.Random;

public class GameData {
    private int turn;
    public enum Phase {UPKEEP, HARVEST_RESOURCES, SELL, ATTACK, MOVE, BUILD, PROSPECT, BUY}
    private final int[] marketPricing = {1, 5, 10, 25, 50, 50, 100, 100, 200, 200, 400, 400, 500, 600, 700, 800, 900, 1000};
    private Phase phase;
    private int mineralPrice;
    private int oilPrice;
    private int grainPrice;

    public GameData() {
        phase = Phase.UPKEEP;
        turn = 1;
        Random rand = new Random();
        mineralPrice = rand.nextInt(marketPricing.length);
        oilPrice = rand.nextInt(marketPricing.length);
        grainPrice = rand.nextInt(marketPricing.length);

    }

    public void nextTurn() {
        turn += 1;
    }

    public void nextPhase() {
        int nextPhase = phase.ordinal() + 1;
        if (nextPhase > Phase.values().length)
            nextPhase = 0;
        phase = Phase.values()[nextPhase];
    }

    public void oilBought(int units) {
        oilPrice -= units;
        if (oilPrice < 0)
            oilPrice = 0;
    }

    public void oilSold(int units) {
        oilPrice += units;
        if (oilPrice >= marketPricing.length)
            oilPrice = marketPricing.length-1;
    }

    public void mineralsBought(int units) {
        mineralPrice -= units;
        if (mineralPrice < 0)
            mineralPrice = 0;
    }

    public void mineralsSold(int units) {
        mineralPrice += units;
        if (mineralPrice >= marketPricing.length)
            mineralPrice = marketPricing.length-1;
    }

    public void grainBought(int units) {
        grainPrice -= units;
        if (grainPrice < 0)
            grainPrice = 0;
    }

    public void grainSold(int units) {
        grainPrice += units;
        if (grainPrice >= marketPricing.length)
            grainPrice = marketPricing.length-1;
    }

    public Phase getPhase() { return phase; }

    public int getTurn() { return turn; }

    public int getMineralPrice() { return marketPricing[mineralPrice]; }

    public int getOilPrice() { return  marketPricing[oilPrice]; }

    public int getGrainPrice() { return marketPricing[grainPrice]; }
}
