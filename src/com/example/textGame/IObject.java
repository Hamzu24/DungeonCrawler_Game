package com.example.textGame;

public interface IObject {
    String getTitle();
    ToolTypes getToolNeeded();
    IObject getClone();
}
