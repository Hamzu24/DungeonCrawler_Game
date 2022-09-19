package com.example.textGame;

import java.util.List;

public class Player extends Entity {
    private String name;
    private Backpack backpack;
    private int experienceLevel;

    public Player(String name) {
        super(100, 0, 10, 100, 2);
        this.backpack = new Backpack(10);
        this.name = name;
        this.experienceLevel = 0;
    }

    public Player(String name, Backpack backpack) { //Used to preset a backpack for the player
        super(100, 0, 10, 100, 2);
        this.backpack = backpack;
        this.name = name;
        this.experienceLevel = 0;
    }

    @Override
    public String getTitle() {
        return this.name;
    }

    @Override
    public void addWeapon(Weapon weapon) {
        super.addWeapon(weapon);
        System.out.println(weapon.getAttribute() +" "+ weapon.getWeaponType().toString().toLowerCase() +" has been added to your inventory!");
    }

    public class Backpack extends Container {
        public Backpack(int size) {
            super(size);
            super.getContents().add(new Journal());
        }

        public void increaseSize(int sizeIncrease) {
            super.setSize(super.getSize() + sizeIncrease);
        }

        public Backpack getClone() {
            Backpack newBackpack = new Backpack(super.getSize());
            for (Item item : super.getContents()) {
                newBackpack.addItem(item.getClone());
            }
            return newBackpack;
        }
    }

    @Override
    public String toString() {
        String finalString = "Player "+ this.name +" Health: "+ super.getHealth();
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
    public Container getStorage() {
        return backpack;
    }

    @Override
    public Entity getClone(boolean keepWeaponAttributes) {
        Player newPlayer = new Player(this.name, backpack.getClone());
        newPlayer.setHealth(super.getHealth());
        newPlayer.setSpeed(super.getSpeed());
        newPlayer.setDefence(super.getDefence());
        newPlayer.setInitiative(super.getInitiative());
        newPlayer.setHandCapacity(super.getHandCapacity());
        List<Weapon> previousWeapons = super.returnWeapons();

        for (int i = 0; i < previousWeapons.size(); i++) {
            Weapon weapon = previousWeapons.get(i);
            Weapon newWeapon = keepWeaponAttributes ? weapon.getClone(weapon.getAttribute()) : weapon.getClone(Attributes.chooseAttribute()) ;
            newPlayer.addWeapon(newWeapon);
            if (super.getEquippedWeapons().contains(weapon)) {
                newPlayer.equipWeapon(i);
            }
        }

        newPlayer.setPosition(super.getPosition());
        newPlayer.wearArmour(super.getArmour().getClone());
        return newPlayer;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void increaseExperience(int increase) {
        this.experienceLevel += increase;
    }
}
