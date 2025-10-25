package com.xylian.pokemon.domain;

import com.xylian.pokemon.entity.Entity;

public class Player{

    private String name;
    private String starter;
    private int money;


    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Starter functions
    public void writeStarter(String starter) {
        this.starter = starter;
    }

    public String getStarter() {
        return starter;
    }

    // Money functions
    public void addMoney(int amount) {
        this.money += amount;
    }

    public void spendMoney(int amount) {
        this.money -= amount;
    }

    public int getMoney() {
        return money;
    }
}
