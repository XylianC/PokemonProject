package com.xylian.pokemon.entity;

import com.xylian.pokemon.app.GamePanel;

import java.util.Random;

public class NPC_MonsterTest extends Entity {
    GamePanel gp;

    public NPC_MonsterTest(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 2;

        getNPCImages();
        setDialogue();

        isMonster = true;
    }

    public void getNPCImages() {
        String directory = "/monster/spr_monster1_";

        up1 = setUp(directory,"1");
        up2 = setUp(directory,"2");
        down1 = setUp(directory,"3");
        down2 = setUp(directory,"4");
        left1 = setUp(directory,"5");
        left2 = setUp(directory,"6");
        right1 = setUp(directory,"7");
        right2 = setUp(directory,"8");
    }

    public void setDialogue() {

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


