package com.example.textGame;

import java.util.*;
import java.util.function.Predicate;

public class Encounter {
    static Scanner inputScanner = new Scanner(System.in);

    private final List<List<Entity>> teams;
    private final int surpriseValue; //0 = team1, 1 = team2, 2 = none
    private int time;
    private boolean finished;
    private List<List<weaponInstance>> weaponInstances;
    private List<weaponInstance> readyWeaponInstances;
    private List<potionEffect> potionEffectList;
    private final potionEffect neutralPotionEffect;
    private final List<EntityAttributes> attackAttributes;

    public Encounter(List<List<Entity>> teams, int surpriseValue) {
        this.surpriseValue = surpriseValue;
        this.finished = false;
        this.teams = teams;
        this.weaponInstances = new ArrayList<>();
        this.potionEffectList = new ArrayList<>();
        this.neutralPotionEffect = new potionEffect(new Potion(null, false, 0, 0), null);
        this.attackAttributes = Arrays.asList(EntityAttributes.DAMAGE, EntityAttributes.CRIT_CHANCE, EntityAttributes.ARMOUR_PENETRATION, EntityAttributes.DEFENCE);

        setupBattle();
        startTime();
    }

    private void setupBattle() {
        weaponInstance thisWeaponInstance;
        for (int currentTeam = 0; currentTeam < 2; currentTeam++) {
            this.teams.add(teams.get(currentTeam));
            this.weaponInstances.add(new ArrayList<>()); //makes one arrayList in the weaponInstances list per team
            for (Entity character : teams.get(currentTeam)) {
                if (currentTeam == 0) {
                    character.setPosition(-1* Math.abs(character.getPosition()));
                } else {
                    character.setPosition(Math.abs(character.getPosition()));
                }
                int weaponNumber = 0;
                for (Weapon weapon : character.getEquippedWeapons()) {
                    if (weaponNumber == 0) {
                        thisWeaponInstance = new weaponInstance(weapon, character, character.getPosition(), true, surpriseValue);
                    } else {
                        thisWeaponInstance = new weaponInstance(weapon, character, character.getPosition(), false, surpriseValue);
                    }
                    this.weaponInstances.get(currentTeam).add(thisWeaponInstance);
                    character.addWeaponInstance(thisWeaponInstance);
                    weaponNumber++;
                }
                if (character.getEquippedWeapons().isEmpty()) {
                    thisWeaponInstance = new weaponInstance(null, character, character.getPosition(), true, surpriseValue);
                    this.weaponInstances.get(currentTeam).add(thisWeaponInstance);
                    character.addWeaponInstance(thisWeaponInstance);
                }
            }
        }
    }

    private void startTime() {
        time = 0;
        System.out.println("\n-----------------------------------------\nAn encounter has started between team 0:");
        displayTargets(teams.get(0));
        System.out.println("\nAnd team 1:");
        displayTargets(teams.get(1));
        printMap();
        System.out.println("\nPress o and press enter to continue...");
        inputScanner.next();
        readyWeaponInstances = new ArrayList<>();
        passTime();
    }

    private void passTime() {
        readyWeaponInstances.clear();
        int timeToPass = setReadyWeaponInstances();
        executeActions(readyWeaponInstances);

        for (int currentTeam = 0; currentTeam < 2; currentTeam++) {
            for (weaponInstance curWeaponInstance : this.weaponInstances.get(currentTeam)) {
                if (curWeaponInstance.countdown != -1) {
                    if (!readyWeaponInstances.contains(curWeaponInstance)) {
                        curWeaponInstance.decreaseCountdown(timeToPass);
                    }
                }
            }
        }

        for (potionEffect curPotionEffect : potionEffectList) {
            curPotionEffect.decreaseTimeLeft(timeToPass);
            if (curPotionEffect.getTimeLeft() == 0) {
                removePotionEffect(curPotionEffect);
            }
        }

        if (!finished) {
            printMap();
            System.out.println("-----------------------------------------------");
            inputScanner.next();
            passTime();
        }
    }

