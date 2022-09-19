package com.example.textGame;

import java.util.Hashtable;

public class Gun extends Weapon {
    private int reloadSpeed;
    private int clipSize;
    private int accuracy;
    private int ammoInClip;
    private String ammoType;

    public Gun(Guns type) {
        super(type, Attributes.chooseAttribute());
        this.clipSize = type.clipSize;
        this.ammoInClip = type.clipSize;
        this.accuracy = type.accuracy;
        this.reloadSpeed = type.reloadSpeed;
        this.ammoType = type.ammoType;
    }

    public Gun(Guns type, Attributes attribute) {
        super(type, attribute);
        this.clipSize = type.clipSize;
        this.ammoInClip = type.clipSize;
        this.accuracy = type.accuracy;
        this.reloadSpeed = type.reloadSpeed;
    }

    @Override
    public int attack(Entity target, Entity user, Encounter curEncounter, int teamNumber, Hashtable<EntityAttributes, Encounter.potionEffect> potionsAffecting) {
        this.ammoInClip -= 1;
        int randomValue = (int) Math.round(Math.random()*100);
        if (randomValue <= this.accuracy) {
            return -1; //-1 means attack missed
        } else {
            return super.attack(target, user, curEncounter, teamNumber, potionsAffecting);
        }
    }

    public int getReloadSpeed() {
        return reloadSpeed;
    }

    public int getClipSize() {
        return clipSize;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void reload() {
        this.ammoInClip = this.clipSize;
    }

    public int getAmmoInClip() {
        return ammoInClip;
    }

    @Override
    public boolean hasAmmo() {
        return (ammoInClip != 0);
    }

    @Override
    public Weapon getClone(Attributes attribute) { //if attribute = null it's random
        Guns gunType = (Guns) super.getWeaponType();
        if (attribute == null) {
            return new Gun(gunType, Attributes.chooseAttribute());
        } else {
            return new Gun(gunType, attribute);
        }
    }
}
