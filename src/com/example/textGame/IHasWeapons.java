package com.example.textGame;

import java.util.ArrayList;
import java.util.List;

public interface IHasWeapons {
    List<Weapon> weapons = new ArrayList<>();

    void addWeapon(Weapon weapon);
    Weapon removeWeapon(int index);
    List<Weapon> returnWeapons();
    void displayWeapons();
}
