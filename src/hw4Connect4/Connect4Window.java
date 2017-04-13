package hw4Connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Reiley Hillman on 4/10/2017.
 */
public class Connect4Window {

    int titleBarHeight = 20;
    Con4Game board;
    JTextField rowCountTxt;

    /**
     * constructor
     */
    public Connect4Window()
    {
        this.setTitle("Connect 4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new Con4Game(4,4);
        int width = board.getSize().width;
        int height = board.getSize().height+titleBarHeight+80;
        this.setSize(width,height);
        this.add(board, BorderLayout.CENTER);
        initButs();
        this.setVisible(true);
    }

    /**
     * initializes the components under the game board.
     */
    public void initButs()
    {
        JPanel butPan = new JPanel(new GridLayout(2,1));
        JPanel topPan = new JPanel();
        JPanel botPan = new JPanel();

        butPan.add(topPan);
        butPan.add(botPan);

        JLabel rowCountLab = new JLabel("cells per side: ");
        topPan.add(rowCountLab);
        rowCountTxt = new JTextField("8");
        topPan.add(rowCountTxt);
        JButton rowCountBut = new JButton("Set new row-columns");
        botPan.add(rowCountBut);

        rowCountBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent me) {
                rowButClicked(me);
            }
        });

        this.add(butPan, BorderLayout.SOUTH);
    }

    /**
     * action to be performed if button is clicked to change the number of rows
     * on game board
     * @param me
     */
    public void rowButClicked(MouseEvent me)
    {
        board.setDimensions(Integer.parseInt(rowCountTxt.getText()));

    };

    /**
     *  main method
     * @param args
     */
    public static void main(String[] args)
    {
        Connect4Window cw = new Connect4Window();
    }



}

