package com.xylian.pokemon.app;

import com.xylian.pokemon.world.objects.*;

public class AssetPlacer {
    GamePanel gp;

    public AssetPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void SetObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 5 * gp.tileSize;
        gp.obj[0].worldY = 5 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 11 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 19 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 7 * gp.tileSize;
        gp.obj[3].worldY = 20 * gp.tileSize;

        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 20 * gp.tileSize;

        gp.obj[5] = new OBJ_Pokebal();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 19 * gp.tileSize;

        gp.obj[6] = new OBJ_Waterboots();
        gp.obj[6].worldX = 23 * gp.tileSize;
        gp.obj[6].worldY = 30 * gp.tileSize;
    }
}
