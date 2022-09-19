package com.example.textGame;

import java.util.HashMap;

public class Merchant implements IObject {
    HashMap<Item, Integer> inventory;
    String appearance;
    ToolTypes toolNeeded;

    public Merchant(HashMap<Item, Integer> inventory, String appearance, ToolTypes toolNeeded) {
        this.inventory = inventory;
        this.appearance = appearance;
        this.toolNeeded = toolNeeded;
    }

    @Override
    public String getTitle() {
        return "A "+ appearance +" merchant willing to trade";
    }

    @Override
    public ToolTypes getToolNeeded() {
        return toolNeeded;
    }

    @Override
    public IObject getClone() {
        HashMap<Item, Integer> newInventory = new HashMap<>();
        for (Item item : inventory.keySet()) {
            newInventory.put(item.getClone(), inventory.get(item));
        }
        return new Merchant(newInventory, appearance, toolNeeded);
    }
}
