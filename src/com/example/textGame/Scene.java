package com.example.textGame;

import java.util.*;

public class Scene {
    private List<IObject> objects;
    private List<List<Entity>> entities; //0 = friendly, 1 = enemy
    private SceneTemplates template;
    private int difficultyModifier;
    private Environments environment;
    private static final Random random = new Random();

    public Scene(SceneTemplates template, Player player) {
        this.template = template;
        this.difficultyModifier = template.difficultyModifier;
        this.environment = template.environment;

        for (List<IObject> curObjectOptions : template.objects) {
            IObject curObject = curObjectOptions.get(random.nextInt(curObjectOptions.size()));
            if (curObject != null) {
                this.objects.add(curObject.getClone());
            }
        }

        entities.add(new ArrayList<>());
        if (this.template.friendlyEntities != null) {
            int friendlyEntityNumb = 0;
            Random random = new Random();
            for (Entity entity : template.friendlyEntities.keySet()) {
                friendlyEntityNumb = random.nextInt(template.friendlyEntities.get(entity).get(0) + 1 - template.friendlyEntities.get(entity).get(1)) + template.friendlyEntities.get(entity).get(1);
                for (int i = 0; i < friendlyEntityNumb; i++) {
                    entities.get(0).add(entity.getClone(false));
                }
            }
            entities.get(0).add(player);
        }

        entities.add(new ArrayList<>());
        if (this.template.enemyEntities != null) {
            int enemyEntityNumb = 0;
            for (Entity entity : template.enemyEntities.keySet()) {
                enemyEntityNumb = random.nextInt(template.enemyEntities.get(entity).get(0) + 1 - template.enemyEntities.get(entity).get(1)) + template.enemyEntities.get(entity).get(1);
                for (int i = 0; i < enemyEntityNumb; i++) {
                    entities.get(0).add(entity.getClone(false));
                }
            }
        }
    }

    public List<IObject> getObjects() {
        return objects;
    }

    public void addObject(IObject object) {
        this.objects.add(object);
    }

    public void addObjects(List<IObject> objects) {
        this.objects.addAll(objects);
    }

    public List<List<Entity>> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity, int teamNumber) {
        this.entities.get(teamNumber).add(entity);
    }

    public void addEntities(List<Entity> entities, int teamNumber) {
        this.entities.get(teamNumber).addAll(entities);
    }

    public SceneTemplates getTemplate() {
        return template;
    }

    public int getDifficultyModifier() {
        return difficultyModifier;
    }

    public Environments getEnvironment() {
        return environment;
    }
}
