package com.example.textGame;

public class Potion extends Item {
    private EntityAttributes attributeAffected;
    private int duration;
    private boolean isMultiplier;
    private int magnitude;

    public Potion(EntityAttributes attributeAffected, boolean isMultiplier, int magnitude, int duration) {
        this.attributeAffected = attributeAffected;
        this.isMultiplier = isMultiplier;
        this.magnitude = magnitude;
        this.duration = duration;
    }

    public EntityAttributes getAttributeAffected() {
        return attributeAffected;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isMultiplier() {
        return isMultiplier;
    }

    public int getMagnitude() {
        return magnitude;
    }

    @Override
    public String getTitle() {
        return "TBD";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getAttributeAffected()).append(" potion. ");
        if (this.isMultiplier) {
            sb.append("Multiplies value by ");
        } else {
            sb.append("Increases value by ");
        }
        sb.append(this.magnitude).append(" for ").append(this.duration).append(" minutes");
        return sb.toString();
    }

    @Override
    public Item getClone() {
        return new Potion(attributeAffected, isMultiplier, magnitude, duration);
    }
}
