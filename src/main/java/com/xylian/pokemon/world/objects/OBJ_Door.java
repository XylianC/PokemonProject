package com.xylian.pokemon.world.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends MasterObject{
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
