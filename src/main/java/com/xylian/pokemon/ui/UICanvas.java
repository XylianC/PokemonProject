package com.xylian.pokemon.ui;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.world.objects.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UICanvas {
    GamePanel gp;

    Font arialFont35, arialFont75;

    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    int messageDisplayTime = 90; // 60 = 1 second

    public boolean gameFinished = false;

    public UICanvas(GamePanel gp) {
        this.gp = gp;
        arialFont35 = new Font("Arial", Font.PLAIN, 35);
        arialFont75 = new Font("Arial", Font.BOLD, 75);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void draw(Graphics2D g2) {
        if(gameFinished) {
            String text = "You won the game";

            g2.setFont(arialFont35);
            g2.setColor(Color.white);

            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth / 2 - textLength / 2;
            int y = gp.screenHeight / 2 - gp.tileSize * 3;

            g2.drawString(text, x, y);

            text = "Congratulations!";

            g2.setFont(arialFont75);
            g2.setColor(Color.blue);

            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 3;


            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(arialFont35);
            g2.setColor(Color.white);

            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.playerEntity.amountOfKeys, 65, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(25f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 3);

                messageCounter++;

                if (messageCounter > messageDisplayTime) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}
