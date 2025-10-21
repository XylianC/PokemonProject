package com.xylian.pokemon.app;

import com.xylian.pokemon.entity.PlayerEntity;
import com.xylian.pokemon.world.tiles.Tile;
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

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int fps = 60;

    InputSystem input = new InputSystem();
    Thread gameThread;

    TileManager tileManager = new TileManager(this);
    PlayerEntity playerEntity = new PlayerEntity(this,input);

    // Construct the game window
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);

        this.addKeyListener(input);
        this.setFocusable(true);
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
        playerEntity.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);
        playerEntity.draw(g2);

        g2.dispose();
    }
}

