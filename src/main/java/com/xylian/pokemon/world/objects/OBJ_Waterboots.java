package com.xylian.pokemon.world.objects;

import com.xylian.pokemon.app.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Waterboots extends MasterObject {
    GamePanel gp;

    public OBJ_Waterboots(GamePanel gp) {
        this.gp = gp;
        name = "WaterBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_waterboots.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
