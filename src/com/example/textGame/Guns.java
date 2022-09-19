package com.example.textGame;

public enum Guns implements IWeaponType {
    SLINGSHOT(6, 20, 75, 0, 1, 40, 1, 8, "stones"), //dmg: 0.12
    BOW(11,22,100,0,2,60,1,12,"arrows"), //dmg: 0.15
    FIREBALL_WAND(36,40,150,2,2,80,1,20,"mana"), //dmg: 0.36
    SPEAR_THROWER(8,18,150,1,1,75,1,5,"spears"), //dmg: 0.33
    SIX_SHOOTER(13, 20, 100, 1, 1, 60, 6, 25, "lightBullets"), //dmg: 0.39
    PISTOL(15,20,150,2,1,75,8,15, "lightBullets"), //dmg: 0.56
    MAGNUM(40,30,200,3,2,85,6,20, "lightBullets"), //dmg: 0.57 (PEN increase)
    MACHINE_PISTOL(8,8,150,2,1,60,25,10, "lightBullets"), //dmg: 0.6
    MAC_10(19,15,250,3,1,60,20,13, "lightBullets"), //dmg: 0.76
    BROKEN_SNIPER(60, 25, 150, 3, 2, 75, 2, 26, "sniperBullets"), //dmg: 0.9
    OTS(26,22,250,3,1,70,17,19, "lightBullets"), //dmg: 0.83
    MINI_CANNON(60,50,250,3,2,85,1,26, "sniperBullets"), //dmg: 1.02
    LIGHT_AR(36, 18, 250, 4, 2, 90, 20, 20, "ARBullets"), //dmg: 0.9
    SPAZ_SHOTGUN(65, 30, 250, 4, 2, 95, 7, 30, "ARBullets"), //dmg: 1.0
    MINI_MG(13, 6, 250, 4, 1, 50, 20, 20, "ARBullets"), //dmg: 1.1
    WIDOW_MAKER(54, 23, 300, 5, 2, 99, 5,30, "sniperBullets"), //dmg: 1.2
    AMAX(82, 20, 300, 5, 2, 60, 40,20, "ARBullets"), //dmg: 1.2
    CHAINSMOKER(15, 10, 300, 5, 1, 88, 5,30, "lightBullets"), //dmg: 1.3
    DRAGONS_BREATH(57, 15, 1000, 6, 2, 90, 40, 25, "ARBullets"); //dmg: 1.7

    int damage;
    int attackSpeed;
    int durability;
    int armourPenetration;
    int handsNeeded;
    int accuracy;
    int clipSize;
    int reloadSpeed;
    String ammoType;

    Guns(int damage, int attackSpeed, int durability, int armourPenetration, int handsNeeded, int accuracy, int clipSize, int reloadSpeed, String ammoType) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.durability = durability;
        this.armourPenetration = armourPenetration;
        this.handsNeeded = handsNeeded;
        this.accuracy = accuracy;
        this.clipSize = clipSize;
        this.reloadSpeed = reloadSpeed;
        this.ammoType = ammoType;
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
