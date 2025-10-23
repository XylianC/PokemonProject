package com.xylian.pokemon.entity;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.app.InputSystem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerEntity extends Entity {
    GamePanel gp;
    InputSystem inputSystem;

    //to see where in the screen the entity should be rendered;
    public final int screenX;
    public final int screenY;

    // Player variables
    public int amountOfKeys = 0;
    public boolean hasWaterBoots = false;

    public PlayerEntity(GamePanel gp, InputSystem inputSystem) {
        this.gp = gp;
        this.inputSystem = inputSystem;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;

        SetDefaultValues();
        GetPlayerImage();
    }

    public void SetDefaultValues() {
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 20;
        speed = 4;
    }

    public void update() {
        GetPlayerDirection();

        boolean anyPressed = inputSystem.upPressed || inputSystem.downPressed || inputSystem.leftPressed || inputSystem.rightPressed;

        if (anyPressed) {
            // collision code
            collision = false;

            gp.cChecker.CheckTile(this);
            int objectIndex = gp.cChecker.CheckObject(this, true);
            interactWithObject(objectIndex);

            if (!collision) {
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

            doPlayerAnimation();
        } else {
            spriteIndex = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        SetPlayerImages(g2);
    }

    public void GetPlayerDirection() {
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

    public void GetPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/spr_player_right2.png")));

        } catch (IOException e) {
            System.err.println("No images were found");
        }
    }

    public void SetPlayerImages(Graphics2D g2) {
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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

    public void interactWithObject(int index) {
        // an object cant have 999 as index, so this means no object is touched
        if (index != 999) {
            String objectName = gp.obj[index].name;

            switch (objectName) {
                case "Key": //pickup script for keys
                    amountOfKeys++;
                    gp.obj[index] = null;
                    //gp.playSoundEffect(1);
                    break;
                case "Door": //script to open a door
                    if (amountOfKeys > 0) {
                        amountOfKeys--;
                        gp.obj[index] = null;
                        //gp.playSoundEffect(1);
                    }
                    break;
                case "WaterBoots": //script as example of a power-up item
                    hasWaterBoots = true;
                    speed += 2;
                    gp.obj[index] = null;

            }
        }
    }
}