package com.example.textGame;

import java.util.ArrayList;
import java.util.List;

public class Chest extends Container implements IHasWeapons, IObject { //A chest is a container that can hold weapons
    protected List<Weapon> weapons;
    private int weaponSlots;
    private Container storage;
    private final ToolTypes toolNeeded;
    private final boolean isLootBox;
    private String appearance; //TO BE MADE

    public Chest(int size, List<Weapon> weapons, int weaponSlots, String appearance, ToolTypes toolNeeded) {
        super(size);
        this.weapons = weapons;
        this.weaponSlots = weaponSlots;
        this.appearance = appearance;
        this.toolNeeded = toolNeeded;
        this.isLootBox = false;
    }

    public Chest(List<Item> items, List<Weapon> weapons, String appearance, ToolTypes toolNeeded, boolean isLootBox) { //used for chests spawned from sceneTemplates
        super(items.size());
        this.weapons = weapons;
        this.weaponSlots = weapons.size();
        this.appearance = appearance;
        this.storage.getContents().addAll(items);
        this.toolNeeded = toolNeeded;
        this.isLootBox = true;
    }

    public Chest() {
        super(10);
        this.weapons = null;
        this.weaponSlots = 5;
        this.appearance = "UNDEFINED";
        this.toolNeeded = null;
        this.isLootBox = false;
    }

    @Override
    public void addWeapon(Weapon weapon) {
        if (this.weapons.size() < this.weaponSlots) {
            this.weapons.add(weapon);
        }
    }

    @Override
    public Weapon removeWeapon(int index) {
        Weapon weaponToRemove = null;
        if (this.weapons.size() >= index) {
            this.weapons.remove(index);
            weaponToRemove = this.weapons.get(index);
        }
        return weaponToRemove;
    }

    @Override
    public List<Weapon> returnWeapons() {
        return this.weapons;
    }

    @Override
    public void displayWeapons() {
        for (int i = 0; i < this.weapons.size(); i++) {
            Weapon selectedWeapon = this.weapons.get(i);
            System.out.println("#" + i + ": " + selectedWeapon.getAttribute() + " " + selectedWeapon.getWeaponType());
        }
    }

    @Override
    public String getTitle() {
        StringBuilder sb = new StringBuilder();
        return sb.append("A ").append(appearance).append(" chest with ").append(getContents().size()).append(" items inside and ").append(weapons).append(" weapons").toString();
    }

    @Override
    public IObject getClone() {
        List<Weapon> weapons = new ArrayList<>();
        for (Weapon weapon : weapons) {
            weapons.add(weapon.getClone(null));
        }

        List<Item> items = new ArrayList<>();
        for (Item item : getContents()) {
            items.add(item.getClone());
        }

        return new Chest(items, weapons, this.appearance, this.toolNeeded, false);
    }

    @Override
    public ToolTypes getToolNeeded() {
        return this.toolNeeded;
    }

    public boolean isLootBox() {
        return isLootBox;
    }

    public String getAppearance() {
        return appearance;
    }
}
