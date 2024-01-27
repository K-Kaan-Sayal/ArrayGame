package com.arraygame;

import com.arraygame.game.DataReader;
import com.arraygame.game.Game;
import com.arraygame.game.Output;

import java.util.*;

public class Main {


    public static void main(String[] args) {

        String inputFile = "src/main/java/com/data/input.txt";
        String outputFile = "src/main/java/com/data/output.txt";

        List<Output> outputList = DataReader.readOutputs(outputFile);
        List<Game> gameList = DataReader.readInputs(inputFile);

        gameList.parallelStream().forEach(game -> {

            boolean expectedOutput = outputList.parallelStream()
                    .filter(output -> output.getGameNo() == game.getGameNo())
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Output not found for game: " + game.getGameNo()))
                    .isWin();

            boolean canWin = game.canWinTheGame();

            game.printGame();

            if ( canWin != expectedOutput ) {
                System.out.println("Fix your code. Game No: " + game.getGameNo() + ", Expected Output: " + expectedOutput + ", Your Output: " + canWin + ", leap: " + game.getLeap());
            }else {
                System.out.println("Game " + game.getGameNo() + " passed.");
            }
        });
    }
}
