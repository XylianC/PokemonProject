package com.xylian.pokemon.world.objects;

import com.xylian.pokemon.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MasterObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.playerEntity.worldX + gp.playerEntity.screenX;
        int screenY = worldY - gp.playerEntity.worldY + gp.playerEntity.screenY;

        //this if statement makes sure only tiles in the viewport are drawn
        if (worldX + gp.tileSize > gp.playerEntity.worldX - gp.playerEntity.screenX &&
                worldX - gp.tileSize < gp.playerEntity.worldX + gp.playerEntity.worldX &&
                worldY + gp.tileSize > gp.playerEntity.worldY - gp.playerEntity.screenY &&
                worldY - gp.tileSize < gp.playerEntity.worldY + gp.playerEntity.screenY
        ) {
            //this code draws the tile to the screen.
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
