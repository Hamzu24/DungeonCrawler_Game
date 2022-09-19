package com.example.textGame;

public class Food extends Item {
    FoodTypes foodType;
    boolean isCooked;
    int nourishment;

    public Food(FoodTypes foodType, boolean isCooked) {
        super();
        this.foodType = foodType;
        this.isCooked = isCooked;
        this.nourishment = foodType.nourishment.get(isCooked ? 1 : 0);
    }

    @Override
    public String getTitle() {
        String isCookedString = (isCooked) ? "cooked" : "raw";
        return "A "+ isCookedString +" "+ foodType.toString();
    }

    @Override
    public Item getClone() {
        return new Food(foodType, isCooked);
    }
}
