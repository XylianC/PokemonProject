package com.xylian.pokemon.world.objects;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.app.UtilityToolBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MasterObject {
    // Opject properties
    public BufferedImage image;
    public String name;
    public boolean collision = false;

    // World position
    public int worldX, worldY;

    // Collison shape
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    UtilityToolBox uTool = new UtilityToolBox();


    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.playerEntity.worldX + gp.playerEntity.screenX;
        int screenY = worldY - gp.playerEntity.worldY + gp.playerEntity.screenY;

        //this if statement makes sure only tiles in the viewport are drawn
        if (worldX + gp.tileSize > gp.playerEntity.worldX - gp.playerEntity.screenX &&
                worldX - gp.tileSize < gp.playerEntity.worldX + gp.playerEntity.screenX &&
                worldY + gp.tileSize > gp.playerEntity.worldY - gp.playerEntity.screenY &&
                worldY - gp.tileSize < gp.playerEntity.worldY + gp.playerEntity.screenY
        ) {
            //this code draws the tile to the screen.
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
