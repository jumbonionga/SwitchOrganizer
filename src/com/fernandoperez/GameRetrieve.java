package com.fernandoperez;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GameRetrieve {
    private String hashPath = "./HashListCSV.csv"; //This might change for an option. Still thinking.
    private File hashFile;
    private Scanner fileScan;
    private HashMap<String, String> savedGames;

    public GameRetrieve() {
        savedGames = new HashMap<>();
    }

    public HashMap<String,String> savedGameRetriever() {
        try {
            hashFile = new File(hashPath);
            fileScan = new Scanner(hashFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] tokens;
        List<String> entries;

        while(fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            tokens = line.split(",");
            savedGames.put(tokens[0],tokens[1]);
        }
        fileScan.close();
        return savedGames;
    }

    public HashMap<String, String> getSavedGames() {
        return savedGames;
    }

    public void setNewGame(String hash, String name) {
        BufferedWriter writer;
        File file;
        try {
            file = new File(hashPath);
            writer = new BufferedWriter(new FileWriter(file,true));

            writer.newLine();
            writer.write(hash + "," + name);

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
