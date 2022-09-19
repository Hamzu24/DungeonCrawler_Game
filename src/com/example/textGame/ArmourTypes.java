package com.example.textGame;

public enum ArmourTypes {
    LEATHER(1, 50),
    ORNATE(5, 15),
    CHAINMAIL(2, 500),
    FULL_IRON(3, 200);

    int defence;
    int durability;

    ArmourTypes(int defence, int durability) {
        this.defence = defence;
        this.durability = durability;
    }
}
