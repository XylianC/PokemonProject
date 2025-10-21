package com.xylian.pokemon.world.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends MasterObject{
    public OBJ_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
