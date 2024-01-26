package com.arraygame.game;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {

    public static List<Output> readOutputs(String path) {

        LinkedList<Output> expectedOutputs = new LinkedList<>();

        try (BufferedReader outputReader = new BufferedReader(new FileReader(path))) {

            String outputLine;
            int gameNo = 0;
            while ((outputLine = outputReader.readLine()) != null) {
                if ( outputLine.equals("YES") ) expectedOutputs.add(new Output(gameNo, true));
                else if ( outputLine.equals("NO") ) expectedOutputs.add(new Output(gameNo, false));
                else throw new RuntimeException("Output cannot read correctly");

                gameNo++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return expectedOutputs;
    }

    public static List<Game> readInputs(String path) {

        List<Game> gameList = new LinkedList<>();

        try (BufferedReader inputReader = new BufferedReader(new FileReader(path))) {

            String inputLine;
            int lineCounter = 0;
            int gameNo = 0;
            int leap = 0;
            List<Integer> board = new ArrayList<>();
            while ((inputLine = inputReader.readLine()) != null) {

                String[] parts = inputLine.split("\\s+");

                List<Integer> inputs = Arrays.stream(parts).map(Integer::valueOf).collect(Collectors.toList());

                if ( lineCounter == 0 ) leap = inputs.get(1);
                if ( lineCounter == 1 ) board = inputs;

                lineCounter++;
                if ( lineCounter == 2 ) {
                    lineCounter = 0;

                    gameList.add(new Game(board.stream().mapToInt(i -> i).toArray(), leap, gameNo));
                    gameNo++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gameList;
    }

    private DataReader() {
    }
}
