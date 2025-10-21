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
}
