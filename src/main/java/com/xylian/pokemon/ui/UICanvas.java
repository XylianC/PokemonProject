package com.xylian.pokemon.ui;

import com.xylian.pokemon.app.GamePanel;

import java.awt.*;

public class UICanvas {
    GamePanel gp;
    Font arialFont;

    public UICanvas(GamePanel gp) {
        this.gp = gp;
        arialFont = new Font("Arial", Font.PLAIN, 35);
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arialFont);
        g2.setColor(Color.white);

        g2.drawString("Keys: " + gp.playerEntity.amountOfKeys, 50, 50);
    }
}
