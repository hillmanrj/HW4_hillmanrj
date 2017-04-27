package hw4Connect4;


/**
 * CSIS 150
 *
 * Starting of Checkers Game - stores the board information
 * and draws it as a JPanel
 * Cate Anderson
 * Created: 3-13-14
 *
 * display of the game
 *
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Connect4GameBoardDisplay extends JPanel{


    private int cellWidth = 40; //pixel width of a cell
    private int dimensions;//number of rows and col
    int width;
    int height;
    Color[] colors;
    Con4Game game;
    int offset = 5;
    int titleOffSet = 17;
    int [] selectedCell;
    Font font;
    int player;
    String playerName;
    String Player1Name = "Test";
    String Player2Name = "Test2";

    public Connect4GameBoardDisplay(int d)
    {
        dimensions = d;
        colors = new Color[2];
        colors[0] = Color.BLACK;
        colors[1] = Color.BLACK;
        font = new Font("Arial", Font.BOLD, 8);

        width = dimensions * cellWidth;
        height = dimensions *  cellWidth;
        game = new Con4Game(width, height);

        this.setSize(width, height);

        this.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pieceSelected(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pieceMoved(evt);
            }
        });
    }

    /**
     * Get the row and column numbers from the x and y coordinates on JPanel
     * @param x
     * @param y
     * @return
     */
    public int[] calculateRowCol(int x, int y)
    {
        int col = (x - x%cellWidth)/cellWidth;
        int[] coor = {0, col};
        return coor;
    }

    /**
     * sends the selected cell to the game
     * @param me
     */
    public void pieceSelected(MouseEvent me)
    {
        selectedCell = calculateRowCol(me.getX(), me.getY());
        game.getToken(0, selectedCell[1]);

    }

    /**
     * sends the selected "new" location to the game.
     * @param me
     */
    public void pieceMoved(MouseEvent me)
    {
        selectedCell = calculateRowCol(me.getX(), dimensions - 1 );
        game.makeMove(selectedCell[1]);
        repaint();
    }

    /**
     * recalculates all factors when number of rows and columns changes
     */
    public void redimension()
    {
        width = dimensions * cellWidth;
        height = dimensions *  cellWidth;
        this.setSize(width, height);
        game = new Con4Game(width, height);

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing board
        for (int row = 0; row < dimensions; row++) {
            int count = 0;
            for (int col = 0; col < dimensions; col++) {
                g.setColor(colors[count % 2]);
                g.fillRect(cellWidth * col, cellWidth * row, cellWidth, cellWidth);
                g.setColor(Color.white);
                g.drawLine(cellWidth * col, cellWidth * row, cellWidth * col, (cellWidth * row) + cellWidth);
            }
        }

        //drawing tokens
        for (int row = 0; row < dimensions; row++) {
            for (int col = 0; col < dimensions; col++) {
                if (game.getToken(row, col) == 0) {
                    g.setColor(Color.red);
                    g.fillOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);
                    g.setColor(Color.black);
                    g.drawOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);

                if (game.checkforWin(player) == true && playerName.equals(Player2Name)) {
                    g.setColor(Color.black);
                    g.fillOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);
                    g.setColor(Color.green);
                    g.drawOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);

                }
                } else if (game.getToken(row, col) == 1) {
                    g.setColor(Color.blue);
                    g.fillOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);
                    g.setColor(Color.black);
                    g.drawOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);

                    if (game.checkforWin(player) == true && playerName.equals(Player1Name)) {
                        g.setColor(Color.black);
                        g.fillOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);
                        g.setColor(Color.orange);
                        g.drawOval(cellWidth * col, cellWidth * (7 - row), cellWidth, cellWidth);

                    }
                }
            }
        }
    }

    /**
     * @return the cellWidth
     */
    public int getCellWidth() {
        return cellWidth;
    }

    /**
     * @param cellWidth the cellWidth to set
     */
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
        redimension();
    }

    /**
     * @param d rows the rows to set
     */
    public void setDimensions(int d) {
        this.dimensions = d;
        redimension();
    }
}