    private int setReadyWeaponInstances() {
        int timeToPass = -1;
        for (int currentTeam = 0; currentTeam < 2; currentTeam++) {
            for (weaponInstance curWeaponInstance : this.weaponInstances.get(currentTeam)) {
                //System.out.println(curWeaponInstance.entity.toString() + " ---- Countdown: "+ curWeaponInstance.countdown);
                curWeaponInstance.willExecute = true;
                if (curWeaponInstance.countdown != -1) {
                    if (readyWeaponInstances.size() == 0) {
                        readyWeaponInstances.add(curWeaponInstance);
                        timeToPass = curWeaponInstance.countdown;
                    } else if (readyWeaponInstances.get(0).countdown > curWeaponInstance.countdown) {
                        readyWeaponInstances.clear();
                        readyWeaponInstances.add(curWeaponInstance);
                        timeToPass = curWeaponInstance.countdown;
                    } else if (readyWeaponInstances.get(0).countdown == curWeaponInstance.countdown) {
                        readyWeaponInstances.add(curWeaponInstance);
                    }
                }
            }
        }
        return timeToPass;
    }

    private void executeActions(List<weaponInstance> selectedWeaponInstances) {
        Entity target;

        /*System.out.println("Start:");
        for (weaponInstance WeaponInstance : selectedWeaponInstances) {
            System.out.println(WeaponInstance.entity.toString());
        }
        System.out.println("......");
        displayTargets(teams.get(0));
        displayTargets(teams.get(1));
        System.out.println("End");*/
        for (weaponInstance curWeaponInstance : selectedWeaponInstances) {
            if (curWeaponInstance.willExecute && !finished) {
                int teamNumber = curWeaponInstance.teamNumber;
                if (curWeaponInstance.entity.getClass().getSimpleName().equals("Player") && curWeaponInstance.actionState != 2) {
                    displayPotionMenu(curWeaponInstance);
                }
                if (curWeaponInstance.actionState == 0) {
                    int attackDamage;
                    target = chooseTarget(curWeaponInstance);
                    Hashtable<EntityAttributes, potionEffect> potionsAffecting = new Hashtable<>();
                    for (EntityAttributes attribute : attackAttributes) { //DAMAGE, CRIT_CHANCE, ARMOUR_PENETRATION, DEFENCE
                        potionsAffecting.put(attribute, checkPotionEffectList(curWeaponInstance.entity, attribute));
                    }

                    attackDamage = curWeaponInstance.weapon.attack(target, curWeaponInstance.entity,this, teamNumber, potionsAffecting); //-1 means attack missed
                    System.out.println(curWeaponInstance.entity.returnAttackStatement(attackDamage, target, teamNumber));

                } else if (curWeaponInstance.actionState == 1 && curWeaponInstance.isPrimaryInstance) { //Only moves if weaponInstance is primary
                    int movementMagnitude;
                    boolean direction = teamNumber == 0;

                    potionEffect potionAffecting = checkPotionEffectList(curWeaponInstance.entity, EntityAttributes.SPEED);
                    movementMagnitude = curWeaponInstance.entity.move(direction, potionAffecting.getPotion().getMagnitude(), potionAffecting.getPotion().isMultiplier());
                    System.out.println(curWeaponInstance.entity.returnMovementStatement(movementMagnitude, teamNumber));

                    List<weaponInstance> resetWeaponInstances = checkEntityCountdowns(curWeaponInstance); //Next section of code handles entities affected by this movement
                    for (weaponInstance curWeaponInstance2 : selectedWeaponInstances) {
                        if (resetWeaponInstances.contains(curWeaponInstance2)) {
                            curWeaponInstance2.willExecute = false;
                            //System.out.println("Affected! --> "+ curWeaponInstance2.entity.toString());
                        }
                    }
                } else if (curWeaponInstance.actionState == 2) {
                    Gun gun = castWeaponToGun(curWeaponInstance.weapon);
                    gun.reload();
                    curWeaponInstance.resetCountdown(false);
                    System.out.println(curWeaponInstance.entity.getTitle() +" reloaded their "+ curWeaponInstance.weapon.getWeaponType().toString().toLowerCase());
                }
            }
            curWeaponInstance.resetCountdown(false);
        }
    }

    private void displayPotionMenu(weaponInstance curWeaponInstance) {
        StringBuilder sb = new StringBuilder();
        if (curWeaponInstance.actionState == 0) {
            sb.append("Before ").append(curWeaponInstance.entity.getTitle()).append(" attacks");
        } else if (curWeaponInstance.actionState == 1) {
            sb.append("Before ").append(curWeaponInstance.entity.getTitle()).append(" moves");
        }
        System.out.println(sb.append(", do you want to view current potion effects active or use a potion? (V/U/N)").toString());
        inputScanner.nextLine();
        String answer = inputScanner.nextLine().strip().toLowerCase();
        if (answer.equals("v")) {
            displayPotionEffects(potionEffectList);
            System.out.println("\n");
        } else if (answer.equals("u")) {
            List<Item> potions = curWeaponInstance.entity.getStorage().displayContents(Collections.singletonList("Potion"));
            System.out.println("\nWhich one do you want to use?");
            answer = inputScanner.nextLine().strip();
            curWeaponInstance.entity.usePotion((Potion) potions.get(Integer.parseInt(answer)), this);
        }
    }

