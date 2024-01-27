
# Array Game Solver
This repository contains a solution for a coding challenge focused on navigating through an array game. The game operates on a simple board represented by an array and incorporates a unique leap mechanic that adds complexity to the challenge.

## Problem Description
In the array game, players start at index 0 of an n-element array named game. The objective is to reach or surpass the end of the array using a combination of moves that involve walking or leaping forward and, if necessary, stepping backward.

### Input Format
Input data is read from a file named `input.txt`, structured as follows:

- The first line contains two integers: the first represents the size of the array `n`, and the second specifies the leap number `leap`.
- The second line provides the array itself, denoted as the game board.

### Output Format
The expected outputs are written to a file named `output.txt`, where each line contains either `YES` or `NO`. A `YES` indicates a scenario where it is possible to navigate through or leap off the end of the array, thereby winning the game. Conversely, a `NO` signifies an impassable arrangement.

### Rules of Movement
Players can move in the array using the following rules:

- Move Backward: Move to cell `i-1` if it exists and contains a `0`.
- Move Forward: Move to cell `i+1` if it contains a `0`.
- Leap Forward: Jump to cell `i+leap` if it contains a `0`. Winning the game occurs by either standing in cell `n-1` or when i+leap is beyond the array's bounds (n-1).
  The essence of the game is to navigate through the array by strategically choosing when to move or leap, considering the placement of 0's and 1's within the array.

### Examples
- For a board [0, 0, 0, 0, 0] with leap = 3, traversal to the array's end is straightforward, resulting in a win `YES`.
- With a board [0, 0, 0, 1, 1, 1] and leap = 5, one can move to index 1 and leap to the end, also resulting in a win `YES`.
- Given [0, 0, 1, 1, 1, 0] with leap = 3, the sequence of three 1s blocks progress, resulting in a loss `NO`.
- For a board [0, 1, 0] and leap = 1, the solitary 1 at index 1 prevents winning, leading to a loss `NO`.

### Solving The Problem
First, the values in the `input.txt` file are read, and a new game object is created from the Game class for each game. In the constructor method of the Game class, alternative scenarios that will bring the index `i` value closer to the `n` value are calculated using the `generatePath` method. If the condition `i >= n-1` is met during the creation of a new Path, it is understood that the game can be won, and no further Path research is conducted.

The above calculations are completed during the creation of the Game class. After an object is created from the Game class, the `canWinTheGame` method becomes available for the respective object.

Once all Game objects are created from the data in the `input.txt` file, the `canWinTheGame` method is called for each game, and the calculated result is compared with the actual result in the `output.txt` file.
