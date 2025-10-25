package com.xylian.pokemon.ui;

import com.xylian.pokemon.app.GamePanel;

import java.awt.*;
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


        if(gp.gameState == gp.titleState) {
            setFontSize(80);
            drawTitleScreen();
        }

        // Play State
        if(gp.gameState == gp.playState) {
            drawOverworldInterface();
        }

        // Pause State - Maybe i can add menu functions here?
        if(gp.gameState == gp.pauseState) {
            setFontSize(200);
            drawMenuScreen();
        }

        // Dialogue State
        if(gp.gameState == gp.dialogueState) {
            drawDialogWindow();
        }

        // Battle State
        if(gp.gameState == gp.battleState) {
            setFontSize(80);
            drawBattleScreen();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawTitleScreen() {
        String text = "Pokepals";
        int x = getTextCenterOnScreen(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawOverworldInterface() {

    }

    public void drawMenuScreen() {
        String text = "PAUSED";
        int x = getTextCenterOnScreen(text);
        int y = gp.screenHeight /2 - (gp.tileSize);

        g2.drawString(text, x, y);
    }

    public void drawBattleScreen() {
        String text = "Battlescene";
        int x = getTextCenterOnScreen(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
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

        setFontSize(40);
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

    public void setFontSize(int fontSize) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, fontSize));
    }
}
