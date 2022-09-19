package com.example.textGame;

import java.util.*;

public abstract class Weapon {
    private static final List<Float> armourDifferences = new ArrayList<>(Arrays.asList(1f, 1.4f, 2f, 2.8f, 3.8f, 5f, 5.5f, 7.5f, 10f));
    private int damage;
    private int attackSpeed;
    private int durability;
    private int armourPenetration;
    private final IWeaponType weaponType;
    private Attributes attribute;
    private int handsNeeded;

    public Weapon(IWeaponType type, Attributes attribute) {
        this.weaponType = type;
        this.attribute = attribute;
        this.damage = Math.round(type.getDamage() * attribute.damageModifier);
        this.attackSpeed = Math.round(type.getAttackSpeed() / attribute.attackSpeedModifier);
        this.armourPenetration = type.getArmourPenetration();
        this.durability = Math.round(type.getDurability() * attribute.durabilityModifier);
        this.handsNeeded = type.getHandsNeeded();
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getDurability() {
        return durability;
    }

    public void sharpen(int modifier) {
        this.durability += modifier;
    }

    public IWeaponType getWeaponType() {
        return weaponType;
    }

    public int getArmourPenetration() {
        return armourPenetration;
    }

    public Attributes getAttribute() {
        return attribute;
    }

    public int getHandsNeeded() {
        return handsNeeded;
    }

    public int attack(Entity target, Entity user, Encounter curEncounter, int teamNumber, Hashtable<EntityAttributes, Encounter.potionEffect> potionsAffecting) { //damage, critChance, armourPenetration, defence
        int armourDifference;
        int defence = potionsAffecting.get("defence").getPotion().isMultiplier() ? target.getDefence() * potionsAffecting.get("defence").getPotion().getMagnitude() : target.getDefence() + potionsAffecting.get("defence").getPotion().getMagnitude();
        int armourPenetration = potionsAffecting.get("armourPenetration").getPotion().isMultiplier() ? this.armourPenetration * potionsAffecting.get("armourPenetration").getPotion().getMagnitude() : this.armourPenetration + potionsAffecting.get("armourPenetration").getPotion().getMagnitude();
        int damage = potionsAffecting.get("damage").getPotion().isMultiplier() ? this.damage * potionsAffecting.get("damage").getPotion().getMagnitude() : this.damage + potionsAffecting.get("damage").getPotion().getMagnitude();
        /* critChance is yet to be used!!! */

        armourDifference = Math.max(defence - armourPenetration, 0);
        int damageDealt = Math.round(damage / armourDifferences.get(armourDifference));
        int targetTeamNumber = (teamNumber+1)%2;
        target.changeHealth(-1 * damageDealt, curEncounter, targetTeamNumber);

        sharpen(-1);
        if (this.durability <= 0) {
            this.durability = 0;
            breakWeapon(user, curEncounter);
        }
        return damageDealt;
    }

    public void breakWeapon(Entity user, Encounter curEncounter) {
        int index = user.getEquippedWeapons().indexOf(this);
        System.out.println(user.getTitle() +"'s "+ weaponType.toString() +" has broken!");
        curEncounter.removeWeaponInstances(this);
        user.unequipWeapon(index);
    }

    public boolean hasAmmo() {
        return true;
    }

    public abstract Weapon getClone(Attributes attribute);

}