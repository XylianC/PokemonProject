package com.xylian.pokemon.app;

import com.xylian.pokemon.entity.NPC_MonsterTest;
import com.xylian.pokemon.entity.NPC_Test;
import com.xylian.pokemon.world.objects.*;

public class AssetPlacer {
    GamePanel gp;

    public AssetPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void SetObject() {
        gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = 7 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;

        gp.obj[1] = new OBJ_Chest(gp);
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 23 * gp.tileSize;

        gp.obj[2] = new OBJ_Pokebal(gp);
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;

    }

    public void SetNPC() {
        gp.npc[0] = new NPC_Test(gp);
        gp.npc[0].worldX = gp.tileSize * 15;
        gp.npc[0].worldY = gp.tileSize * 22;

        gp.npc[1] = new NPC_MonsterTest(gp);
        gp.npc[1].worldX = gp.tileSize * 16;
        gp.npc[1].worldY = gp.tileSize * 22;
    }
}
