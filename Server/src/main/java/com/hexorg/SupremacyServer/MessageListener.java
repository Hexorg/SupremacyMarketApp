package com.hexorg.SupremacyServer;

public interface MessageListener {
    public void processMessage(GameData data);
    public void nextPhase();
    public void initData();
}
