package com.xylian.pokemon.world.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends MasterObject{
    public OBJ_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
