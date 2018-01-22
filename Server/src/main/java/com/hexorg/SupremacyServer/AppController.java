package com.hexorg.SupremacyServer;

import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class AppController implements MessageListener {
    private GameData state;
    @Autowired
    private ApplicationContext context;

    public AppController() {
        state = new GameData();
        MessageBus.getInstance().addMessageListener(this);

    }

    @RequestMapping("/gameData")
    public GameData gameData() {
        return state;
    }

    @RequestMapping("/buy")
    public GameData buy(@RequestParam(value="type") GameData.ResourceType type,
                        @RequestParam(value="amount") int amount,
                        @RequestParam(value="target", defaultValue="MARKET")LedgerEntry.CountryTarget target) {

        LedgerEntry entry = new LedgerEntry();
        entry.amount = amount;
        entry.from = LedgerEntry.CountryTarget.RUSSIA;
        entry.to = target;
        entry.type = type;
        entry.price = state.getResourcePrice(type);
        state.addLedgerEntry(entry);
        state.buyResource(type, amount);
        MessageBus.getInstance().newMessage(state);
        return state;
    }

    @RequestMapping("/sell")
    public GameData sell(@RequestParam(value="type") GameData.ResourceType type,
                         @RequestParam(value="amount") int amount,
                         @RequestParam(value="target", defaultValue="MARKET")LedgerEntry.CountryTarget target) {
        LedgerEntry entry = new LedgerEntry();
        entry.amount = amount;
        entry.from = LedgerEntry.CountryTarget.RUSSIA;
        entry.to = target;
        entry.type = type;
        entry.price = state.getResourcePrice(type);
        state.addLedgerEntry(entry);
        state.sellResource(type, amount);
        MessageBus.getInstance().newMessage(state);
        return state;
    }



    @Override
    public void processMessage(GameData data) {

    }

    public void initData() {
        MessageBus.getInstance().newMessage(state);
    }

    public void nextPhase() {
        state.nextPhase();
        MessageBus.getInstance().newMessage(state);
    }
}
