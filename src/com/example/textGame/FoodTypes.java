package com.example.textGame;

import java.util.Arrays;
import java.util.List;

public enum FoodTypes {
    APPLE(Arrays.asList(10, 18), 0),
    GRAPE(Arrays.asList(1, 1), 0),
    PEAR(Arrays.asList(6, 12), 0),
    CHICKEN(Arrays.asList(15, 25), 70),
    BEEF(Arrays.asList(30, 40), 40),
    PORKCHOP(Arrays.asList(25, 30), 40),
    CAKE(Arrays.asList(0, 75), 100),
    BREAD(Arrays.asList(20, 25), 0);

    List<Integer> nourishment;
    int poisonPercentage;

    FoodTypes(List<Integer> nourishment, int poisonPercentage) {
        this.nourishment = nourishment;
        this.poisonPercentage = poisonPercentage;
    }

    public static final class Constants {
        static final Item RAW_APPLE = new Food(APPLE, false);
        static final Item RAW_GRAPE = new Food(GRAPE, false);
        static final Item RAW_PEAR = new Food(PEAR, false);
        static final Item RAW_CHICKEN = new Food(CHICKEN, false);
        static final Item RAW_BEEF = new Food(BEEF, false);
        static final Item RAW_PORKCHOP = new Food(PORKCHOP, false);
        static final Item RAW_CAKE = new Food(CAKE, false);
        static final Item RAW_BREAD = new Food(BREAD, false);

        static final Item COOKED_APPLE = new Food(APPLE, true);
        static final Item COOKED_GRAPE = new Food(GRAPE, true);
        static final Item COOKED_PEAR = new Food(PEAR, true);
        static final Item COOKED_CHICKEN = new Food(CHICKEN, true);
        static final Item COOKED_BEEF = new Food(BEEF, true);
        static final Item COOKED_PORKCHOP = new Food(PORKCHOP, true);
        static final Item COOKED_CAKE = new Food(CAKE, true);
        static final Item COOKED_BREAD = new Food(BREAD, true);
    }
}
