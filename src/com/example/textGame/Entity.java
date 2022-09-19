package com.example.textGame;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements IHasWeapons {
    private int health;
    private int defence;
    private int speed;
    private List<Weapon> weapons;
    private List<Weapon> equippedWeapons;
    private int handCapacity;
    private int initiative;
    private int position;
    private Armour currentArmour;
    private List<Encounter.weaponInstance> weaponInstances;

    public Entity(int health, int defence, int speed, int initiative, int handCapacity) {
        this.health = health;
        this.defence = defence;
        this.speed = speed;
        this.initiative = initiative;
        this.handCapacity = handCapacity;
        weapons = new ArrayList<>();
        equippedWeapons = new ArrayList<>();
        weaponInstances = new ArrayList<>();
        this.position = 0;
        this.currentArmour = null;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setEquippedWeapons(List<Weapon> equippedWeapons) {
        this.equippedWeapons = equippedWeapons;
    }

    public void setHandCapacity(int handCapacity) {
        this.handCapacity = handCapacity;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void changeHealth(int change, Encounter currentEncounter, int teamNumber) {
        this.health += change;
        if (this.health <= 0) {
            this.health = 0;
            currentEncounter.killEntity(this, teamNumber);
        }
    }

    public int getHealth() {
        return health;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getHandCapacity() {
        return handCapacity;
    }

    @Override
    public Weapon removeWeapon(int index) {
        Weapon weaponToRemove = null;
        if (this.weapons.size() >= index) {
            weaponToRemove = this.weapons.get(index);
            this.weapons.remove(index);
            if (equippedWeapons.contains(weaponToRemove)) {
                unequipWeapon(equippedWeapons.indexOf(weaponToRemove));
            }
        }
        return weaponToRemove;
    }

    @Override
    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    @Override
    public List<Weapon> returnWeapons() {
        return this.weapons;
    }

    @Override
    public void displayWeapons() {
        for (int i = 0; i < this.weapons.size(); i++) {
            Weapon selectedWeapon = this.weapons.get(i);
            System.out.println("#"+ Integer.toString(i) +": "+ selectedWeapon.getAttribute() + " "+ selectedWeapon.getWeaponType());
        }
    }

    public List<Weapon> getEquippedWeapons() {
        return equippedWeapons;
    }

    public boolean unequipWeapon(int index) {
        if (this.weapons.size() >= index) {
            equippedWeapons.remove(index);
            return true;
        }
        return false;
    }

    public boolean equipWeapon(int weaponIndex) {
        boolean isEquipped = false;
        int handsUsed = 0;
        for (Weapon weapon : equippedWeapons) {
            handsUsed += weapon.getHandsNeeded();
        }
        if ((handsUsed + weapons.get(weaponIndex).getHandsNeeded()) <= handCapacity && !equippedWeapons.contains(weapons.get(weaponIndex))) {
            equippedWeapons.add(weapons.get(weaponIndex));
            isEquipped = true;
        }
        return isEquipped;
    }

    public int move(boolean direction, int modifier, boolean isModifierMultiplier) {
        int speed = isModifierMultiplier ? (this.getSpeed()) * modifier : (this.getSpeed() + modifier);
        int movementMagnitude = (direction) ? speed : speed * -1;
        this.setPosition(this.getPosition() + movementMagnitude);
        return movementMagnitude;
    }

    public String returnMovementStatement(int movementMagnitude, int teamNumber) {
        return (this.getTitle() +" of team "+ teamNumber +" moved "+ Math.abs(movementMagnitude) +" steps ("+ (this.getPosition()-movementMagnitude) +">"+ this.getPosition() +")");
    };

    public String returnAttackStatement(int damage, Entity target, int teamNumber) {
        if (damage != -1) {
            return (this.getTitle() +" of team "+ teamNumber +" dealt "+ damage +" damage to "+ target.getTitle() +" ("+ (target.getHealth()+damage) +">"+ target.getHealth() +")");
        } else {
            return (this.getTitle() +" of team "+ teamNumber +" missed their attack against "+ target.getTitle() +"!");
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public List<Encounter.weaponInstance> getWeaponInstances() {
        return this.weaponInstances;
    }

    public void addWeaponInstance(Encounter.weaponInstance WeaponInstance) {
        this.weaponInstances.add(WeaponInstance);
    }

    public void removeWeaponInstance(Encounter.weaponInstance weaponInstance) {
        this.weaponInstances.remove(weaponInstance);
    }

    public void clearWeaponInstances() {
        this.weaponInstances.clear();
    }

    public abstract String getTitle();

    public abstract Container getStorage();

    public void usePotion(Potion potion, Encounter encounter) {
        if (this.getStorage().getContents().contains(potion)) {
            this.getStorage().removeItem(potion);
            encounter.usePotion(potion, this);
        }
    }

    public void wearArmour(Armour armour) {
        if (getStorage().getContents().contains(armour)) {
            defence += armour.getDefence();
            System.out.println(getTitle() +" wore "+ armour.getType().toString().toLowerCase() +" armour with durability of "+ armour.getDurability());
            getStorage().removeItem(armour);
            currentArmour = armour;
        } else {
            System.out.println(getTitle() +" does not have that armour to wear in their inventory...");
        }
    }

    public void takeArmourOff() {
        if (currentArmour != null) {
            if (getStorage().getContents().size() < getStorage().getSize()) {
                getStorage().addItem(currentArmour);
                defence -= currentArmour.getDefence();
                currentArmour = null;
            } else {
                System.out.println(getTitle() +" does not have enough space in their inventory to take off their armour...");
            }
        } else {
            System.out.println(getTitle() +" is not wearing armour to take off...");
        }
    }

    public Armour getArmour() {
        return this.currentArmour;
    }

    public abstract Entity getClone(boolean keepWeaponAttributes);
}