package com.hexorg.SupremacyServer;

import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class AppController {
    private GameData state;
    @Autowired
    private ApplicationContext context;

    public AppController() {
        state = new GameData();

    }

    @RequestMapping("/gameData")
    public GameData gameData(@RequestParam(value="name", defaultValue="World") String name) {
        return state;
    }

    @RequestMapping("/buyGrain")
    public int buyGrain(@RequestParam(value="amount", defaultValue = "1") int amount) {
        state.grainBought(amount);
        Message data = new Message();
        data.type = Message.MessageType.GRAIN_BOUGHT;
        data.intData = state.getGrainPrice();
        MessageBus.getInstance().newMessage(data);
        Random rand = new Random();
                return rand.nextInt();
    }
}
