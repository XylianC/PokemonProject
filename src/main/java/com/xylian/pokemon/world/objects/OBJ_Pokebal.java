package com.xylian.pokemon.world.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Pokebal extends MasterObject{
    public OBJ_Pokebal() {
        name = "Pokebal";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_pokebal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
