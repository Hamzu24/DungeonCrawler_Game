package com.example.textGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum ChestTypes {
    WOODEN(Arrays.asList(Arrays.asList(MeleeWeapons.DAGGER, Guns.SLINGSHOT), Arrays.asList(MeleeWeapons.POLE, MeleeWeapons.NUNCHUKS, MeleeWeapons.SWORD, MeleeWeapons.LONGSWORD), Arrays.asList(Guns.SPEAR_THROWER, Guns.BOW, null, null), Arrays.asList(MeleeWeapons.FLAIL, MeleeWeapons.LONGSWORD, null, null, null, null, null)),
        Arrays.asList(Arrays.asList(FoodTypes.Constants.RAW_APPLE, FoodTypes.Constants.RAW_GRAPE, FoodTypes.Constants.RAW_PEAR), Arrays.asList(FoodTypes.Constants.RAW_APPLE, FoodTypes.Constants.RAW_GRAPE, FoodTypes.Constants.RAW_PEAR), Arrays.asList(FoodTypes.Constants.RAW_CHICKEN, FoodTypes.Constants.RAW_PORKCHOP, FoodTypes.Constants.RAW_BREAD, null, null),
        Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.COMMON), new Tool(ToolTypes.CHISEL, ToolRarities.COMMON), new Tool(ToolTypes.SAW, ToolRarities.COMMON), new Tool(ToolTypes.NAIL_GUN, ToolRarities.COMMON), null, null, null, null, null),
        Arrays.asList(STDPotions.getSTDPotion(STDPotions.S_HEALTH), STDPotions.getSTDPotion(STDPotions.M_HEALTH), null, null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.S_S_DAMAGE), STDPotions.getSTDPotion(STDPotions.S_M_DAMAGE), STDPotions.getSTDPotion(STDPotions.S_S_SPEED), STDPotions.getSTDPotion(STDPotions.S_M_SPEED), STDPotions.getSTDPotion(STDPotions.S_S_DEFENCE), STDPotions.getSTDPotion(STDPotions.S_M_DEFENCE), null, null, null, null, null, null))),

    BRONZE(Arrays.asList(Arrays.asList(MeleeWeapons.NUNCHUKS, MeleeWeapons.SWORD), Arrays.asList(MeleeWeapons.FLAIL, MeleeWeapons.LONGSWORD, null, null), Arrays.asList(Guns.SPEAR_THROWER, Guns.BOW), Arrays.asList(Guns.FIREBALL_WAND, Guns.SIX_SHOOTER, null, null), Arrays.asList(Guns.PISTOL, MeleeWeapons.GREAT_AXE, null, null, null, null, null, null, null)),
        Arrays.asList(Arrays.asList(FoodTypes.Constants.RAW_APPLE, FoodTypes.Constants.RAW_PEAR, FoodTypes.Constants.RAW_CHICKEN), Arrays.asList(FoodTypes.Constants.RAW_CHICKEN, FoodTypes.Constants.RAW_PORKCHOP), Arrays.asList(FoodTypes.Constants.RAW_BREAD),
        Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.UNCOMMON), new Tool(ToolTypes.CHISEL, ToolRarities.UNCOMMON), new Tool(ToolTypes.SAW, ToolRarities.UNCOMMON), new Tool(ToolTypes.NAIL_GUN, ToolRarities.UNCOMMON), null, null, null, null, null),
        Arrays.asList(STDPotions.getSTDPotion(STDPotions.M_HEALTH), null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.S_M_DAMAGE), STDPotions.getSTDPotion(STDPotions.S_M_SPEED), STDPotions.getSTDPotion(STDPotions.S_M_DEFENCE), STDPotions.getSTDPotion(STDPotions.S_S_ARMOUR_PENETRATION), STDPotions.getSTDPotion(STDPotions.S_M_ARMOUR_PENETRATION), null, null))),

    SILVER(Arrays.asList(Arrays.asList(MeleeWeapons.FLAIL, MeleeWeapons.LONGSWORD), Arrays.asList(MeleeWeapons.HATCHET, MeleeWeapons.GREAT_AXE, null), Arrays.asList(Guns.SIX_SHOOTER, Guns.FIREBALL_WAND, Guns.SIX_SHOOTER), Arrays.asList(Guns.PISTOL, Guns.MAGNUM, null, null), Arrays.asList(Guns.MACHINE_PISTOL, MeleeWeapons.HATCHET, null, null, null, null, null, null, null)),
        Arrays.asList(Arrays.asList(FoodTypes.Constants.RAW_APPLE, FoodTypes.Constants.RAW_BREAD), Arrays.asList(FoodTypes.Constants.RAW_APPLE, FoodTypes.Constants.RAW_BREAD), Arrays.asList(FoodTypes.Constants.RAW_BEEF, FoodTypes.Constants.RAW_PORKCHOP), Arrays.asList(FoodTypes.Constants.COOKED_PEAR, FoodTypes.Constants.RAW_CAKE, null, null, null),
        Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.UNCOMMON), new Tool(ToolTypes.CHISEL, ToolRarities.UNCOMMON), new Tool(ToolTypes.SAW, ToolRarities.UNCOMMON), new Tool(ToolTypes.NAIL_GUN, ToolRarities.UNCOMMON), null, null),
        Arrays.asList(STDPotions.getSTDPotion(STDPotions.M_HEALTH), STDPotions.getSTDPotion(STDPotions.L_HEALTH), null, null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.S_M_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_S_DAMAGE), STDPotions.getSTDPotion(STDPotions.S_M_SPEED), STDPotions.getSTDPotion(STDPotions.L_S_SPEED), STDPotions.getSTDPotion(STDPotions.S_M_DEFENCE), STDPotions.getSTDPotion(STDPotions.L_S_DEFENCE), STDPotions.getSTDPotion(STDPotions.S_M_ARMOUR_PENETRATION), null, null, null, null, null, null))),

    GOLD(Arrays.asList(Arrays.asList(MeleeWeapons.HATCHET, MeleeWeapons.GREAT_AXE), Arrays.asList(MeleeWeapons.SAMURAI_SWORD, MeleeWeapons.MACHETE, null, null), Arrays.asList(Guns.PISTOL, Guns.MAGNUM), Arrays.asList(Guns.BROKEN_SNIPER, Guns.MACHINE_PISTOL, null, null), Arrays.asList(Guns.MAC_10, MeleeWeapons.GAUNTLET, null, null, null, null, null, null, null)),
        Arrays.asList(Arrays.asList(FoodTypes.Constants.RAW_BREAD), Arrays.asList(FoodTypes.Constants.RAW_CAKE, FoodTypes.Constants.RAW_BEEF), Arrays.asList(FoodTypes.Constants.COOKED_BREAD, FoodTypes.Constants.COOKED_CHICKEN, null),
        Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.RARE), new Tool(ToolTypes.CHISEL, ToolRarities.RARE), new Tool(ToolTypes.SAW, ToolRarities.RARE), new Tool(ToolTypes.NAIL_GUN, ToolRarities.RARE), null, null, null, null, null), Arrays.asList(new Tool(ToolTypes.SCREWDRIVER, ToolRarities.COMMON), null, null),
        Arrays.asList(STDPotions.getSTDPotion(STDPotions.L_HEALTH), null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.L_S_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_S_SPEED), STDPotions.getSTDPotion(STDPotions.L_S_DEFENCE), STDPotions.getSTDPotion(STDPotions.S_M_ARMOUR_PENETRATION), STDPotions.getSTDPotion(STDPotions.L_S_ARMOUR_PENETRATION), null, null, null))),

    DIAMOND(Arrays.asList(Arrays.asList(MeleeWeapons.SAMURAI_SWORD, MeleeWeapons.MACHETE), Arrays.asList(MeleeWeapons.GAUNTLET, MeleeWeapons.SLEDGEHAMMER, null, null), Arrays.asList(Guns.MACHINE_PISTOL, Guns.BROKEN_SNIPER), Arrays.asList(Guns.MAC_10, null, null), Arrays.asList(Guns.OTS, MeleeWeapons.FIRE_GAUNTLET, MeleeWeapons.PIERCING_GAUNTLET, null, null, null, null, null, null, null, null, null, null, null)),
        Arrays.asList(Arrays.asList(FoodTypes.Constants.RAW_BEEF), Arrays.asList(FoodTypes.Constants.RAW_CAKE, FoodTypes.Constants.COOKED_CAKE), Arrays.asList(FoodTypes.Constants.COOKED_APPLE, FoodTypes.Constants.COOKED_PEAR),
        Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.RARE), new Tool(ToolTypes.CHISEL, ToolRarities.RARE), new Tool(ToolTypes.SAW, ToolRarities.RARE), new Tool(ToolTypes.NAIL_GUN, ToolRarities.RARE), null, null), Arrays.asList(new Tool(ToolTypes.SCREWDRIVER, ToolRarities.UNCOMMON), null, null),
        Arrays.asList(STDPotions.getSTDPotion(STDPotions.L_HEALTH), STDPotions.getSTDPotion(STDPotions.G_HEALTH), null, null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.L_S_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_M_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_S_SPEED), STDPotions.getSTDPotion(STDPotions.L_M_SPEED), STDPotions.getSTDPotion(STDPotions.L_S_DEFENCE), STDPotions.getSTDPotion(STDPotions.L_M_DEFENCE), STDPotions.getSTDPotion(STDPotions.L_S_ARMOUR_PENETRATION), null, null, null, null, null, null))),

    CRYSTAL(Arrays.asList(Arrays.asList(MeleeWeapons.GAUNTLET, MeleeWeapons.SLEDGEHAMMER), Arrays.asList(MeleeWeapons.FIRE_GAUNTLET, MeleeWeapons.PIERCING_GAUNTLET, null, null), Arrays.asList(Guns.MAC_10), Arrays.asList(Guns.OTS, Guns.MINI_CANNON, null, null), Arrays.asList(Guns.LIGHT_AR, MeleeWeapons.WOLVERINE_CLAWS, MeleeWeapons.WARHAMMER, null, null, null, null, null, null, null, null, null, null, null)),
            Arrays.asList(Arrays.asList(FoodTypes.Constants.RAW_BEEF, FoodTypes.Constants.COOKED_PORKCHOP), Arrays.asList(FoodTypes.Constants.RAW_CAKE, FoodTypes.Constants.COOKED_APPLE), Arrays.asList(FoodTypes.Constants.COOKED_PORKCHOP),
            Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.EPIC), new Tool(ToolTypes.CHISEL, ToolRarities.EPIC), new Tool(ToolTypes.SAW, ToolRarities.EPIC), new Tool(ToolTypes.NAIL_GUN, ToolRarities.EPIC), null, null, null, null, null), Arrays.asList(new Tool(ToolTypes.SCREWDRIVER, ToolRarities.RARE), null),
            Arrays.asList(STDPotions.getSTDPotion(STDPotions.G_HEALTH), null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.L_M_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_M_SPEED), STDPotions.getSTDPotion(STDPotions.L_M_DEFENCE), STDPotions.getSTDPotion(STDPotions.L_M_ARMOUR_PENETRATION), null, null, null))),

    LEGENDARY(Arrays.asList(Arrays.asList(MeleeWeapons.FIRE_GAUNTLET, MeleeWeapons.PIERCING_GAUNTLET), Arrays.asList(MeleeWeapons.WOLVERINE_CLAWS, MeleeWeapons.WARHAMMER, null, null), Arrays.asList(Guns.OTS, Guns.MINI_CANNON), Arrays.asList(Guns.LIGHT_AR, Guns.SPAZ_SHOTGUN, null, null), Arrays.asList(Guns.MINI_MG, MeleeWeapons.ANCIENT_BLADE, MeleeWeapons.FLESHPOUNDER, null, null, null, null, null, null, null, null, null, null, null)),
            Arrays.asList(Arrays.asList(FoodTypes.Constants.RAW_BEEF, FoodTypes.Constants.COOKED_PORKCHOP), Arrays.asList(FoodTypes.Constants.RAW_CAKE, FoodTypes.Constants.COOKED_CAKE), Arrays.asList(FoodTypes.Constants.COOKED_PORKCHOP, FoodTypes.Constants.COOKED_PORKCHOP),
            Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.EPIC), new Tool(ToolTypes.CHISEL, ToolRarities.EPIC), new Tool(ToolTypes.SAW, ToolRarities.EPIC), new Tool(ToolTypes.NAIL_GUN, ToolRarities.EPIC), null, null), Arrays.asList(new Tool(ToolTypes.SCREWDRIVER, ToolRarities.EPIC), null, null),
            Arrays.asList(STDPotions.getSTDPotion(STDPotions.G_HEALTH)), Arrays.asList(STDPotions.getSTDPotion(STDPotions.L_M_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_L_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_M_SPEED), STDPotions.getSTDPotion(STDPotions.L_L_SPEED), STDPotions.getSTDPotion(STDPotions.L_M_DEFENCE), STDPotions.getSTDPotion(STDPotions.L_L_DEFENCE), STDPotions.getSTDPotion(STDPotions.L_M_ARMOUR_PENETRATION), null, null, null, null, null, null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.COCAINE), STDPotions.getSTDPotion(STDPotions.SPEED), null, null))),

    GODLY(Arrays.asList(Arrays.asList(MeleeWeapons.WOLVERINE_CLAWS, MeleeWeapons.WARHAMMER), Arrays.asList(MeleeWeapons.ANCIENT_BLADE, MeleeWeapons.FLESHPOUNDER, null, null), Arrays.asList(Guns.SPAZ_SHOTGUN, Guns.MINI_MG, Guns.WIDOW_MAKER), Arrays.asList(Guns.AMAX, Guns.CHAINSMOKER, null, null), Arrays.asList(Guns.DRAGONS_BREATH, MeleeWeapons.DRAGONS_TALON, null, null, null, null, null, null, null, null, null, null, null, null)),
            Arrays.asList(Arrays.asList(FoodTypes.Constants.COOKED_CAKE), Arrays.asList(FoodTypes.Constants.COOKED_BEEF), Arrays.asList(FoodTypes.Constants.RAW_CAKE, FoodTypes.Constants.COOKED_PORKCHOP, FoodTypes.Constants.COOKED_BEEF),
            Arrays.asList(new Tool(ToolTypes.HAMMER, ToolRarities.LEGENDARY), new Tool(ToolTypes.CHISEL, ToolRarities.LEGENDARY), new Tool(ToolTypes.SAW, ToolRarities.LEGENDARY), new Tool(ToolTypes.NAIL_GUN, ToolRarities.LEGENDARY), null, null), Arrays.asList(new Tool(ToolTypes.SCREWDRIVER, ToolRarities.LEGENDARY), null),
            Arrays.asList(STDPotions.getSTDPotion(STDPotions.G_HEALTH)), Arrays.asList(STDPotions.getSTDPotion(STDPotions.L_L_DAMAGE), STDPotions.getSTDPotion(STDPotions.L_L_SPEED), STDPotions.getSTDPotion(STDPotions.L_L_DEFENCE), STDPotions.getSTDPotion(STDPotions.L_L_ARMOUR_PENETRATION), null, null), Arrays.asList(STDPotions.getSTDPotion(STDPotions.SPEED), STDPotions.getSTDPotion(STDPotions.AMPHETAMINE), null, null)));

    private List<List<IWeaponType>> weaponOptions;
    private List<List<Item>> itemOptions;

    ChestTypes(List<List<IWeaponType>> weaponOptions, List<List<Item>> itemOptions) {
        this.weaponOptions = weaponOptions;
        this.itemOptions = itemOptions;
    }

    public static Chest spawnLootBox(ChestTypes chestType, ToolTypes toolNeeded) {
        Random random = new Random();

        List<Weapon> weaponsInChest = new ArrayList<>();
        IWeaponType weaponType;
        for (List<IWeaponType> curWeaponOptions : chestType.weaponOptions) {
            weaponType = curWeaponOptions.get(random.nextInt(curWeaponOptions.size()));
            if (weaponType != null) {
                if (weaponType.getClass().equals("MeleeWeapons")) {
                    weaponsInChest.add(new MeleeWeapon((MeleeWeapons) weaponType, Attributes.chooseAttribute()));
                } else if (weaponType.getClass().equals("Guns")) {
                    weaponsInChest.add(new Gun((Guns) weaponType, Attributes.chooseAttribute()));
                }
            }
        }

        List<Item> itemsInChest = new ArrayList<>();
        Item item;
        for (List<Item> curItemOptions : chestType.itemOptions) {
            item = curItemOptions.get(random.nextInt(curItemOptions.size()));
            if (item != null) {
                itemsInChest.add(item.getClone());
            }
        }

        return new Chest(itemsInChest, weaponsInChest, chestType.toString(), toolNeeded, true);
    }
}
