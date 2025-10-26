package com.xylian.pokemon.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputSystem implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, interactionPressed;

    public InputSystem(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Title State
        if(gp.gameState == gp.titleState) {
            if (gp.ui.mainMenuState == 0) {
                if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
                    gp.ui.mainMenuIndex--;
                    if (gp.ui.mainMenuIndex < 0) {
                        gp.ui.mainMenuIndex = 3;
                    }
                }
                if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                    gp.ui.mainMenuIndex++;
                    if (gp.ui.mainMenuIndex > 3) {
                        gp.ui.mainMenuIndex = 0;
                    }
                }
                if (code == KeyEvent.VK_E || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                    switch (gp.ui.mainMenuIndex) {
                        case 0:
                            gp.gameState = gp.ui.mainMenuState++;
                            break;
                        case 1:
                            gp.gameState = gp.playState;
                            break;
                        case 2:
                            gp.gameState = gp.playState;
                            break;
                        case 3:
                            System.exit(0);
                            break;
                    }
                }
            } else if (gp.ui.mainMenuState == 1) {
                if (code == KeyEvent.VK_E || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                    gp.gameState = gp.ui.mainMenuState++;

                }
            }
        }

        // Play State
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_Z) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_Q) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_E || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                interactionPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
        }

        // Pause State
        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
                gp.ui.pauseMenuIndex--;
                if (gp.ui.pauseMenuIndex < 0) {
                    gp.ui.pauseMenuIndex = 5;
                }
            }
            if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.ui.pauseMenuIndex++;
                if (gp.ui.pauseMenuIndex > 5) {
                    gp.ui.pauseMenuIndex = 0;
                }
            }
        }

        // Dialogue State;
        if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
        }

        // Battle State
        if (gp.gameState == gp.battleState) {
            if(code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
                gp.ui.battleMenuIndex--;
                if (gp.ui.battleMenuIndex < 0) {
                    gp.ui.battleMenuIndex = 3;
                }
            }
            if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.ui.battleMenuIndex++;
                if (gp.ui.battleMenuIndex > 3) {
                    gp.ui.battleMenuIndex = 0;
                }
            }
            if (code == KeyEvent.VK_E || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                switch (gp.ui.battleMenuIndex) {
                    case 0:
                        gp.ui.battleMenuState = 0;
                        System.out.println("Choose your attack!");
                        break;
                    case 1:
                        gp.ui.battleMenuState = 1;
                        System.out.println("Here is some info!");
                        break;
                    case 2:
                        gp.ui.battleMenuState = 2;
                        System.out.println("Choose an item!");
                        break;
                    case 3:
                        gp.gameState = gp.playState;
                        break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_Q){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
