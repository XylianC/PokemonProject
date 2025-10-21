package com.xylian.pokemon.world.tiles;

import com.xylian.pokemon.app.GamePanel;

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

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[16];
        mapTileIndex = new int[gp.maxWorldCol][gp.maxWorldRow];

        LoadMap();
        GetTileImage();
    }

    public void LoadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map2.csv");

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

    public void GetTileImage() {
        BufferedImage image = null;
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_grass_1.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_grass_2.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_grass_3.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_grass_4.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_dirt_1.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_dirt_2.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_dirt_3.png")));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_dirt_4.png")));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_water_1.png")));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_water_2.png")));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_water_3.png")));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_water_4.png")));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_sand_1.png")));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_sand_2.png")));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_sand_3.png")));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/spr_tiles_sand_4.png")));



        } catch (IOException e) {
            System.err.println("No tile-images were found!");
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

            //this if statement makes sure only tiles in the viewport are drawn
            if (worldX + gp.tileSize > gp.playerEntity.worldX - gp.playerEntity.screenX &&
                    worldX - gp.tileSize < gp.playerEntity.worldX + gp.playerEntity.worldX &&
                    worldY + gp.tileSize > gp.playerEntity.worldY - gp.playerEntity.screenY &&
                    worldY - gp.tileSize < gp.playerEntity.worldY + gp.playerEntity.screenY
            ) {
                //this code draws the tile to the screen.
                g2.drawImage(tile[tileIndex].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
                worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow ++;

            }
        }
    }
}
