package com.example.textGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ToolTypes {
    HAMMER(new HashMap<>(Map.of(ToolRarities.COMMON, 2, ToolRarities.UNCOMMON, 5,ToolRarities.RARE, 10,ToolRarities.EPIC, 25,ToolRarities.LEGENDARY, 60))), //first found in wooden, 2
    CHISEL(new HashMap<>(Map.of(ToolRarities.COMMON, 2, ToolRarities.UNCOMMON, 5,ToolRarities.RARE, 10,ToolRarities.EPIC, 25,ToolRarities.LEGENDARY, 60))), //first found in wooden
    NAIL_GUN(new HashMap<>(Map.of(ToolRarities.COMMON, 5, ToolRarities.UNCOMMON, 10,ToolRarities.RARE, 20,ToolRarities.EPIC, 50,ToolRarities.LEGENDARY, 160))),
    SAW(new HashMap<>(Map.of(ToolRarities.COMMON, 2, ToolRarities.UNCOMMON, 5,ToolRarities.RARE, 10,ToolRarities.EPIC, 25,ToolRarities.LEGENDARY, 60))), //1
    SCREWDRIVER(new HashMap<>(Map.of(ToolRarities.COMMON, 2, ToolRarities.UNCOMMON, 5,ToolRarities.RARE, 10,ToolRarities.EPIC, 25,ToolRarities.LEGENDARY, 60)));

    HashMap<ToolRarities, Integer> durabilityList;

    ToolTypes(HashMap<ToolRarities, Integer> durabilityList) {
        this.durabilityList = durabilityList;
    }
}
