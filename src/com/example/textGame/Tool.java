package com.example.textGame;

public class Tool extends Item {
    int durability;
    ToolTypes toolType;
    ToolRarities toolRarity;

    public Tool(ToolTypes toolType, ToolRarities toolRarity) {
        this.toolType = toolType;
        this.toolRarity = toolRarity;
        this.durability = this.toolType.durabilityList.get(toolRarity);
    }

    public int getDurability() {
        return durability;
    }

    public ToolTypes getToolType() {
        return toolType;
    }

    public ToolRarities getToolRarity() {
        return toolRarity;
    }

    public void use() { //MORE TO ADD
        this.durability -= 1;
    }

    public void restoreDurability(int increase) {
        this.durability += increase;
    }

    @Override
    public String getTitle() {
        return "A "+ toolRarity.toString() +" "+ toolType.toString();
    }

    @Override
    public Item getClone() {
        return new Tool(toolType, toolRarity);
    }
}
