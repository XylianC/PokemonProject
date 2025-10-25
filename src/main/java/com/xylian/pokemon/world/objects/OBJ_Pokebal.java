package com.xylian.pokemon.world.objects;

import com.xylian.pokemon.app.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Pokebal extends MasterObject{
    GamePanel gp;

    public OBJ_Pokebal(GamePanel gp) {
        this.gp = gp;
        name = "Pokebal";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_pokebal.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
