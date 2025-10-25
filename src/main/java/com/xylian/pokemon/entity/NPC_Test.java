package com.xylian.pokemon.entity;

import com.xylian.pokemon.app.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Test extends Entity {

    public NPC_Test(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 2;

        getNPCImages();
        setDialogue();
    }

    public void getNPCImages() {
        up1 = setUp("up1");
        up2 = setUp("up2");
        down1 = setUp("down1");
        down2 = setUp("down2");
        left1 = setUp("left1");
        left2 = setUp("left2");
        right1 = setUp("right1");
        right2 = setUp("right2");
    }

    public void setDialogue() {
        dialogues[0] = "Hello, I am NPC!";
        dialogues[1] = "Welcome to the world of Pokemon!\nIt is ever so nice to meet you, how are you today?";

    }

    // AI
    public void setAction() {
        actionLoopCounter++;

        // First pick a random number between 1 and 100;
        if (actionLoopCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLoopCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}
