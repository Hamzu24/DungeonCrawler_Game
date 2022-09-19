package com.example.textGame;

public enum MeleeWeapons implements IWeaponType {
    DAGGER(5, 20, 50, 0,1), //dmg: 0.25
    NUNCHUKS(3, 8, 250, 0, 1), //dmg: 0.38
    POLE(4, 6, 100, 0, 2), //dmg: 0.33
    SWORD(11 ,15, 125, 0, 2), //dmg: 0.37
    LONGSWORD(22, 23, 175, 1, 2), //dmg: 0.48
    FLAIL(11, 20,125, 1, 1), //dmg: 0.55
    HATCHET(8, 12, 125, 1, 1), //dmg: 0.67
    GREAT_AXE(30, 25, 175, 2, 2), //dmg: 0.6
    SAMURAI_SWORD(20, 14, 200, 2, 2), //dmg: 0.71
    MACHETE(10, 14, 150, 2, 1), //dmg: 0.71
    GAUNTLET(16, 19, 200, 3, 1), //dmg: 0.75
    SLEDGEHAMMER(40, 25, 200, 3, 2), //dmg: 0.84
    FIRE_GAUNTLET(18, 20, 200, 3, 1), //dmg: 0.90
    PIERCING_GAUNTLET(17, 20, 200, 4, 1), //dmg: 0.85
    WOLVERINE_CLAWS(7, 7, 200, 4, 1), //dmg: 1
    WARHAMMER(70, 30, 225, 4, 2), //dmg: 1.2
    ANCIENT_BLADE(24, 20, 225, 5, 1), //dmg: 1.2
    FLESHPOUNDER(55, 21, 225, 5, 2), //dmg: 1.3
    DRAGONS_TALON(100, 28, 500, 6, 2), //dmg: 1.8
    WEAK_CLAWS(4, 10, 250,0,1), //dmg: 0.4
    STRONG_CLAWS(11, 25, 250, 1, 1), //dmg: 0.44
    SMALL_TEETH(3, 12,250,0,1), //dmg: 0.25
    LARGE_TEETH(14, 28, 250, 1, 1), //dmg: 0.5
    STONE_FIST(18, 25, 350, 1, 1), //dmg: 0.72
    SPIDER_CLAW(1, 15, 250, 0, 1); //dmg: 0.067

    int damage;
    int attackSpeed;
    int durability;
    int armourPenetration;
    int handsNeeded;

    MeleeWeapons(int damage, int attackSpeed, int durability, int armourPenetration, int handsNeeded) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.durability = durability;
        this.armourPenetration = armourPenetration;
        this.handsNeeded = handsNeeded;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public int getArmourPenetration() {
        return armourPenetration;
    }

    @Override
    public int getHandsNeeded() {
        return handsNeeded;
    }
}
