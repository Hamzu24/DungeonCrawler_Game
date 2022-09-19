package com.example.textGame;

public class MeleeWeapon extends Weapon {

    public MeleeWeapon(MeleeWeapons type) {
        super(type, Attributes.chooseAttribute());
    }

    public MeleeWeapon(MeleeWeapons type, Attributes attribute) {
        super(type, attribute);
    }

    @Override
    public Weapon getClone(Attributes attribute) { //if attribute = null it's random
        MeleeWeapons meleeWeaponType = (MeleeWeapons) super.getWeaponType();
        if (attribute == null) {
            return new MeleeWeapon(meleeWeaponType, Attributes.chooseAttribute());
        } else {
            return new MeleeWeapon(meleeWeaponType, attribute);
        }
    }
}
