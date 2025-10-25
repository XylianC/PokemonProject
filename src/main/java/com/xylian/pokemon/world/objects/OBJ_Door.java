package com.xylian.pokemon.world.objects;

import com.xylian.pokemon.app.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends MasterObject{
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        this.gp = gp;
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
