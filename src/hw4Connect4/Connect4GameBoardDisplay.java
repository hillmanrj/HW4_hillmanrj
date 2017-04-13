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
    Color[] colors, chColors;
    Con4Game game;
    int offset = 5;
    int titleOffSet = 17;
    int [] selectedCell;
    Font font;

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
    public int[] calculteRowCol(int x, int y)
    {
        int col = (x - x%cellWidth)/cellWidth;
        int row = (y - y%cellWidth)/cellWidth;
        int[] coor = { row, col};
        return coor;
    }

    /**
     * sends the selected cell to the game
     * @param me
     */
    public void pieceSelected(MouseEvent me)
    {
        selectedCell = calculteRowCol(me.getX(), me.getY());
        game.getToken(selectedCell[0], selectedCell[1]);

    }

    /**
     * sends the selected "new" location to the game.
     * @param me
     */
    public void pieceMoved(MouseEvent me)
    {
        selectedCell = calculteRowCol(me.getX(), me.getY());
        game.makeMove(selectedCell[0]);
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
        for(int row = 0; row < dimensions; row++)
        {
            int count = 0;
            for(int col = 0; col < dimensions; col++)
            {
                if(row%2==0 && col==0)
                    count =1;
                count ++;
                g.setColor(colors[count%2]);
                g.fillRect(cellWidth*col, cellWidth*row, cellWidth, cellWidth);
                g.setColor(Color.white);
                g.drawRect(cellWidth*col, cellWidth*row, cellWidth, cellWidth);
            }
        }

        //drawing tokens
        g.setFont(font);
        for(int row = 0; row < dimensions; row++)
        {
            int count = 0;
            for(int col = 0; col < dimensions; col++)
            {
                if(game.getGameArray()[row][col] == -1)
                    continue;
                else
                {
                    g.setColor(colors[game.getCurrentPlayer()]);
                    g.fillOval(cellWidth*col+offset, cellWidth*row+2*offset, cellWidth-(2*offset), cellWidth-(4*offset));

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
