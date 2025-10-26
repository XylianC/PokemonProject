package com.xylian.pokemon.ui;

import com.xylian.pokemon.app.GamePanel;
import com.xylian.pokemon.entity.NPC_MonsterTest;
import com.xylian.pokemon.world.objects.OBJ_Pokebal;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UICanvas {
    GamePanel gp;
    Graphics2D g2;

    Font arialFont35, arialFont75, fnt_PixelOperator;

    public boolean messageOn = false;
    public String message = "";

    // Pause Menu & Commands
    public int pauseMenuIndex = 0;
    public int pauseMenuState = 0;

    // Dialog
    public String currentDialogue;

    // TitleScreen Menu & Commands
    public int mainMenuIndex = 0;
    public int mainMenuState = 0;

    // Welcoming Text
    public int introStep = 0;
    String gender = null;

    // Battle screen Menu & Commands
    public int battleMenuIndex = 0;
    public int battleMenuState = 0;

    // Yes No Menu
    public int thisOrThatIndex = 0;
    public int thisOrThatResponse = 3;
    public boolean thisOrThatAnswered = false;
    public boolean thisOrThatOpen = false;


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

        // Title State
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // Play State
        if(gp.gameState == gp.playState) {
            drawOverworldInterface();
        }

        // Pause State - Maybe i can add menu functions here?
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // Dialogue State
        if(gp.gameState == gp.dialogueState) {
            drawDialogWindow();
        }

        // Battle State
        if(gp.gameState == gp.battleState) {
            drawBattleScreen();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawTitleScreen() {
        if(mainMenuState == 0) {
            g2.setColor(new Color(250, 100, 100));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


            setFontSize(200);
            String text = "Pokepals";
            int x = getTextCenterOnScreen(text);
            int y = gp.screenHeight / 2 - (gp.tileSize * 3);

            // Draw shadow
            g2.setColor(new Color(0, 0, 0, 100));
            g2.drawString(text, x + 5, y + 5);

            // Draw text on top of the shadow
            g2.setColor(new Color(255, 255, 255));
            g2.drawString(text, x, y);

            // Pokebal image
            x = gp.screenWidth / 2 - (gp.tileSize * 4) / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 2);
            g2.drawImage(new OBJ_Pokebal(gp).image, x, y, gp.tileSize * 4, gp.tileSize * 4, null);

            // Draw menu
            setFontSize(60);

            text = "NEW GAME";
            x = getTextCenterOnScreen(text);
            y += gp.tileSize * 6;
            g2.drawString(text, x, y);

            if (mainMenuIndex == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "CONTINUE";
            x = getTextCenterOnScreen(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (mainMenuIndex == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "SETTINGS";
            x = getTextCenterOnScreen(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (mainMenuIndex == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "QUIT";
            x = getTextCenterOnScreen(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (mainMenuIndex == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

        // New Game Window
        if(mainMenuState == 1) {
            int x = gp.screenWidth / 2 - (gp.tileSize * 4) / 2;
            int y = gp.screenHeight / 2 - (gp.tileSize * 2);
            g2.drawImage(new OBJ_Pokebal(gp).image, x, y, gp.tileSize * 4, gp.tileSize * 4, null);

            switch (introStep) {
                case 0: {
                    currentDialogue = "Welcome to the world of Pokemon\nAre you a boy, or a girl?";
                    drawDialogWindow();
                    gender = drawThisOrThatWindow("Boy", "Girl");
                    if(gender != null) {thisOrThatOpen = false; introStep ++;}
                    break;
                }
                case 1: {
                    currentDialogue = "Ah, so you are a " + gender;
                    drawDialogWindow();
                    break;
                }
                case 2: {
                    currentDialogue = "In this world, people live together in harmony side\nby side, with Pokemon! Some use them for battling,\n some use them as pets.";
                    drawDialogWindow();
                    break;
                }
                case 3: {
                    currentDialogue = "I'm the pokemon professor, I study Pokemon for a living!";
                    drawDialogWindow();
                    break;
                }
                case 4: {
                    mainMenuState = 0;
                    introStep = 0;
                    gp.gameState = gp.playState;
                }
            }
        }
        // Settings Window
        if(mainMenuState == 2) {
            // Do nothing for now
        }
    }

    public void drawOverworldInterface() {

    }

    public void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 75));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        String text = "";

        int margin = gp.tileSize;
        int width = gp.tileSize * 6;
        int height = gp.screenHeight - margin * 5;
        int x = gp.screenWidth - width - margin;
        int y = margin;

        g2.setColor(new Color(0, 0, 0, 200)); // semi-transparant
        g2.fillRect(x, y, width, height);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(x, y, width, height);

        // Draw Text and cursor
        setFontSize(50);
        x += gp.tileSize;
        y += gp.tileSize * 2 - 24;

        text = "POKEDEX";
        g2.drawString(text, x, y);

        if (pauseMenuIndex == 0) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * 2;

        text = "POKEMON";
        g2.drawString(text, x, y);

        if (pauseMenuIndex == 1) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * 2;

        text = "INVENTORY";
        g2.drawString(text, x, y);

        if (pauseMenuIndex == 2) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * 2;

        text = "TRAINER";
        g2.drawString(text, x, y);

        if (pauseMenuIndex == 3) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * 2;

        text = "SETTINGS";
        g2.drawString(text, x, y);

        if (pauseMenuIndex == 4) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * 2;

        text = "QUIT";
        g2.drawString(text, x, y);

        if (pauseMenuIndex == 5) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }



    }

    public void drawBattleScreen() {
        // Draw RGB background
        g2.setColor(new Color(100, 180, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        setFontSize(40);

        // Draw Player Pokemon
        int playerMonX = gp.tileSize * 3;
        int playerMonY = gp.tileSize * 7;
        String playerMonName = "BIG BALL OF FIRE";

        g2.drawImage(new NPC_MonsterTest(gp).down1, playerMonX, playerMonY, gp.tileSize * 5, gp.tileSize * 5, null);

        int width = gp.tileSize * 7;
        int height = gp.tileSize * 2;
        int x = gp.screenWidth - (gp.tileSize * 8);
        int y = gp.tileSize * 10;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize - gp.tileSize / 2;
        y += gp.tileSize;

        g2.setColor(Color.white);
        g2.drawString(playerMonName, x, y);

        // Draw Opponent Pokemon
        int opponentMonX = gp.tileSize * 15;
        int opponentMonY = gp.tileSize * 2;
                String opponentMonName = "BIG BLOB OF FOIR";
        g2.drawImage(new NPC_MonsterTest(gp).left1, opponentMonX, opponentMonY, gp.tileSize * 5, gp.tileSize * 5, null);

        x = gp.tileSize;
        y = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize - gp.tileSize / 2;
        y += gp.tileSize;

        g2.setColor(Color.white);
        g2.drawString(opponentMonName, x , y);

        String text = "";

        // Battle Dialogue
        currentDialogue = "You are now fighting!";
        drawBattleDialogWindow();

        width = gp.tileSize * 7;
        height = gp.tileSize * 4;
        x = gp.screenWidth - (gp.tileSize * 8);
        y = gp.tileSize * 13;
        drawSubWindow(x, y, width, height);

        // Draw Text and cursor
        setFontSize(40);
        x += gp.tileSize;
        y += gp.tileSize * 2 - 24;

        text = "FIGHT";
        g2.drawString(text, x, y);

        int lineHeight = 1;

        if (battleMenuIndex == 0) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * lineHeight;

        text = "INFO";
        g2.drawString(text, x, y);

        if (battleMenuIndex == 1) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        x += gp.tileSize * 3;
        y -= gp.tileSize * lineHeight;

        text = "ITEM";
        g2.drawString(text, x, y);

        if (battleMenuIndex == 2) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * lineHeight;

        text = "RUN";
        g2.drawString(text, x, y);

        if (battleMenuIndex == 3) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
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

    public void drawBattleDialogWindow() {
        // Window paramaters
        int x = gp.tileSize * 1;
        int y = gp.tileSize * 13;
        int width = gp.tileSize * 15;
        int height = gp.tileSize * 4;

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

    public String drawThisOrThatWindow(String firstOption, String secondOption) {
        thisOrThatOpen = true;
        int lineHeight = 1;

        int width = gp.tileSize * 6;
        int height = gp.tileSize * 5;
        int x = gp.screenWidth - (gp.tileSize * 8);
        int y = gp.tileSize * 12;
        drawSubWindow(x, y, width, height);

        // Draw text and cursor
        setFontSize(40);
        x += gp.tileSize;
        y += gp.tileSize * 2 - 24;

        g2.drawString(firstOption, x, y);
        if (thisOrThatIndex == 0) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        y += gp.tileSize * lineHeight;

        g2.drawString(secondOption, x, y);
        if (thisOrThatIndex == 1) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }

        // Decide result (if answered)
        String result = null;

        if (thisOrThatAnswered) {
            result = (thisOrThatResponse == 0) ? firstOption : secondOption;
            thisOrThatAnswered = false; // reset AFTER capturing result
        }

        return result; // null until answered
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
