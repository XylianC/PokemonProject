package com.xylian.pokemon.app;

import com.xylian.pokemon.world.objects.OBJ_Chest;
import com.xylian.pokemon.world.objects.OBJ_Key;

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
        gp.obj[1].worldY = 18 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 19 * gp.tileSize;

    }
}
