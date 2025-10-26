package com.xylian.pokemon.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xylian.pokemon.domain.Player;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

public class SaveService {
    private static final String SAVE_FILE = "player.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void savePlayer(Player player) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)){
            gson.toJson(player, writer);
            System.out.printf("%nDEBUG: Player saved!%n");
        } catch (IOException e) {
            System.err.printf("%nDEBUG: Could not save player = %s%n", e.getMessage());
        }
    }

    public static Player loadPlayer() {
        try (FileReader reader = new FileReader(SAVE_FILE)) {
            return gson.fromJson(reader, Player.class);
        } catch (IOException e) {
            return null;
        }
    }
}
