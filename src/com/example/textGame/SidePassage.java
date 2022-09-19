package com.example.textGame;

public class SidePassage implements IObject {
    ToolTypes toolNeeded;
    SceneTemplates sceneTemplate;

    public SidePassage(ToolTypes toolNeeded, SceneTemplates sceneTemplate) {
        this.toolNeeded = toolNeeded;
        this.sceneTemplate = sceneTemplate;
    }

    @Override
    public String getTitle() {
        return "A side passage";
    }

    @Override
    public ToolTypes getToolNeeded() {
        return toolNeeded;
    }

    @Override
    public IObject getClone() {
        return new SidePassage(toolNeeded, sceneTemplate);
    }
}
