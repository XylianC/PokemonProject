package com.xylian.pokemon.entity;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.app.InputSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerEntity extends Entity {
    InputSystem inputSystem;

    //to see where in the screen the entity should be rendered;
    public final int screenX;
    public final int screenY;

    // Player variables
    public boolean hasWaterBoots = false;


    public PlayerEntity(GamePanel gp, InputSystem inputSystem) {
        super(gp);
        this.inputSystem = inputSystem;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();

        solidArea.x = 12;
        solidArea.y = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 20;
        speed = 4;
    }

    public void update() {

        getPlayerDirection();

        boolean anyPressed = inputSystem.upPressed || inputSystem.downPressed || inputSystem.leftPressed || inputSystem.rightPressed;

        if (anyPressed) {
            // collision code
            collision = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objectIndex = gp.cChecker.checkObject(this, true);
            interactWithObject(objectIndex);

            // check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactWithNPC(npcIndex);

            if (!collision) {
                doPlayerMovement();
                doPlayerAnimation();
            }

        } else {
            spriteIndex = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        setPlayerImages(g2);
    }

    public void getPlayerDirection() {
        if (inputSystem.upPressed) {
            direction = "up";
        } else if (inputSystem.downPressed) {
            direction = "down";
        } else if (inputSystem.leftPressed) {
            direction = "left";
        } else if (inputSystem.rightPressed) {
            direction = "right";
        }
    }

    public void getPlayerImage() {
        up1 = setUp("up1");
        up2 = setUp("up2");
        down1 = setUp("down1");
        down2 = setUp("down2");
        left1 = setUp("left1");
        left2 = setUp("left2");
        right1 = setUp("right1");
        right2 = setUp("right2");
    }

    public void setPlayerImages(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteIndex == 1) {
                    image = up1;
                }
                if (spriteIndex == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteIndex == 1) {
                    image = down1;
                }
                if (spriteIndex == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteIndex == 1) {
                    image = left1;
                }
                if (spriteIndex == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteIndex == 1) {
                    image = right1;
                }
                if (spriteIndex == 2) {
                    image = right2;
                }
                break;
        }

        int x = screenX;
        int y = screenY;

        if(screenX > worldX) {
            x = worldX;
        }
        if(screenY > worldY) {
            y = worldY;
        }

        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }

        int bottomOffset = gp.screenHeight - screenY;
        if (bottomOffset > gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }

        g2.drawImage(image, x, y, null);
    }

    public void doPlayerAnimation() {
        if (inputSystem.upPressed || inputSystem.downPressed || inputSystem.leftPressed || inputSystem.rightPressed) {
            spriteCounter++;
            if (spriteCounter > 15) { //every X frames it switches between sprite frames.
                if (spriteIndex == 1) {
                    spriteIndex = 2;
                } else if (spriteIndex == 2) {
                    spriteIndex = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void doPlayerMovement() {
        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }
    }

    public void interactWithObject(int index) {
        // an object or npc MAY NOT have 999 as index
        // so this means no object is touched
        if (index != 999) {
            // Do nothing for now
        }
    }

    public void interactWithNPC(int index) {
        if(index != 999) {
            gp.npc[index].canMove = false;

            if (gp.input.interactionPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
        }
        gp.input.interactionPressed = false;
    }
}