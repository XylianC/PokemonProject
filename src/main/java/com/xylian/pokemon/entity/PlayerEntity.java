package com.xylian.pokemon.entity;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.app.InputSystem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerEntity extends Entity {
    GamePanel gp;
    InputSystem inputSystem;

    public PlayerEntity(GamePanel gp, InputSystem inputSystem) {
        this.gp = gp;
        this.inputSystem = inputSystem;

        SetDefaultValues();
        GetPlayerImage();
    }

    public void SetDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        doPlayerMovement();
        spriteCounter++;
        if(spriteCounter > 10) {
            if(spriteIndex == 1) {
                spriteIndex = 2;
            } else if (spriteIndex == 2) {
                spriteIndex = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteIndex == 1) {
                    image = up1;
                }
                if(spriteIndex == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteIndex == 1) {
                    image = down1;
                }
                if(spriteIndex == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteIndex == 1) {
                    image = left1;
                }
                if(spriteIndex == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteIndex == 1) {
                    image = right1;
                }
                if(spriteIndex == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void doPlayerMovement() {
        if(inputSystem.upPressed) {
            direction = "up";
            y -= speed;
        } else if (inputSystem.downPressed) {
            direction = "down";
            y += speed;
        } else if (inputSystem.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (inputSystem.rightPressed) {
            direction = "right";
            x += speed;
        }
    }

    public void GetPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/spr_player_right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
