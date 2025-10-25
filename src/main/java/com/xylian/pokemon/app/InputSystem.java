package com.xylian.pokemon.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;

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

        // Playstate
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
            if (code == KeyEvent.VK_E) {
                interactionPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
        }

        // Pause State
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        // Dialogue State;
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
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
