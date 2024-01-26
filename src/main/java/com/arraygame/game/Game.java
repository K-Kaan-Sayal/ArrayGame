package com.arraygame.game;


import lombok.Getter;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Game {
    final int[] board;
    final int leap;
    int gameNo;
    Path path;
    Set<Integer> generatedRoots;

    public Game(int[] board, int leap, int gameNo) {
        this.board = board;
        this.leap = leap;
        this.gameNo = gameNo;
        this.generatedRoots = new HashSet<>();
        this.path = generatePath(0);
    }

    public void printGame() {

        if ( null == path ) return;

        System.out.println("\n********** Game No: " + gameNo + " **********");
        for (int i : board) {
            System.out.print(i + ", ");
        }
        System.out.println("\nleap: " + leap);
        for (int i : generatedRoots) {
            System.out.print(i + ", ");
        }
        path.printPath("");
    }

    public boolean canWinTheGame() {

        if ( null == path ) return false;

        return board.length- 1 <= Collections.max(generatedRoots);
    }

    private Path generatePath(int index) {

        if ( index == 0 && getMaxBarrierSize() >= leap && leap != 0 ) {
            return null;
        }

        generatedRoots.add(index);

        Path rootPath = new Path();
        rootPath.index = index;

        EnumSet<Actions> actions = getActions(index);

        actions.forEach(action -> {

            switch (action) {
                case JUMP:
                    rootPath.jumpedPath = generatePath(index + leap);
                    break;
                case FORWARD:
                    rootPath.walkForwardPath = generatePath(index + 1);
                    break;
                case BACK:
                    rootPath.walkBackPath = generatePath(index - 1);
                    break;
            }
        });

        return rootPath;
    }

    private EnumSet<Actions> getActions(int index) {

        EnumSet<Actions> actions = EnumSet.noneOf(Actions.class);

        if ( canJump(index) ) actions.add(Actions.JUMP);
        if ( canWalkForward(index) ) actions.add(Actions.FORWARD);
        if ( canWalkBack(index) ) actions.add(Actions.BACK);

        return actions;
    }

    private boolean canJump(int index) {
        return index + leap < board.length + leap * 2
                &&(index + leap >= board.length || board[index + leap] == 0)
                && leap > 1
                && (generatedRoots.isEmpty() || !generatedRoots.contains(index + leap));
    }

    private boolean canWalkForward(int index) {
        return (index + 1 <= board.length -1 && board[index + 1] == 0)
                && (generatedRoots.isEmpty() || !generatedRoots.contains(index + 1));
    }

    private boolean canWalkBack(int index) {
        return index < board.length-1
                && (index != 0 && board[(index - 1)] == 0)
                && (generatedRoots.isEmpty() || !generatedRoots.contains(index -1));
    }

    private int getMaxBarrierSize() {
        int barrierSize = 0;
        int maxSize = 0;
        for (int j : board) {
            if ( j == 1 ) {
                barrierSize++;
            } else {
                maxSize = Math.max(maxSize, barrierSize);
                barrierSize = 0;
            }
        }
        maxSize = Math.max(maxSize, barrierSize);

        return maxSize;
    }

}
