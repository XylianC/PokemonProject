package com.xylian.pokemon.world.objects;

import com.xylian.pokemon.app.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends MasterObject{
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        this.gp = gp;

        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