    private Entity chooseTarget(weaponInstance curWeaponInstance) {
        int teamNumber = curWeaponInstance.teamNumber;
        int otherTeam = (teamNumber+1)%2;
        Entity chosenEnemy = null;
        int chosenIndex;

        if (curWeaponInstance.entity.getClass().equals("Player")) {
            System.out.println("Which of the following do you want to attack?");
            displayTargets(teams.get(otherTeam));
            chosenIndex = inputScanner.nextInt();
            inputScanner.nextLine();
            chosenEnemy = teams.get(otherTeam).get(chosenIndex);
        } else {
            if (!curWeaponInstance.weapon.getClass().getSimpleName().equals("Gun")) {
                List<Entity> nearbyEntities = getEntitiesNearby(curWeaponInstance);
                Random random = new Random();
                chosenIndex = random.nextInt(nearbyEntities.size());
                chosenEnemy = nearbyEntities.get(chosenIndex);
            } else {

                int nearestPos = Integer.MAX_VALUE;
                for (int i = 0; i < teams.get(otherTeam).size(); i++) {
                    if (Math.abs(curWeaponInstance.entity.getPosition() - teams.get(otherTeam).get(i).getPosition()) < nearestPos) {
                        chosenEnemy = teams.get(otherTeam).get(i);
                        nearestPos = Math.abs(curWeaponInstance.entity.getPosition() - teams.get(otherTeam).get(i).getPosition());
                    }
                }

            }
        }
        return chosenEnemy;
    }

    private List<weaponInstance> checkEntityCountdowns(weaponInstance curWeaponInstance) {
        List<Entity> affectedEntities = getEntitiesNearby(curWeaponInstance);
        List<weaponInstance> resetWeaponInstances = new ArrayList<>();
        List<weaponInstance> affectedWeaponInstances;

        for (Entity entity : affectedEntities) {
            affectedWeaponInstances = entity.getWeaponInstances();

            for (weaponInstance affectedWeaponInstance : affectedWeaponInstances) {
                if (affectedWeaponInstance.actionState != 2) {
                    if (affectedWeaponInstance.weapon != null) {
                        if (!affectedWeaponInstance.weapon.getClass().getSimpleName().equals("Gun")) {
                            if (curWeaponInstance.entity.getHealth() > 0) {
                                if (!(affectedWeaponInstance.actionState == 0)) {
                                    resetWeaponInstances.add(affectedWeaponInstance);
                                    affectedWeaponInstance.resetCountdown(false);
                                }
                            } else {
                                if (getEntitiesNearby(affectedWeaponInstance).size() == 0 && affectedWeaponInstance.actionState == 0) {
                                    resetWeaponInstances.add(affectedWeaponInstance);
                                    affectedWeaponInstance.resetCountdown(false);
                                }
                            }
                        }
                    } else {
                        if (curWeaponInstance.entity.getHealth() > 0) {
                            if (!(affectedWeaponInstance.actionState == 0)) {
                                resetWeaponInstances.add(affectedWeaponInstance);
                                affectedWeaponInstance.resetCountdown(false);
                            }
                        } else {
                            if (getEntitiesNearby(affectedWeaponInstance).size() == 0 && affectedWeaponInstance.actionState == 0) {
                                resetWeaponInstances.add(affectedWeaponInstance);
                                affectedWeaponInstance.resetCountdown(false);
                            }
                        }
                    }
                }
            }
        }

        return resetWeaponInstances;
    }

    private List<Entity> getEntitiesNearby(weaponInstance curWeaponInstance) {
        int teamNumber = curWeaponInstance.teamNumber;
        int curPosition = curWeaponInstance.entity.getPosition();

        List<Entity> nearbyEntities = new ArrayList<>();
        for (Entity entity : teams.get((teamNumber+1)%2)) {
            if (teamNumber == 0) {
                if (entity.getPosition() <= curPosition && entity.getHealth() > 0) {
                    nearbyEntities.add(entity);
                }
            } else {
                if (entity.getPosition() >= curPosition && entity.getHealth() > 0) {
                    nearbyEntities.add(entity);
                }
            }
        }
        return nearbyEntities;
    }

