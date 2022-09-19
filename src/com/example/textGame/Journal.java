package com.example.textGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Journal extends Item {
    List<Runes> runes;
    HashMap<String, Integer> statistics;

    public Journal() {
        runes = new ArrayList<>();
        statistics = new HashMap<>(Map.of("Areas Explored", 0, "Enemies Killed", 0, "Durability Used", 0, "Damage Dealt", 0, "Damage Taken", 0, "Potions Used", 0, "Food Eaten", 0, "Environments Traversed", 0, "Runes Used", 0));
    }

    public Journal(List<Runes> runes, HashMap<String, Integer> statistics) { //Used when cloning a journal
        this.runes = new ArrayList<>();
        this.statistics = new HashMap<>();

        for (Runes rune : runes) {
            this.runes.add(rune);
        }

        for (String statistic : statistics.keySet()) {
            this.statistics.put(statistic, statistics.get(statistic));
        }
    }

    public void UpdateStatistic(String statisticName, int increase) {
        statistics.put(statisticName, statistics.get(statisticName) + increase);
    }

    public boolean addRune(Runes rune) { //returns if rune is already present or not
        if (!runes.contains(rune)) {
            runes.add(rune);
            return true;
        }
        return false;
    }

    public Runes getRune(Runes rune) { //Returns null if rune is not present
        if (runes.contains(rune)) {
            return runes.get(runes.indexOf(rune));
        }
        return null;
    }

    public String getTitle() {
        return "Your journal";
    }

    public Item getClone() {
        return new Journal(runes, statistics);
    }
}
