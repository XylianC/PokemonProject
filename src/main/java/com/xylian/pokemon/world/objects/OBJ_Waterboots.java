package com.xylian.pokemon.world.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Waterboots extends MasterObject {
    public OBJ_Waterboots() {
        name = "WaterBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_waterboots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
