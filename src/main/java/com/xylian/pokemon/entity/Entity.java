package com.xylian.pokemon.entity;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.app.UtilityToolBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    GamePanel gp;

    public Entity(GamePanel gp) {
        this.gp = gp;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    // Declaration of position and movement variables
    public int worldX, worldY;
    public int speed;
    public boolean canMove = true;

    // Declaration of entity image variables
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteIndex = 1;
    public boolean isMonster = false;
    public boolean isIdle = false;

    // Declaration of entity collision variables
    public Rectangle solidArea = new Rectangle(12,24,20,20);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;

    // Declaration of entity AI variables
    public int actionLoopCounter = 0;

    // Declaratin of entity dialogue variables
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;


    public void setAction() {

    }

    public void update() {
        if(canMove && !isMonster) {
            setAction();
        }

        collision = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        if(!isMonster) {
            if (!collision) {
                if (canMove) {
                    doMovement();
                    playAnimation();
                }
            } else {
                spriteIndex = 1;
                spriteCounter = 0;
            }
        }

        if(isMonster) {
            playIdleAnimation();
        }
    }

    public void doMovement() {
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

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        lookAtPlayer();
    }

    public void lookAtPlayer() {
        switch (gp.playerEntity.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void playAnimation() {
        isIdle = false;
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

    public void playIdleAnimation() {
        isIdle = true;
        spriteCounter++;
        if (spriteCounter > 5) {
            spriteIndex++;

            if (spriteIndex > 7) {
                spriteIndex = 1;
            }
            spriteCounter = 0;
        }

    }

    public BufferedImage setUp(String directory, String imageName) {
        UtilityToolBox uTool = new UtilityToolBox();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(directory + imageName + ".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = worldX - gp.playerEntity.worldX + gp.playerEntity.screenX;
        int screenY = worldY - gp.playerEntity.worldY + gp.playerEntity.screenY;

        //this if statement makes sure only tiles in the viewport are drawn
        if (worldX + gp.tileSize > gp.playerEntity.worldX - gp.playerEntity.screenX &&
                worldX - gp.tileSize < gp.playerEntity.worldX + gp.playerEntity.screenX &&
                worldY + gp.tileSize > gp.playerEntity.worldY - gp.playerEntity.screenY &&
                worldY - gp.tileSize < gp.playerEntity.worldY + gp.playerEntity.screenY
        ) {
            if(!isIdle) {
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
            }
            if (isIdle) {
                if (spriteIndex == 1) {
                    image = up1;
                }
                if (spriteIndex == 2) {
                    image = up2;
                }
                if (spriteIndex == 3) {
                    image = down1;
                }
                if (spriteIndex == 4) {
                    image = down2;
                }
                if (spriteIndex == 5) {
                    image = left1;
                }
                if (spriteIndex == 6) {
                    image = left2;
                }
                if (spriteIndex == 7) {
                    image = right1;
                }
                if (spriteIndex == 8) {
                    image = right2;
                }
            }


            //this code draws the tile to the screen.
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