    private void displayTargets(List<Entity> targets) {
        for (int i = 0; i < targets.size(); i++) {
            System.out.print("(#"+ i +") ");
            System.out.println(targets.get(i).toString());
        }
    }

    private void displayPotionEffects(List<potionEffect> potionEffects) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < potionEffects.size(); i++) {
            System.out.println(sb.append("(#").append(i).append(") [Affects ").append(potionEffects.get(i).getTargetEntity().getTitle()).append("] ").append(potionEffects.get(i).toString()).toString());
            sb.setLength(0);
        }
    }

    public void killEntity(Entity entity, int targetTeamNumber) {
        List<weaponInstance> containedWeaponInstances = entity.getWeaponInstances();
        checkEntityCountdowns(containedWeaponInstances.get(0));
        for (int teamNumber = 0; teamNumber < 2; teamNumber++) {

            Predicate<weaponInstance> pr = curWI -> (containedWeaponInstances.contains(curWI));
            weaponInstances.get(teamNumber).removeIf(pr);
            entity.clearWeaponInstances();

            for (Entity curEntity : teams.get(teamNumber)) {
                if (curEntity.equals(entity)) {
                    teams.get(teamNumber).remove(entity);
                    break;
                }
            }
        }

        System.out.println("Entity ["+ entity.toString() + "] of team "+ targetTeamNumber +" has perished in battle!");
        entity.clearWeaponInstances();
        if (!(teams.get(0).size() >= 1 && teams.get(1).size() >= 1)) {
            int winningTeam = teams.get(0).size() >= 1 ? 0 : 1;
            endEncounter(winningTeam);
        }
    }

    public void endEncounter(int winningTeam) {
        this.finished = true;
        for (int currentTeam = 0; currentTeam < 2; currentTeam++) {
            for (Entity entity : teams.get(currentTeam)) {
                entity.setPosition(0);

                for (Weapon weapon : entity.getEquippedWeapons()) { //Reloads all guns on entities
                    if (weapon.getClass().getSimpleName().equals("Gun")) {
                        Gun gun = castWeaponToGun(weapon);
                        gun.reload();
                    }
                }
            }
        }
        System.out.println("Team "+ winningTeam +" won! The remaining entities are:");
        displayTargets(teams.get(winningTeam));
    }

    public potionEffect checkPotionEffectList(Entity entity, EntityAttributes attributeAffected) {
        for (potionEffect curPotionEffect : potionEffectList) {
            if (curPotionEffect.getTargetEntity().equals(entity) && curPotionEffect.getPotion().getAttributeAffected().equals(attributeAffected)) {
                return curPotionEffect;
            }
        }
        return this.neutralPotionEffect;
    }

    public boolean usePotion(Potion potion, Entity entity) {
        boolean added = true;
        if (potion.getAttributeAffected() != EntityAttributes.HEALTH) {
            potionEffect newPotionEffect = new potionEffect(potion, entity);
            potionEffect curPotionEffect = checkPotionEffectList(entity, potion.getAttributeAffected());
            added = true;
            if (!(curPotionEffect).equals(neutralPotionEffect)) {
                if (entity.getClass().getSimpleName().equals("Enemy")) {
                    potionEffectList.remove(curPotionEffect);
                } else {
                    System.out.println(entity.getTitle() + " already has the following potion active:\n" + curPotionEffect.toString() + "\nDo you want to replace it? (y/n)");
                    String userInput = inputScanner.nextLine().strip().toLowerCase();
                    if (userInput.equals("y")) {
                        potionEffectList.remove(curPotionEffect);
                    } else {
                        added = false;
                    }
                }
            }

            if (added) {
                System.out.println(returnPotionUseString(potion, entity));
                potionEffectList.add(newPotionEffect);
            }
        } else {
            added = true;
            int finalHealth = potion.isMultiplier() ? entity.getHealth() * potion.getMagnitude() : entity.getHealth() * potion.getMagnitude() ;
            entity.setHealth(finalHealth);
            System.out.println(returnPotionUseString(potion, entity));
        }
        return added;

    }

    public String returnPotionUseString(Potion potion, Entity entity) {
        int teamNumber = teams.get(0).contains(entity) ? 0 : 1;
        if (potion.getAttributeAffected() != EntityAttributes.HEALTH) {
            return entity.getTitle() +" of team "+ teamNumber + " used the following potion:\n"+ potion.toString();
        } else {
            return entity.getTitle() +" of team "+ teamNumber + " used the following potion:\n"+ potion.toString()+" They now have "+ entity.getHealth() +" HP";
        }
    }

    private void removePotionEffect(potionEffect potionEffect) {
        System.out.println("The following potion effect has worn off:\n"+ potionEffect.toString());
        potionEffectList.remove(potionEffect);
    }

    public void printMap() {
        StringBuilder sb1 = new StringBuilder(); //Temporary stringBuilder for each side
        StringBuilder sb2 = new StringBuilder(); //Stores the final map String
        List<ArrayList<Integer>> entityTeamNumbers = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>()));
        List<ArrayList<Entity>> entitiesBySide;
        List<ArrayList<Entity>> sortedEntityPositions = new ArrayList<>();
        List<Entity> workingEntityList = new ArrayList<>();
        int maxPosition;
        int greatestIndex = 0;
        int curPosition;

        List<Entity> entitiesWithPotions;
        entitiesWithPotions = getEntitiesWithPotions();
        entitiesBySide = sortEntitiesBySide();

        for (int sideNumber = 0; sideNumber < 2; sideNumber++) {

            sortedEntityPositions.add(new ArrayList<>()); //Following block sorts entities on each side by position and stores the teamNumber of each
            workingEntityList.clear();
            workingEntityList.addAll(entitiesBySide.get(sideNumber));
            while (workingEntityList.size() > 0) {
                maxPosition = -1;

                for (int i = 0; i < workingEntityList.size(); i++) {
                    curPosition = Math.abs(workingEntityList.get(i).getPosition());
                    if (i == 0) {
                        maxPosition = curPosition;
                        greatestIndex = 0;
                    } else if (curPosition > maxPosition) {
                        maxPosition = curPosition;
                        greatestIndex = i;
                    }
                }
                sortedEntityPositions.get(sideNumber).add(workingEntityList.get(greatestIndex));
                int teamNumber = teams.get(0).contains(workingEntityList.get(greatestIndex)) ? 0 : 1;
                entityTeamNumbers.get(sideNumber).add(teamNumber);
                workingEntityList.remove(greatestIndex);
            }

            if (sideNumber == 1) { //So the code also works for the RHS of the map the list may need to be reversed
                Collections.reverse(sortedEntityPositions.get(sideNumber));
                Collections.reverse(entityTeamNumbers.get(sideNumber));
            }

            int positionDifference;
            for (int i = 0; i < sortedEntityPositions.get(sideNumber).size(); i++) {
                Entity curEntity = sortedEntityPositions.get(sideNumber).get(i);
                sb1.append("*".repeat(Collections.frequency(entitiesWithPotions, curEntity)));
                sb1.append(curEntity.getTitle().substring(0, 1).toUpperCase()).append(entityTeamNumbers.get(sideNumber).get(i));

                if (i < sortedEntityPositions.get(sideNumber).size()-1) { //Following code block is needed to work out positionDifference
                    positionDifference = (sideNumber == 0) ? Math.abs(curEntity.getPosition()) - Math.abs(sortedEntityPositions.get(sideNumber).get(i+1).getPosition()) : Math.abs(sortedEntityPositions.get(sideNumber).get(i+1).getPosition()) - Math.abs(curEntity.getPosition());
                    //The line above just finds positionDifference. Either currentPos - nextPos for LHS, or nextPos - currentPos for RHS
                } else {
                    if (sideNumber == 0) {
                        positionDifference = Math.abs(curEntity.getPosition());
                    } else {
                        positionDifference = 0;
                    }
                }

                sb1.append(" ".repeat(positionDifference));
                sb2.append(sb1);
                sb1.setLength(0);
            }

            if (sideNumber == 0) {
                sb2.append("|");
            }
        }
        System.out.println(sb2.toString());
    }

    private List<ArrayList<Entity>> sortEntitiesBySide() {
        List<ArrayList<Entity>> entitiesBySide = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>()));
        for (int teamNumber = 0; teamNumber < 2; teamNumber++) {
            for (Entity curEntity : teams.get(teamNumber)) {
                if (curEntity.getPosition() > 0) {
                    entitiesBySide.get(1).add(curEntity);
                } else {
                    entitiesBySide.get(0).add(curEntity);
                }
            }
        }
        return entitiesBySide;
    }

    private List<Entity> getEntitiesWithPotions() {
        List<Entity> entitiesWithPotions = new ArrayList<>();
        for (potionEffect curPotionEffect : potionEffectList) {
            entitiesWithPotions.add(curPotionEffect.getTargetEntity());
        }
        return entitiesWithPotions;
    }

    public void removeWeaponInstances(Weapon weapon) { //Weapon broke
        for (int teamNumber = 0; teamNumber < 2; teamNumber++) {
            for (weaponInstance curWeaponInstance : weaponInstances.get(teamNumber)) {
                Entity curEntity = curWeaponInstance.entity;
                if (curWeaponInstance.weapon.equals(weapon)) {
                    weaponInstances.get(teamNumber).remove(curWeaponInstance);
                    curEntity.removeWeaponInstance(curWeaponInstance);
                    if (curEntity.getWeaponInstances().size() == 0) {
                        weaponInstances.get(teamNumber).add(new weaponInstance(null, curEntity, curEntity.getPosition(), true, 2));
                    }
                }
            }
        }
    }

    public Gun castWeaponToGun(Weapon weapon) {
        Gun gun = null;
        try {
            gun = (Gun) weapon; //It has to be a gun if hasAmmo == false
        } catch (ClassCastException e) {
            System.out.println("Error in castWeaponTogGun in class Encounter, weapon isn't a gun but should be");
        }
        return gun;
    }

    public class weaponInstance {
        private final Weapon weapon;
        private int countdown;
        private int countdownTemplate;
        private int actionState; //0 = attack, 1 = move, 2 = reload
        private int initiative;
        private Entity entity;
        private final boolean isPrimaryInstance;
        private boolean willExecute;
        private final int teamNumber;
        private final boolean hasSurpriseAdvantage;

        public weaponInstance(Weapon weapon, Entity entity, int startingPos, boolean isPrimaryInstance, int surpriseValue) {
            this.entity = entity;
            this.entity.setPosition(startingPos);
            this.weapon = weapon;
            this.countdownTemplate = entity.getInitiative();
            this.initiative = entity.getInitiative();
            this.entity = entity;
            this.isPrimaryInstance = isPrimaryInstance;
            this.teamNumber = teams.get(0).contains(this.entity) ? 0 : 1;
            this.hasSurpriseAdvantage = (surpriseValue == teamNumber);
            resetCountdown(true);
        }

        public void resetCountdown(boolean isFirstReset) {
            if (this.weapon != null) {
                if (this.weapon.hasAmmo()) {
                    if (this.weapon.getClass().getSimpleName().equals("Gun") || getEntitiesNearby(this).size() > 0) {
                        this.countdownTemplate = Math.round(weapon.getAttackSpeed() / (this.initiative / 100));
                        if (isFirstReset && this.hasSurpriseAdvantage) {
                            this.countdown = 0;
                        } else {
                            this.countdown = this.countdownTemplate;
                        }
                        this.actionState = 0;
                    } else {
                        this.countdownTemplate = Math.round(10 / (this.initiative / 100));
                        if (isFirstReset && this.hasSurpriseAdvantage) {
                            this.countdown = 0;
                        } else {
                            this.countdown = this.countdownTemplate;
                        }
                        this.actionState = 1;
                    }
                } else {
                    Gun gun = castWeaponToGun(this.weapon);
                    this.countdownTemplate = gun.getReloadSpeed();
                    this.countdown = countdownTemplate;
                    this.actionState = 2;
                }
            } else {
                if (getEntitiesNearby(this).size() == 0) {
                    this.countdownTemplate = Math.round(10 / (this.initiative / 100));
                    if (isFirstReset && this.hasSurpriseAdvantage) {
                        this.countdown = 0;
                    } else {
                        this.countdown = this.countdownTemplate;
                    }
                    this.actionState = 1;
                } else {
                    this.countdown = -1;
                    this.actionState = 0;
                }
            }

        }

        public void decreaseCountdown(int decrease) {
            this.countdown -= decrease;
        }
    }

    public class potionEffect {
        private final Potion potion;
        private int timeLeft;
        private final Entity targetEntity;

        public potionEffect(Potion potion, Entity entity) {
            this.potion = potion;
            this.timeLeft = potion.getDuration();
            this.targetEntity = entity;
        }

        public Potion getPotion() {
            return potion;
        }

        public int getTimeLeft() {
            return timeLeft;
        }

        public void decreaseTimeLeft(int decrease) {
            this.timeLeft -= decrease;
            timeLeft = Math.max(timeLeft, 0);
        }

        public Entity getTargetEntity() {
            return targetEntity;
        }

        @Override
        public String toString() {
            return this.potion.toString() +". Wears off in "+ this.timeLeft +" minutes";
        }
    }

}