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
    String dirMapTest = "/maps/map3.csv";

    public TileManager(GamePanel gp) {
        this.gp = gp;

        int amountOfTiles = 64;


        tile = new Tile[amountOfTiles];
        mapTileIndex = new int[gp.maxWorldCol][gp.maxWorldRow];

        LoadMap();
        GetTileImage();
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

    public void GetTileImage() {
        BufferedImage image = null;
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-1.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-2.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-3.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-4.png")));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-5.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-6.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-7.png")));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-1-column-8.png")));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-1.png")));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-2.png")));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-3.png")));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-4.png")));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-5.png")));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-6.png")));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-7.png")));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-2-column-8.png")));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-1.png")));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-2.png")));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-3.png")));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-4.png")));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-5.png")));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-6.png")));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-7.png")));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-3-column-8.png")));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-1.png")));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-2.png")));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-3.png")));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-4.png")));

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-5.png")));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-6.png")));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-7.png")));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-4-column-8.png")));

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-1.png")));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-2.png")));
            tile[33].collision = true;

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-3.png")));
            tile[34].collision = true;

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-4.png")));

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-5.png")));
            tile[36].collision = true;

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-6.png")));
            tile[37].collision = true;

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-7.png")));
            tile[38].collision = true;

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-5-column-8.png")));

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-1.png")));
            tile[40].collision = true;

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-2.png")));
            tile[41].collision = true;

            tile[42] = new Tile();
            tile[42].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-3.png")));
            tile[42].collision = true;

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-4.png")));

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-5.png")));
            tile[44].collision = true;

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-6.png")));
            tile[45].collision = true;

            tile[46] = new Tile();
            tile[46].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-7.png")));
            tile[46].collision = true;

            tile[47] = new Tile();
            tile[47].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-6-column-8.png")));

            tile[48] = new Tile();
            tile[48].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-1.png")));

            tile[49] = new Tile();
            tile[49].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-2.png")));

            tile[50] = new Tile();
            tile[50].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-3.png")));

           // tile[51] = new Tile();
           // tile[51].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-4.png")));

            tile[52] = new Tile();
            tile[52].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-5.png")));

            tile[53] = new Tile();
            tile[53].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-6.png")));

            tile[54] = new Tile();
            tile[54].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-7.png")));

            tile[55] = new Tile();
            tile[55].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-7-column-8.png")));

            tile[56] = new Tile();
            tile[56].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-1.png")));

            tile[57] = new Tile();
            tile[57].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-2.png")));

            tile[58] = new Tile();
            tile[58].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-3.png")));

            //tile[59] = new Tile();
            //tile[59].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-4.png")));

            tile[60] = new Tile();
            tile[60].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-5.png")));

            tile[61] = new Tile();
            tile[61].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-6.png")));

            tile[62] = new Tile();
            tile[62].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-7.png")));

            tile[63] = new Tile();
            tile[63].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/row-8-column-8.png")));


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
                    worldX - gp.tileSize < gp.playerEntity.worldX + gp.playerEntity.screenX &&
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
