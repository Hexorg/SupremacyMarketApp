package com.hexorg.SupremacyServer;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.ietf.jgss.Oid;

@SpringUI
@Title("Supremacy Market Server")
@Push
public class MainUI extends UI implements MessageListener {
    private Label turnLabel, phaseLabel, mineralPriceLabel, oilPriceLabel, grainPriceLabel;

    @Override
    protected void init(VaadinRequest request) {
        MessageBus.getInstance().addMessageListener(this);
        // Create the content root layout for the UI
        VerticalLayout content = new VerticalLayout();
        setContent(content);
        content.setWidthUndefined();

        content.addComponent(new Label("<h1>Supremacy</h1>", ContentMode.HTML));
        turnLabel = new Label("Turn: 1");
        phaseLabel = new Label("Phase: Upkeep");
        mineralPriceLabel = new Label("Mineral: ");
        oilPriceLabel = new Label("Oil: ");
        grainPriceLabel = new Label("Grain: ");

        content.addComponent(turnLabel);
        content.addComponent(phaseLabel);

        content.addComponent(new Label("<h2>Market:<h2>", ContentMode.HTML));
        content.addComponent(mineralPriceLabel);
        content.addComponent(oilPriceLabel);
        content.addComponent(grainPriceLabel);

        content.addComponent(new Button("Try me"));
    }

    public void processMessage(GameData data) {
        setTurn(data.getTurn());
        setPhase(data.getPhase());
        setGrainPriceLabel(data.getResourcePrice(GameData.ResourceType.GRAIN));
        setMineralPrice(data.getResourcePrice(GameData.ResourceType.MINERAL));
        setOilPriceLabel(data.getResourcePrice(GameData.ResourceType.OIL));
        push();
    }

    public void setTurn(int value) {
        turnLabel.setValue("Turn: "+Integer.toString(value));
    }

    public void setPhase(GameData.Phase phase) {
        String newValue = "Phase: ";
        switch (phase) {
            case UPKEEP: newValue += "Upkeep"; break;
            case BUY: newValue += "Buy"; break;
            case MOVE: newValue += "Move"; break;
            case SELL: newValue += "Sell"; break;
            case BUILD: newValue += "Build armies"; break;
            case ATTACK: newValue += "Attack!"; break;
            case PROSPECT: newValue += "Prospect or Research"; break;
            case HARVEST_RESOURCES: newValue += "Collect resources"; break;
        }
        phaseLabel.setValue(newValue);
    }

    public void setMineralPrice(int value) {
        mineralPriceLabel.setValue("Mineral: "+Integer.toString(value)+" millions");
    }

    public void setOilPriceLabel(int value) {
        oilPriceLabel.setValue("Oil: "+Integer.toString(value)+" millions");
    }

    public void setGrainPriceLabel(int value) {
        grainPriceLabel.setValue("Grain: "+Integer.toString(value)+" millions");
    }
}
