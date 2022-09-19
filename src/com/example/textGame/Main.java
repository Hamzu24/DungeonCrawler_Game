package com.example.textGame;
import java.util.*;

public class Main {
    private static Scanner inputScanner = new Scanner(System.in);
    private static Player user;
    public static Environments currentEnvironment;
    public static int environmentLength;
    public static int sceneNumber;
    public static List<Integer> previousDifficulties;
    public static Random random;


    public static void main(String[] args) {
        System.out.println("Type your name and press enter to begin... ");
        String name = inputScanner.nextLine();
        user = new Player(name);
        user.setPosition(30);
        random = new Random();
        previousDifficulties = new ArrayList<>();
        //introduction();
        //startTestEncounter();
        user.increaseExperience(25);
        user.getExperienceLevel();
        sceneNumber = 1;
        environmentLength = 10;
    }

    private static void introduction() {
        System.out.println("Welcome, "+ user.getTitle() +", to the unnamed text adventure game!");
        System.out.println("You can choose one of the following weapons: A dagger (d), or a slingshot (s)");
        String userAnswer = inputScanner.nextLine();
        user.addWeapon(userAnswer.equals("d") ? new MeleeWeapon(MeleeWeapons.DAGGER) : new Gun(Guns.SLINGSHOT));
        user.equipWeapon(0);
    }

    public static void manageInventory() {
        System.out.println("Do you want to manage your weapons (w) or items (i)?");
        String userAnswer = inputScanner.nextLine();
        if (userAnswer.equals("w")) {
            user.displayWeapons();
        } else if (userAnswer.equals("i")) {
            user.getStorage().displayContents(new ArrayList<>(Collections.singletonList("All")));
        } else {
            System.out.println("That is not a valid input...");
        }
    }

    private static Encounter startEncounter(ArrayList<Entity> team1, ArrayList<Entity> team2, int surpriseValue) {
        List<List<Entity>> teams = new ArrayList<>(Arrays.asList(team1, team2));
        return new Encounter(teams, surpriseValue);
    }

    private static void startTestEncounter() {
        user.getStorage().addItem(new Potion(EntityAttributes.DAMAGE, true, 3, 15));
        Armour newArmour = new Armour(ArmourTypes.FULL_IRON);
        user.getStorage().addItem(newArmour);
        user.wearArmour(newArmour);
        Enemy enemy1 = new Enemy(EnemyTypes.GOBLIN);
        enemy1.addWeapon(new Gun(Guns.SLINGSHOT));
        enemy1.equipWeapon(0);
        enemy1.setPosition(40);
        Enemy enemy2 = new Enemy(EnemyTypes.GOBLIN);
        enemy2.addWeapon(new MeleeWeapon(MeleeWeapons.DAGGER));
        enemy2.addWeapon(new MeleeWeapon(MeleeWeapons.DAGGER));
        enemy2.equipWeapon(0);
        enemy2.equipWeapon(1);
        enemy2.setPosition(10);
        Enemy enemy3 = new Enemy(EnemyTypes.GOBLIN);
        enemy3.addWeapon(new MeleeWeapon(MeleeWeapons.DAGGER));
        enemy3.equipWeapon(0);
        enemy3.setPosition(5);
        Enemy enemy4 = new Enemy(EnemyTypes.GOBLIN);
        enemy4.addWeapon(new MeleeWeapon(MeleeWeapons.SWORD));
        enemy4.equipWeapon(0);
        enemy4.setPosition(5);
        Enemy enemy5 = new Enemy(EnemyTypes.GOBLIN);
        enemy5.addWeapon(new MeleeWeapon(MeleeWeapons.SWORD));
        enemy5.equipWeapon(0);
        enemy5.setPosition(5);
        Enemy enemy6 = new Enemy(EnemyTypes.GOBLIN);
        enemy6.addWeapon(new MeleeWeapon(MeleeWeapons.SWORD));
        enemy6.equipWeapon(0);
        enemy6.setPosition(5);
        Enemy enemy7 = new Enemy(EnemyTypes.GOBLIN);
        enemy7.addWeapon(new MeleeWeapon(MeleeWeapons.SWORD));
        enemy7.equipWeapon(0);
        enemy7.setPosition(5);
        startEncounter(new ArrayList<>(Arrays.asList(user, enemy1, enemy2, enemy3)), new ArrayList<>(Arrays.asList(enemy4, enemy5, enemy6, enemy7)), 0);
    }

    private static Scene chooseNextScene() {
        int mean = averageDifficulty() - (averageDifficulty()-user.getExperienceLevel());
        int standardDistribution = (int) Math.round(1.5*(environmentLength/Math.sqrt(sceneNumber)));
        int targetDifficulty;
        do {
            targetDifficulty = (int) Math.round((random.nextGaussian() * standardDistribution) + mean);
        } while (targetDifficulty <= 0); //Maybe add constraint to max difficulty

        SceneTemplates chosenTemplate = null;
        List<SceneTemplates> orderedList = SceneTemplates.Constants.templatesOrderedByDifficulty.get(currentEnvironment);
        for (int i = 0; i <= orderedList.size(); i++) {
            if (i == 0) {
                if (targetDifficulty < orderedList.get(i).difficultyModifier) {
                    chosenTemplate = orderedList.get(i);
                }
            } else if (i == orderedList.size()) {
                chosenTemplate = orderedList.get(i-1);
            } else if (targetDifficulty > orderedList.get(i-1).difficultyModifier && targetDifficulty < orderedList.get(i).difficultyModifier) {
                if (Math.abs(targetDifficulty - orderedList.get(i-1).difficultyModifier) < Math.abs(targetDifficulty - orderedList.get(i).difficultyModifier)) {
                    chosenTemplate = orderedList.get(i-1);
                } else {
                    chosenTemplate = orderedList.get(i);
                }
            }
        }

        return new Scene(chosenTemplate, user);
    }

    private static int averageDifficulty() {
        if (previousDifficulties.size() > 0) {
            int total = 0;
            for (int difficulty : previousDifficulties) {
                total += difficulty;
            }
            return (total / previousDifficulties.size());
        } else {
            return user.getExperienceLevel();
        }
    }

    private static void enterNewRoom() {
        Scene scene = chooseNextScene();
    }

}
