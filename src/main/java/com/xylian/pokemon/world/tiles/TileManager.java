package com.xylian.pokemon.world.tiles;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.app.UtilityToolBox;

import  javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileIndex[][];
    String dirMapTest = "/maps/map3.csv";

    public TileManager(GamePanel gp) {
        this.gp = gp;

        int amountOfTiles = 64;


        tile = new Tile[amountOfTiles];
        mapTileIndex = new int[gp.maxWorldCol][gp.maxWorldRow];

        LoadMap();
        GetTileImages();
    }

    public void LoadMap() {
        try {
            InputStream is = getClass().getResourceAsStream(dirMapTest);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileIndex[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row ++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    public void GetTileImages() {
        BufferedImage image = null;

        final int rows = 8;
        final int cols = 8;

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                int index = (r - 1) * cols + (c - 1);
                String path = "/tiles/row-" + r + "-column-" + c + ".png";
                setUp(index, path, false);
            }
        }
        SetCollisionHardCoded();
    }

    public void setUp(int index, String imagePath, boolean collision) {
        try {
            UtilityToolBox uTool = new UtilityToolBox();
            tile[index] = new Tile();

            // Load the image; fail fast if missing
            InputStream is = Objects.requireNonNull(
                    getClass().getResourceAsStream(imagePath),
                    "Missing resource: " + imagePath
            );

            BufferedImage img = ImageIO.read(is);
            tile[index].image = uTool.scaleImage(img, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (NullPointerException e) {
            System.err.println("[TileManager] Resource not found for index " + index + " -> " + imagePath);
        } catch (IOException e) {
            System.err.println("[TileManager] Failed to read image for index " + index + " -> " + imagePath);
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol <  gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileIndex = mapTileIndex[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.playerEntity.worldX + gp.playerEntity.screenX;
            int screenY = worldY - gp.playerEntity.worldY + gp.playerEntity.screenY;

            // this prevents the camera from going out of bounds
            if(gp.playerEntity.screenX > gp.playerEntity.worldX) {
                screenX = worldX;
            }
            if(gp.playerEntity.screenY > gp.playerEntity.worldY) {
                screenY = worldY;
            }

            int rightOffset = gp.screenWidth - gp.playerEntity.screenX;
            if(rightOffset > gp.worldWidth - gp.playerEntity.worldX) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }

            int bottomOffset = gp.screenHeight - gp.playerEntity.screenY;
            if (bottomOffset > gp.worldHeight - gp.playerEntity.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            //this if statement makes sure only tiles in the viewport are drawn
            if (worldX + gp.tileSize > gp.playerEntity.worldX - gp.playerEntity.screenX &&
                    worldX - gp.tileSize < gp.playerEntity.worldX + gp.playerEntity.screenX &&
                    worldY + gp.tileSize > gp.playerEntity.worldY - gp.playerEntity.screenY &&
                    worldY - gp.tileSize < gp.playerEntity.worldY + gp.playerEntity.screenY) {
                //this code draws the tile to the screen.
                g2.drawImage(tile[tileIndex].image, screenX, screenY, null);
            } else if (gp.playerEntity.screenX > gp.playerEntity.worldX ||
                    gp.playerEntity.screenY > gp.playerEntity.worldY ||
                    rightOffset > gp.worldWidth - gp.playerEntity.worldX ||
                    bottomOffset > gp.worldHeight - gp.playerEntity.worldY) {
                g2.drawImage(tile[tileIndex].image, screenX, screenY, null);
            }
                worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow ++;

            }
        }
    }

    public void SetCollisionHardCoded() {
        tile[1].collision = true;
        tile[2].collision = true;
        tile[3].collision = true;
        tile[4].collision = true;
        tile[7].collision = true;
        tile[9].collision = true;
        tile[10].collision = true;
        tile[11].collision = true;
        tile[12].collision = true;
        tile[17].collision = true;
        tile[18].collision = true;
        tile[19].collision = true;
        tile[20].collision = true;
        tile[23].collision = true;
        tile[24].collision = true;
        tile[25].collision = true;
        tile[26].collision = true;
        tile[28].collision = true;
        tile[29].collision = true;
        tile[30].collision = true;
        tile[32].collision = true;
        tile[33].collision = true;
        tile[34].collision = true;
        tile[36].collision = true;
        tile[37].collision = true;
        tile[38].collision = true;
        tile[40].collision = true;
        tile[41].collision = true;
        tile[42].collision = true;
        tile[44].collision = true;
        tile[45].collision = true;
        tile[46].collision = true;
    }
}
