package com.example.textGame;

public enum Valuables {

    COIN(1),
    COIN_HANDFUL(10),
    COIN_PILE(30),
    PENDANT(50),
    PURSE(100),
    RUBY(250),
    DIAMOND(500),
    CROWN(750),
    JEWELLERY_BOX(1000),
    SAFE(2500);


    int amount;

    Valuables(int amount) {
        this.amount = amount;
    }
}
