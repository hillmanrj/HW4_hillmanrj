package hw4Connect4;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Created by Reiley Hillman on 4/10/2017.
 * Used CheckersWindow
 */
public class Connect4Window extends JFrame{

    int titleBarHeight = 20;
    private int win_width = 400;
    private int win_height = 300;
    int c = 8;
    private Connect4GameBoardDisplay panel;
    Con4Game board;
    JTextField rowCountTxt;

    /**
     * constructor
     */
    public Connect4Window()
    {
        this.setTitle("Connect 4");
        board = new Con4Game(4,4);
        this.setSize(win_width,win_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initWind();
        this.setVisible(true);
    }

    /**
     * initializes the components under the game board.
     */
    public void initWind()
    {
        panel = new Connect4GameBoardDisplay(8);
        this.add(panel, BorderLayout.CENTER);
    }


    /**
     *  main method
     * @param args
     */
    public static void main(String[] args)
    {
        Connect4Window cw = new Connect4Window();
    }



}

