package com.example.textGame;

public class WallRune implements IObject {
    Runes runeType;
    ToolTypes toolNeeded;

    public WallRune(Runes runeType, ToolTypes toolNeeded) {
        this.runeType = runeType;
        this.toolNeeded = toolNeeded;
    }

    @Override
    public String getTitle() {
        return "A rune of "+ runeType.toString();
    }

    @Override
    public ToolTypes getToolNeeded() {
        return toolNeeded;
    }

    @Override
    public IObject getClone() {
        return new WallRune(runeType, toolNeeded);
    }
}
