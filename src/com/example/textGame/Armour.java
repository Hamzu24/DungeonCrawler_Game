package com.example.textGame;

public class Armour extends Item {
    final private ArmourTypes type;
    final private int defence;
    private int durability;

    public Armour(ArmourTypes type) {
        this.type = type;
        this.defence = type.defence;
        this.durability = type.durability;
    }

    public void clean(int modifier) {
        this.durability += modifier;
    }

    @Override
    public String getTitle() {
        return type.toString().toLowerCase();
    }

    public ArmourTypes getType() {
        return type;
    }

    public int getDefence() {
        return defence;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public Armour getClone() {
        return new Armour(type);
    }
}
