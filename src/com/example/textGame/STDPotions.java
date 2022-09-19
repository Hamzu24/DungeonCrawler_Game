package com.example.textGame;

public enum STDPotions { //Potion name format: duration (short, long)_ size (small, medium, large, giant)_ attributeAffected
    //Health (no duration values for health potions)
    S_HEALTH(EntityAttributes.HEALTH, 0, false, 10),
    M_HEALTH(EntityAttributes.HEALTH, 0, false, 25),
    L_HEALTH(EntityAttributes.HEALTH, 0, false, 50),
    G_HEALTH(EntityAttributes.HEALTH, 0, false, 80),
    COCAINE(EntityAttributes.HEALTH, 0, true, 2), //Steroids multiply health
    SPEED(EntityAttributes.HEALTH, 0, false, 3),
    AMPHETAMINE(EntityAttributes.HEALTH, 0, false, 5),

    //Damage
    S_S_DAMAGE(EntityAttributes.DAMAGE, 20, false, 5),
    S_M_DAMAGE(EntityAttributes.DAMAGE, 20, false, 10),
    S_L_DAMAGE(EntityAttributes.DAMAGE, 20, false, 17),
    L_S_DAMAGE(EntityAttributes.DAMAGE, 50, false, 5),
    L_M_DAMAGE(EntityAttributes.DAMAGE, 50, false, 10),
    L_L_DAMAGE(EntityAttributes.DAMAGE, 50, false, 17),

    //Speed
    S_S_SPEED(EntityAttributes.SPEED, 20, false, 10),
    S_M_SPEED(EntityAttributes.SPEED, 20, false, 20),
    S_L_SPEED(EntityAttributes.SPEED, 20, false, 30),
    L_S_SPEED(EntityAttributes.SPEED, 50, false, 10),
    L_M_SPEED(EntityAttributes.SPEED, 50, false, 20),
    L_L_SPEED(EntityAttributes.SPEED, 50, false, 30),

    //Defence
    S_S_DEFENCE(EntityAttributes.DEFENCE, 20, false, 1),
    S_M_DEFENCE(EntityAttributes.DEFENCE, 20, false, 2),
    S_L_DEFENCE(EntityAttributes.DEFENCE, 20, false, 3),
    L_S_DEFENCE(EntityAttributes.DEFENCE, 50, false, 1),
    L_M_DEFENCE(EntityAttributes.DEFENCE, 50, false, 2),
    L_L_DEFENCE(EntityAttributes.DEFENCE, 50, false, 3),

    //Armour Penetration
    S_S_ARMOUR_PENETRATION(EntityAttributes.ARMOUR_PENETRATION, 20, false, 1),
    S_M_ARMOUR_PENETRATION(EntityAttributes.ARMOUR_PENETRATION, 20, false, 2),
    S_L_ARMOUR_PENETRATION(EntityAttributes.ARMOUR_PENETRATION, 20, false, 3),
    L_S_ARMOUR_PENETRATION(EntityAttributes.ARMOUR_PENETRATION, 50, false, 1),
    L_M_ARMOUR_PENETRATION(EntityAttributes.ARMOUR_PENETRATION, 50, false, 2),
    L_L_ARMOUR_PENETRATION(EntityAttributes.ARMOUR_PENETRATION, 50, false, 3);

    //Critical chance standard potions haven't been made yet

    EntityAttributes attributeAffected;
    int duration;
    boolean isMultiplier;
    int magnitude;

    STDPotions(EntityAttributes attributeAffected, int duration, boolean isMultiplier, int magnitude) {
        this.attributeAffected = attributeAffected;
        this.duration = duration;
        this.isMultiplier = isMultiplier;
        this.magnitude = magnitude;
    }

    public static Potion getSTDPotion(STDPotions potionType) {
        return new Potion(potionType.attributeAffected, potionType.isMultiplier, potionType.duration, potionType.magnitude);
    }
}
