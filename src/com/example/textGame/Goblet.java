package com.example.textGame;

public class Goblet implements IObject {
    int intensity;
    FlameTypes flameType;
    ToolTypes toolNeeded;

    public Goblet(int flameIntensity, FlameTypes flameType, ToolTypes toolNeeded) {
        this.intensity = flameIntensity;
        this.flameType = flameType;
        this.toolNeeded = toolNeeded;
    }

    @Override
    public String getTitle() {
        return "A "+ flameType.toString().toLowerCase() +" goblet with a flame intensity of "+ intensity;
    }

    @Override
    public IObject getClone() {
        return new Goblet(intensity, flameType, toolNeeded);
    }

    @Override
    public ToolTypes getToolNeeded() {
        return this.toolNeeded;
    }
}
