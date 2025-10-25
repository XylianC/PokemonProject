package com.xylian.pokemon.app;

import com.xylian.pokemon.entity.Entity;
import com.xylian.pokemon.entity.PlayerEntity;
import com.xylian.pokemon.ui.UICanvas;
import com.xylian.pokemon.world.objects.MasterObject;
import com.xylian.pokemon.world.tiles.TileManager;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    // Settings for the window
    final int originalTileSize = 16; //16x16 tileset
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 18;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // world map settings
    public final int maxWorldCol = 72;
    public final int maxWorldRow = 54;

    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // Game controllers
    // fps settings
    int fps = 60;

    public InputSystem input = new InputSystem(this);
    public Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetPlacer aPlacer = new AssetPlacer(this);
    public TileManager tileManager = new TileManager(this);
    public UICanvas ui = new UICanvas(this);

    // Sound
    public Sound music = new Sound();
    public Sound sound = new Sound();

    // Entity and objects
    public PlayerEntity playerEntity = new PlayerEntity(this,input);
    public MasterObject obj[] = new MasterObject[10];

    public Entity npc[] = new Entity[10];

    // Game states
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int battleState = 4;


    // Construct the game window
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(input);
        this.setFocusable(true);
    }

    public void setupGame() {
        aPlacer.SetObject();
        aPlacer.SetNPC();

        //playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Game loop code must be here.
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if(gameState == playState) {
            playerEntity.update();

            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState) {
            // nothing for now
        }
        if(gameState == battleState) {
            // nothing for now
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        else if (gameState == playState || gameState == pauseState){
            tileManager.draw(g2);

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // draw npc's
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }

            playerEntity.draw(g2);
            ui.draw(g2);
        }

        else if (gameState == battleState) {
            ui.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic(int i ) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

}

