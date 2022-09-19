package com.example.textGame;

import java.util.*;

public enum SceneTemplates {

    EMPTY_ROOM(null,
            null,
            null,
            0, Environments.GOBLIN_TUNNELS),
    GOBLIN_DUNGEON(new HashMap<>(Map.of(Constants.STD_STONE_GOLEM, Arrays.asList(0, 2))),
            new HashMap<>(Map.of(Constants.STD_GOBLIN, Arrays.asList(3, 6), Constants.STD_GOBLIN_WARRIOR, Arrays.asList(1, 3), Constants.STD_GOBLIN_BRUTE, Arrays.asList(1, 2), Constants.STD_GOBLIN_PRIEST, Arrays.asList(0, 1))),
            Arrays.asList(Arrays.asList(new ChestTemplate(ChestTypes.GOLD, null), new ChestTemplate(ChestTypes.SILVER, null)), Arrays.asList(new ChestTemplate(ChestTypes.GOLD, ToolTypes.HAMMER), new ChestTemplate(ChestTypes.DIAMOND, ToolTypes.HAMMER)), Arrays.asList(new Goblet(3, FlameTypes.LAVA, null), new Goblet(2, FlameTypes.FIRE, null), new Goblet(1, FlameTypes.HELL, ToolTypes.SAW))), //NOT FINISHED
            25, Environments.GOBLIN_TUNNELS),
    UNDERGROUND_PALACE(new HashMap<>(),
            new HashMap<>(Map.of(Constants.STD_GOBLIN_PRIEST, Arrays.asList(5, 6), new Enemy(EnemyTypes.GOBLIN, Arrays.asList(new Gun(Guns.SIX_SHOOTER), new MeleeWeapon(MeleeWeapons.FLAIL)), ArmourTypes.ORNATE), Arrays.asList(1, 3))),
            Arrays.asList(Arrays.asList(new ChestTemplate(ChestTypes.DIAMOND, null), new Goblet(4, FlameTypes.LIGHT, null))),
            35, Environments.GOBLIN_TUNNELS),
    GOBLIN_TUNNEL1(new HashMap<>(),
            new HashMap<>(Map.of(Constants.STD_GOBLIN, Arrays.asList(3, 6), Constants.STD_GOBLIN_BRUTE, Arrays.asList(0, 2))),
            Arrays.asList(),
            10, Environments.GOBLIN_TUNNELS),
    GOBLIN_TUNNEL2(new HashMap<>(),
            new HashMap<>(Map.of(Constants.STD_GOBLIN, Arrays.asList(1, 3), Constants.STD_GOBLIN_WARRIOR, Arrays.asList(0, 1), Constants.STD_HOUND, Arrays.asList(2, 4))),
            Arrays.asList(),
            18, Environments.GOBLIN_TUNNELS),
    GOBLIN_TUNNEL3(new HashMap<>(),
            new HashMap<>(Map.of(Constants.STD_SPIDER, Arrays.asList(1, 3), Constants.STD_SILVERFISH, Arrays.asList(1, 4))),
            Arrays.asList(),
            5, Environments.GOBLIN_TUNNELS),
    STONE_CAVERN(new HashMap<>(),
            new HashMap<>(Map.of(Constants.STD_STONE_GOLEM, Arrays.asList(2, 3), Constants.STD_SILVERFISH, Arrays.asList(1, 5))),
            Arrays.asList(),
            25, Environments.GOBLIN_TUNNELS),
    GOBLIN_BARRACKS(new HashMap<>(),
            new HashMap<>(Map.of(Constants.STD_GOBLIN_WARRIOR, Arrays.asList(1, 2), new Enemy(EnemyTypes.GOBLIN_WARRIOR, Arrays.asList(new MeleeWeapon(MeleeWeapons.HATCHET), new MeleeWeapon(MeleeWeapons.FLAIL)), ArmourTypes.CHAINMAIL), Arrays.asList(1, 2), new Enemy(EnemyTypes.GOBLIN_BRUTE, Arrays.asList(new Gun(Guns.SIX_SHOOTER), new Gun(Guns.SPEAR_THROWER))), Arrays.asList(1, 2))),
            Arrays.asList(),
            40, Environments.GOBLIN_TUNNELS),
    GOBLIN_CAMP(new HashMap<>(),
            new HashMap<>(Map.of(new Enemy(EnemyTypes.GOBLIN, Collections.emptyList(), null, 40), Arrays.asList(2, 8), Constants.STD_HOUND, Arrays.asList(0, 3))),
            Arrays.asList(),
            2, Environments.GOBLIN_TUNNELS),
    HOME_TOWN(new HashMap<>(),
            new HashMap<>(),
            Arrays.asList(Arrays.asList(Constants.STD_MERCHANT)),
            2, Environments.NEUTRAL);



