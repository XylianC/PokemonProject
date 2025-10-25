package com.xylian.pokemon.world.objects;

import com.xylian.pokemon.app.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends MasterObject{
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        this.gp = gp;

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spr_key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
