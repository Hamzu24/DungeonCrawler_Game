package com.example.textGame;

import java.util.List;

public enum EnemyTypes {
    GOBLIN(10, 0, 10, 100, 2, null),
    GOBLIN_WARRIOR(15, 1, 10, 120, 2, new Armour(ArmourTypes.CHAINMAIL)),
    GOBLIN_PRIEST(10,5,10,50,2,new Armour(ArmourTypes.LEATHER)),
    GOBLIN_BRUTE(25,0, 7, 80, 2, new Armour(ArmourTypes.LEATHER)),
    HOUND(5, 0, 20, 150, 1, null),
    SPIDER(8, 0, 15, 125, 8, null),
    WARRIOR(20,1,10,125,2,new Armour(ArmourTypes.FULL_IRON)),
    STONE_GOLEM(40,3,3,70,2,null),
    SORCERER(10,2,10,200,2, new Armour(ArmourTypes.CHAINMAIL)),
    SILVERFISH(2,0,20,200,1, new Armour(ArmourTypes.CHAINMAIL));

    int health;
    int defence;
    int speed;
    int initiative;
    int handCapacity;
    Armour armour;

    EnemyTypes(int health, int defence, int speed, int initiative, int handCapacity, Armour armour) {
        this.health = health;
        this.defence = defence;
        this.speed = speed;
        this.initiative = initiative;
        this.handCapacity = handCapacity;
        this.armour = armour;
    }
}