    HashMap<Entity, List<Integer>> friendlyEntities;
    HashMap<Entity, List<Integer>> enemyEntities;
    List<List<IObject>> objects;
    int difficultyModifier;
    Environments environment;


    SceneTemplates(HashMap<Entity, List<Integer>> friendlyEntities, HashMap<Entity, List<Integer>> enemyEntities, List<List<IObject>> objects, int difficultyModifier, Environments environment) {
        this.friendlyEntities = friendlyEntities;
        this.enemyEntities = enemyEntities;
        this.objects = objects;
        this.difficultyModifier = difficultyModifier;
        this.environment = environment;
        if (Constants.templatesByEnvironment.get(environment) != null) {
            Constants.templatesByEnvironment.get(environment).add(this);
        } else {
            Constants.templatesByEnvironment.put(environment, Arrays.asList(this));
        }
    }

    private static List<SceneTemplates> orderByDifficulties(Environments environment) {
        List<SceneTemplates> workingList = new ArrayList<>();
        List<SceneTemplates> finalList = new ArrayList<>();
        int greatestIndex;
        int greatestDifficulty = 0;

        workingList.addAll(Constants.templatesByEnvironment.get(environment));
        while (workingList.size() > 0) {
            greatestIndex = 0;
            for (int i = 0; i < workingList.size(); i++) {
                SceneTemplates curTemplate = workingList.get(i);
                if (i == 0) {
                    greatestDifficulty = curTemplate.difficultyModifier;
                } else if (curTemplate.difficultyModifier > greatestDifficulty) {
                    greatestDifficulty = curTemplate.difficultyModifier;
                    greatestIndex = i;
                }
            }

            finalList.add(workingList.get(greatestIndex));
            workingList.remove(greatestIndex);
        }

        return finalList;
    }

    public static class Constants {
        private final static Entity STD_GOBLIN = new Enemy(EnemyTypes.GOBLIN, Arrays.asList(new MeleeWeapon(MeleeWeapons.DAGGER)));
        private final static Entity STD_GOBLIN_WARRIOR = new Enemy(EnemyTypes.GOBLIN_WARRIOR, Arrays.asList(new MeleeWeapon(MeleeWeapons.SWORD)));
        private final static Entity STD_GOBLIN_BRUTE = new Enemy(EnemyTypes.GOBLIN_BRUTE, Arrays.asList(new MeleeWeapon(MeleeWeapons.LONGSWORD)));
        private final static Entity STD_GOBLIN_PRIEST = new Enemy(EnemyTypes.GOBLIN_PRIEST, Arrays.asList(new Gun(Guns.FIREBALL_WAND)));
        private final static Entity STD_HOUND = new Enemy(EnemyTypes.HOUND, Arrays.asList(new MeleeWeapon(MeleeWeapons.WEAK_CLAWS)));
        private final static Entity STD_STONE_GOLEM = new Enemy(EnemyTypes.STONE_GOLEM, Arrays.asList(new MeleeWeapon(MeleeWeapons.STONE_FIST)));
        private final static Entity STD_SPIDER = new Enemy(EnemyTypes.SPIDER, Arrays.asList(new MeleeWeapon(MeleeWeapons.WEAK_CLAWS), new MeleeWeapon(MeleeWeapons.WEAK_CLAWS), new MeleeWeapon(MeleeWeapons.WEAK_CLAWS), new MeleeWeapon(MeleeWeapons.WEAK_CLAWS), new MeleeWeapon(MeleeWeapons.WEAK_CLAWS), new MeleeWeapon(MeleeWeapons.WEAK_CLAWS), new MeleeWeapon(MeleeWeapons.WEAK_CLAWS), new MeleeWeapon(MeleeWeapons.WEAK_CLAWS)));
        private final static Entity STD_SILVERFISH = new Enemy(EnemyTypes.SILVERFISH, Arrays.asList(new MeleeWeapon(MeleeWeapons.SMALL_TEETH)));
        private final static IObject STD_MERCHANT = new Merchant(new HashMap<>(Map.of(FoodTypes.Constants.RAW_BREAD, 5)), "friendly", null);

        public final static HashMap<Environments, List<SceneTemplates>> templatesByEnvironment = new HashMap<>();
        public final static HashMap<Environments, List<SceneTemplates>> templatesOrderedByDifficulty = new HashMap<>(Map.of(Environments.GOBLIN_TUNNELS, orderByDifficulties(Environments.GOBLIN_TUNNELS)));
    }
}
