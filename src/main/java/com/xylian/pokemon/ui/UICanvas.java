package com.xylian.pokemon.ui;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.world.objects.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UICanvas {
    GamePanel gp;
    Graphics2D g2;

    Font arialFont35, arialFont75, fnt_PixelOperator;

    public boolean messageOn = false;
    public String message = "";

    int messageCounter = 0;
    int messageDisplayTime = 90; // 60 = 1 second

    public boolean gameFinished = false;

    // Dialog
    public String currentDialogue;


    public UICanvas(GamePanel gp) {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/fonts/pixel_operator/PixelOperator.ttf");

        try {
            fnt_PixelOperator = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        arialFont35 = new Font("Arial", Font.PLAIN, 35);
        arialFont75 = new Font("Arial", Font.BOLD, 75);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(fnt_PixelOperator);
        g2.setColor(Color.white);

        // Play State
        if(gp.gameState == gp.playState) {
            drawUserInterface();
        }

        // Pause State - Maybe i can add menu functions here?
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // Dialogue State
        if(gp.gameState == gp.dialogueState) {
            drawDialogWindow();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getTextCenterOnScreen(text);
        int y = gp.screenHeight /2;

        g2.drawString(text, x, y);
    }

    public void drawUserInterface() {

    }

    public void drawDialogWindow() {
        // Window paramaters
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 12;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 5;

        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize + 10;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color windowColor = new Color(0, 0, 0, 200);

        g2.setColor(windowColor);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        Color windowBorderColor = new Color(255, 255, 255);
        g2.setColor(windowBorderColor);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getTextCenterOnScreen(String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int centerPosition = (gp.screenWidth / 2) - (textLength/2);
        return centerPosition;
    }

}
