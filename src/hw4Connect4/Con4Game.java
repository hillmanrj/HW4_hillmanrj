package hw4Connect4;

/**
 * Start of Solution for Connect 4 game implementation
 * Assignment 5 of CSIT 150
 *  @author Catherine Anderson
 * created Spring 2013
 * revised Spring 2014
 *  * 
 * This code provides the bare bones game class needed to implement the
 * first part of the connect four assignment
 *
 */

public class Con4Game {

    private int rows;
    private int cols;
    private int[][] gameArray; // array that indicated whcih palyers tokens 
    // are in which column
    private int currentPlayer;
    private int dimensions;
    private Connect4GameBoardDisplay size;

    Con4Game(int r, int c)
    {
        rows = r;
        cols = c;

        setGameArray(new int[rows][cols]);
        for(int row =0; row < rows; row++)
        {   for(int col =0; col < cols; col++)
        {   getGameArray()[row][col] = -1;  // -1 indicated no token present
        }
        }
        currentPlayer = 0;
    }


    public void makeMove(int c)
    {
        for(int row = 0; row < rows; row++)
        {   if( getGameArray()[row][c] == -1)
        { getGameArray()[row][c] = currentPlayer;
            break;
        }
        }
        nextPlayer();
    }


    public int getToken(int r, int c)
    {
        return getGameArray()[r][c];
    }


    public void nextPlayer()
    {
        currentPlayer++;
        currentPlayer %= 2;
    }

    /**
     * @return the currentPlayer
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }


    public int[][] getGameArray() {
        return gameArray;
    }


    public void setGameArray(int[][] gameArray) {
        this.gameArray = gameArray;
    }

}
