package com.example.textGame;

public class ChestTemplate implements IObject {
    private ChestTypes chestType;
    private ToolTypes toolNeeded;

    public ChestTemplate(ChestTypes chestType, ToolTypes toolNeeded) {
        this.chestType = chestType;
        this.toolNeeded = toolNeeded;
    }

    @Override
    public String getTitle() {
        return "A chest template of type ";
    }

    @Override
    public ToolTypes getToolNeeded() {
        return toolNeeded;
    }

    @Override
    public IObject getClone() {
        return ChestTypes.spawnLootBox(chestType, toolNeeded);
    }

    public ChestTypes getChestType() {
        return chestType;
    }

}
