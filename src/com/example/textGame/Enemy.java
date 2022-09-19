package com.example.textGame;

import javax.swing.text.Position;
import java.util.List;

public class Enemy extends Entity {
    public EnemyTypes type;
    public int attackSpeedModifier;
    public Container storage;

    public EnemyTypes getType() {
        return type;
    }

    public Enemy(EnemyTypes type) {
        super(type.health, type.defence, type.speed, type.initiative, type.handCapacity);
        this.type = type;
        this.storage = new Container(10);
        if (type.armour != null) {
            wearArmour(new Armour(type.armour.getType()));
        }
    }

    public Enemy(EnemyTypes type, List<Weapon> weapons) { //Used to override the default armour type & weapons
        super(type.health, type.defence, type.speed, type.initiative, type.handCapacity);
        this.type = type;
        this.storage = new Container(10);

        for (int i = 0; i < weapons.size(); i++) {
            super.addWeapon(weapons.get(i));
            super.equipWeapon(i);
        }
    }

    public Enemy(EnemyTypes type, List<Weapon> weapons, ArmourTypes armourType) { //Used to override the default armour type & weapons
        super(type.health, type.defence, type.speed, type.initiative, type.handCapacity);
        if (armourType != null) {
            this.wearArmour(new Armour(armourType));
        }
        this.type = type;
        this.storage = new Container(10);

        for (int i = 0; i < weapons.size(); i++) {
            super.addWeapon(weapons.get(i));
            super.equipWeapon(i);
        }
    }

    public Enemy(EnemyTypes type, List<Weapon> weapons, ArmourTypes armourType, int position) { //Used to override the default armour type & weapons
        super(type.health, type.defence, type.speed, type.initiative, type.handCapacity);
        if (armourType != null) {
            this.wearArmour(new Armour(armourType));
        }
        this.type = type;
        this.storage = new Container(10);

        for (int i = 0; i < weapons.size(); i++) {
            super.addWeapon(weapons.get(i));
            super.equipWeapon(i);
        }
    }

    public Enemy(EnemyTypes type, List<Weapon> weapons, int position) { //Used to override the position & def
        super(type.health, type.defence, type.speed, type.initiative, type.handCapacity);
        this.type = type;
        this.storage = new Container(10);
        for (int i = 0; i < weapons.size(); i++) {
            super.addWeapon(weapons.get(i));
            super.equipWeapon(i);
        }

        this.setPosition(position);
    }

    @Override
    public String toString() {
        String finalString = "|"+ this.type +"| Health: "+ Integer.toString(super.getHealth());
        if (super.getEquippedWeapons().size() > 1) {
            finalString = finalString + ". Weapons: ";
        } else if (super.getEquippedWeapons().size() == 1) {
            finalString = finalString + ". Weapon: ";
        } else {
            finalString = finalString + ". No equipped weapons";
        }

        int weaponNumber = 0;
        for (Weapon weapon : super.getEquippedWeapons()) {
            if (weaponNumber != 0) {
                finalString = finalString + ", ";
            }
            finalString = finalString + weapon.getWeaponType();
            weaponNumber++;
        }

        finalString = finalString + ". Offset: "+ super.getPosition();
        return finalString;
    }

    @Override
    public void addWeapon(Weapon weapon) {
        super.addWeapon(weapon);
    }

    @Override
    public String getTitle() {
        return this.type.toString().toLowerCase();
    }

    @Override
    public Container getStorage() {
        return this.storage;
    }

    @Override
    public Entity getClone(boolean keepWeaponAttributes) {
        Enemy newEnemy = new Enemy(this.type);
        List<Weapon> previousWeapons = super.returnWeapons();

        for (int i = 0; i < previousWeapons.size(); i++) {
            Weapon weapon = previousWeapons.get(i);
            Weapon newWeapon = keepWeaponAttributes ? weapon.getClone(weapon.getAttribute()) : weapon.getClone(Attributes.chooseAttribute()) ;
            newEnemy.addWeapon(newWeapon);
            if (super.getEquippedWeapons().contains(weapon)) {
                newEnemy.equipWeapon(i);
            }
        }

        newEnemy.setPosition(super.getPosition());
        newEnemy.wearArmour(super.getArmour().getClone());
        return newEnemy;
    }
}
