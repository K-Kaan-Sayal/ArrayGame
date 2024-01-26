package com.arraygame.game;

class Path {

    int index;
    Path jumpedPath;
    Path walkForwardPath;
    Path walkBackPath;

    void printPath(String prefix) {
        System.out.println(prefix + "Path Index: " + index);

        if (jumpedPath != null) {
            System.out.println(prefix + "Jumped to:");
            jumpedPath.printPath(prefix + "  ");
        }

        if (walkForwardPath != null) {
            System.out.println(prefix + "Walked Forward to:");
            walkForwardPath.printPath(prefix + "  ");
        }

        if (walkBackPath != null) {
            System.out.println(prefix + "Walked Back to:");
            walkBackPath.printPath(prefix + "  ");
        }
    }
}
