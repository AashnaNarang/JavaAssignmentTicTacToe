/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

/**
 * This class allows you to create TicTacToe objects and includes methods to
 * allow someone to play the game.
 *
 * @author Aashna Narang
 * @Since 2018-10-03
 */
public class TicTacToe {

    //Instance variables
    private int nRows;
    private int nColumns;
    private int numToWin;
    private char turn;
    private char[][] grid;
    private TicTacToeEnum gameState;
    private int nMarks;

    /**
     * Default one-parameter constructor that initializes nRows, nColumns, and
     * numToWin as 3 using the this() method. This() uses the other constructor
     * to create a new array that acts as the grid, initializes gameState to
     * IN_PROGRESS, and nMarks to 0. Initializes turn to the argument.
     *
     * @param initialTurn
     */
    public TicTacToe(char initialTurn) {
        this(3, 3, 3, initialTurn);
    }

    /**
     * Four argument constructor for a customized game that sets all the
     * arguments to the instance variables. A new instance of grid is created
     * and made empty, gameState is initialized to IN_PROGRESS, and nMarks to 0.
     *
     * @param nRows represents number of rows of the tic tac toe grid
     * @param nColumns represents number of columns of the tic tac toe grid
     * @param numToWin represents number of consecutive Xs or Os to win
     * @param initialTurn represents which player starts the game, X or O
     */
    public TicTacToe(int nRows, int nColumns, int numToWin, char initialTurn) {

        //Checking for illegal input 
        if (nColumns < 0 || nRows < 0) {
            throw new IllegalArgumentException("Please enter a valid number.");
        }
        if (nColumns < numToWin || nRows < numToWin) {
            throw new IllegalArgumentException("Please give a valid grid or change numToWin.");
        }
        if (initialTurn != 'X' && initialTurn != 'O') {
            throw new IllegalArgumentException("Not a valid player");
        }

        //declaring instance variables
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;
        this.turn = initialTurn;
        this.nMarks = 0;

        //call reset to create empty grid or do this
        grid = new char[nRows][nColumns];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                this.grid[i][j] = ' ';
            }
        }
        gameState = TicTacToeEnum.IN_PROGRESS;
    }

    //Public Methods:
    /**
     * This Method resets the game by emptying the grid and resetting variables
     *
     * @param initialTurn which player starts 'X' or 'O'
     */
    public void reset(char initialTurn) {
        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nColumns; j++) {
                this.grid[i][j] = ' ';
            }
        }
        this.turn = initialTurn;
        this.nMarks = 0;
        this.gameState = TicTacToeEnum.IN_PROGRESS;
    }

    //Getters:
    /**
     * Getter method that returns whose turn it is
     *
     * @return whose turn it is; X or O
     */
    public char getTurn() {
        return this.turn;
    }

    /**
     * Getter method that returns the state the game is in, if its in progress,
     * if x won the game, o won the game or if there was a draw
     *
     * @return State of game whether it's going on, it was completed or there
     * was a draw
     */
    public TicTacToeEnum getGameState() {
        return this.gameState;
    }

    //More public methods:
    /**
     * Code used in findWinner() that outputs who won based on whose turn it is
     * Used to reduce repetition of code in findWinner()
     *
     * @param player whose turn is it currently
     * @return state of game depending on who won the game, X or O.
     */
    public TicTacToeEnum charToEnum(char player) {
        if (player == 'O') {
            return TicTacToeEnum.O_WON;
        }
        return TicTacToeEnum.X_WON;
    }

    /**
     * This method takes a turn in the game, but checks if input is valid, then
     * prints X or O on the space, checks if someone won, changes whose turn it
     * is, and checks if there was a draw.
     *
     * @param row the row that the player wants to take their turn on
     * @param column the column the player wants to take their turn on
     * @return state of the game, whether game is still in progress, there's
     * been a draw or if the game is over and someone won.
     */
    public TicTacToeEnum takeTurn(int row, int column) {

        //Checking if input is valid 
        if (column < 0 || column > nColumns || row < 0 || row > nRows) {
            throw new IllegalArgumentException("Invalid square");
        }

        //Check if game is in progress
        if (gameState != TicTacToeEnum.IN_PROGRESS) {
            throw new IllegalArgumentException("Game not in play.");
        }

        //Taking turn
        if (grid[row][column] == ' ') {
            if (turn == 'X') {
                grid[row][column] = turn;
                gameState = findWinner();
                turn = 'O';

            } else {
                grid[row][column] = turn;
                gameState = findWinner();
                turn = 'X';

            }
            nMarks++;
        } else {
            System.out.println("Square already taken");
            return gameState;
        }

        //Check if it's a draw 
        if (nMarks == nRows * nColumns && gameState == TicTacToeEnum.IN_PROGRESS) {
            gameState = TicTacToeEnum.DRAW;
        }

        return gameState;
    }

    //Private Method
    /**
     * Private method that checks each square in each column and row if there 
     * are consecutive Xs or Os equal to numToWins. If numToWin is less than 
     * nRows or nColumns, this method will continuously check if there are 
     * numToWin amount of consecutive Xs or Os along each row or column.
     *
     * @return the state of the game depending on who won, X or O
     */
    private TicTacToeEnum findWinner() {
        int counter = 0;
        //checking columns
        for (int j = 0; j < nColumns; j++) {
            for (int strtPos = 0; strtPos < nRows - numToWin + 1; strtPos++) {
                counter = 1;
                for (int checker = 0; checker < numToWin - 1; checker++) {
                    if (grid[strtPos][j] == this.turn && grid[strtPos + checker][j] == grid[strtPos + checker + 1][j]) {
                        counter++;
                        if (counter == this.numToWin) {
                            return charToEnum(turn);
                        }
                    }
                }
            }
        }

        //checking rows 
        for (int i = 0; i < nRows; i++) {
            for (int strtPos = 0; strtPos < nColumns - numToWin + 1; strtPos++) {
                counter = 1;
                for (int checker = 0; checker < numToWin - 1; checker++) {
                    if (grid[i][strtPos] == this.turn && grid[i][strtPos + checker] == grid[i][strtPos + checker + 1]) {
                        counter++;
                        if (counter == this.numToWin) {
                            return charToEnum(turn);
                        }
                    }
                }
            }
        }

        return TicTacToeEnum.IN_PROGRESS;
    }

    /**
     * Converts grid into a string that can be outputted
     *
     * @return grid in string form
     */
    @Override
    public String toString() {
        String s = new String();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                s += grid[i][j] + " | ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Main method with code given by the Professor to play the game.
     *
     * @param args not used
     */
    public static void main(String args[]) {
        TicTacToe game = new TicTacToe('X');
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(game.toString());
            System.out.println(game.getTurn()
                    + ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;
            scanner.nextLine();
            game.takeTurn(row, column);

        } while (game.getGameState() == TicTacToeEnum.IN_PROGRESS);
        System.out.println(game.getGameState());
    }
}
