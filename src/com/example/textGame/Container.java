package com.example.textGame;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Item> contents;
    private int size;

    public Container(int size) {
        this.contents = new ArrayList<>();
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public boolean addItem(Item item) {
        if (this.contents.size() < this.getSize()) {
            this.contents.add(item);
            return true;
        }
        return false;
    }

    public void removeItem(int index) {
        if (this.contents.size() >= index) {
            this.contents.remove(index);
        }
    }

    public void removeItem(Item item) {
        if (this.contents.contains(item)) {
            this.contents.remove(item);
        }
    }

    public List<Item> getContents() {
        return this.contents;
    }

    public List<Item> displayContents(List<String> classNames) {
        int index = 0;
        List<Item> returnList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Item item : contents) {
            if (classNames.contains(item.getClass().getSimpleName()) || classNames.contains("All")) {
                System.out.println(sb.append("#").append(index).append(": ").append(item.getTitle()).toString());
                returnList.add(item);
                index++;
                sb.setLength(0);
            }
        }
        return returnList;
    }

    public List<Item> returnItems(List<String> classNames) {
        List<Item> returnList = new ArrayList<>();
        for (Item item : contents) {
            if (classNames.contains(item.getClass().getSimpleName())) {
                returnList.add(item);
            }
        }
        return returnList;
    }

}
