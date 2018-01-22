package com.hexorg.SupremacyServer;

import java.util.ArrayList;
import java.util.List;

public class MessageBus {
    private static MessageBus instance;
    private List<MessageListener> listeners;

    private MessageBus() {
        listeners = new ArrayList<>();
    }

    public static MessageBus getInstance() {
        if (instance == null) {
            instance = new MessageBus();
        }
        return instance;
    }

    public void addMessageListener(MessageListener m) {
        listeners.add(m);
    }

    public void newMessage(GameData data) {
        for (MessageListener m: listeners) {
            m.processMessage(data);
        }
    }

    public void initData() {
        for (MessageListener m: listeners) {
            m.initData();
        }
    }

    public void nextPhase() {
        for (MessageListener m: listeners) {
            m.nextPhase();
        }
    }

}
