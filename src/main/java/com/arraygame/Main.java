package com.arraygame;

import com.arraygame.game.DataReader;
import com.arraygame.game.Game;
import com.arraygame.game.Output;

import java.util.*;


/*
Let's play a game on an array!
You're standing at index 0 of an n-element array named game. From some index i (where 0<=i<n), you can perform one of the following moves:

    Move Backward: If cell i-1 exists and contains a 0, you can walk back to cell i-1.
    Move Forward:
        If cell i+1 contains a zero, you can walk to cell i+1.
        If cell i+leap contains a zero, you can jump to cell i+leap.
        If you're standing in cell n-1 or the value of i+leap >= n, you can walk or jump off the end of the array and win the game.

In other words, you can move from index i to index i+1, i-1, or i+leap as long as the destination index is a cell containing a 0.
If the destination index is greater than n-1, you win the game.

Function Description
    Complete the canWin function in the editor below.
    canWin has the following parameters:
        int leap: the size of the leap
        int game[n]: the array to traverse

Explanation

We perform the following  queries:

For game = [0, 0, 0, 0, 0] and leap = 3, we can walk and/or jump to the end of the array because every cell contains a 0. Because we can win, we return true.
For game = [0, 0, 0, 1, 1, 1] and leap = 5, we can walk to index 1 and then jump i + leap = 1 + 5 = 6 units to the end of the array. Because we can win, we return true.
For game = [0, 0, 1, 1, 1, 0] and leap = 3, there is no way for us to get past the three consecutive ones. Because we cannot win, we return false.
For game = [0, 1, 0] and leap = 1, there is no way for us to get past the one at index 1. Because we cannot win, we return false.

    0 => i, j
    n => i, g, j
 */

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

            boolean canWin = canWin(game);

           // game.printGame();

            if ( canWin != expectedOutput ) {
                System.out.println("Fix your code. Game No: " + game.getGameNo() + ", Expected Output: " + expectedOutput + ", Your Output: " + canWin + ", leap: " + game.getLeap());
            }else {
                System.out.println("Game " + game.getGameNo() + " passed.");
            }
        });
    }

    static boolean canWin(Game game) {

        return game.canWinTheGame();
    }


}
