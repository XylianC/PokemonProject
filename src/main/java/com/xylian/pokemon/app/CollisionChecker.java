package com.xylian.pokemon.app;

import com.xylian.pokemon.entity.Entity;

public class CollisionChecker{
     GamePanel gp;

     public CollisionChecker(GamePanel gp) {
        this.gp = gp;
     }

     public void CheckTile(Entity entity) {
         int entityLeftWorldX = entity.worldX + entity.solidArea.x;
         int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
         int entityTopWorldY = entity.worldY + entity.solidArea.y;
         int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

         int entityLeftCol = entityLeftWorldX / gp.tileSize;
         int entityRightCol = entityRightWorldX / gp.tileSize;
         int entityTopRow = entityTopWorldY / gp.tileSize;
         int entityBottomRow = entityBottomWorldY / gp.tileSize;

         int tileIndex1, tileIndex2;

         switch(entity.direction) {
             case "up":
                 entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                 tileIndex1 = gp.tileManager.mapTileIndex[entityLeftCol][entityTopRow];
                 tileIndex2 = gp.tileManager.mapTileIndex[entityRightCol][entityTopRow];

                 if(gp.tileManager.tile[tileIndex1].collision || gp.tileManager.tile[tileIndex2].collision){
                     entity.collision = true;
                 }
                 break;
             case "down":
                 entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                 tileIndex1 = gp.tileManager.mapTileIndex[entityLeftCol][entityBottomRow];
                 tileIndex2 = gp.tileManager.mapTileIndex[entityRightCol][entityBottomRow];

                 if(gp.tileManager.tile[tileIndex1].collision || gp.tileManager.tile[tileIndex2].collision){
                     entity.collision = true;
                 }
                 break;
             case "left":
                 entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                 tileIndex1 = gp.tileManager.mapTileIndex[entityLeftCol][entityTopRow];
                 tileIndex2 = gp.tileManager.mapTileIndex[entityLeftCol][entityBottomRow];

                 if(gp.tileManager.tile[tileIndex1].collision || gp.tileManager.tile[tileIndex2].collision){
                     entity.collision = true;
                 }
                 break;
             case "right":
                 entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                 tileIndex1 = gp.tileManager.mapTileIndex[entityRightCol][entityTopRow];
                 tileIndex2 = gp.tileManager.mapTileIndex[entityRightCol][entityBottomRow];

                 if(gp.tileManager.tile[tileIndex1].collision || gp.tileManager.tile[tileIndex2].collision){
                     entity.collision = true;
                 }
                 break;
         }
     }

     public int CheckObject(Entity entity, Boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                // Get entity's world position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's world position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }


        return index;
     }
}
