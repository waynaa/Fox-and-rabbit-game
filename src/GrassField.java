package src;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class GrassField extends JPanel {
    // GAME SETTINGS
    private int CELL = 20; // 20 x 20
    private int PADDING = 2; // 2 Cell ( padding simula sa unang box ng cell)
    private int GRID_SIZE = CELL + PADDING * 2; // times two kase 2 dimensional, from left to right && top to bottom
    private JLayeredPane[][] tile = new JLayeredPane[GRID_SIZE][GRID_SIZE];
    private int PIXEL_SIZE = 22;

    // GAME CONSTANT
    public int NUMBER_RABBIT = 3;


    public GrassField(){
          setLayout(new GridLayout(GRID_SIZE,GRID_SIZE,0,0));
          setBorder(new LineBorder(Color.WHITE, 4, true));
          place_grass();
    }

    public void place_grass(){
        for (int row = 0; row < tile.length; row++) {
            for (int col = 0; col < tile[row].length; col++)
            {
                tile[row][col] = new JLayeredPane();
                tile[row][col].setBounds(0,0,PIXEL_SIZE,PIXEL_SIZE);
                JLabel grass = new JLabel();
                if((row == 1 && col != 0 && col != GRID_SIZE - 1) // TOP TREES [[ Minus 1 kase start ng array is 0 ]]
                        || (row == GRID_SIZE - PADDING && col != 0 && col != GRID_SIZE - 1) // BOTTOM TREES [[ GRID minus PADDING kase kinuha dito yun position ng trees]]
                        || (col == 1 && row != 0 && row != GRID_SIZE - 1) // LEFT TREES
                        || (col == GRID_SIZE - PADDING && row != 0 && row != GRID_SIZE - 1) // RIGHT TREES
                ){
                    JLabel tree = new JLabel();
                    tree.setIcon(new ImageIcon("assets/tree.png"));
                    tree.setBounds(0,0,PIXEL_SIZE,PIXEL_SIZE);
                    grass.setIcon(new ImageIcon("assets/grass.png"));
                    tile[row][col].add(tree, JLayeredPane.DRAG_LAYER);
                    grass.setBounds(0,0,PIXEL_SIZE,PIXEL_SIZE);
                    tile[row][col].add(grass);
                    add(tile[row][col]);
                }else{
                    grass.setIcon(new ImageIcon("assets/grass.png"));
                    grass.setBounds(0,0,PIXEL_SIZE,PIXEL_SIZE);
                    tile[row][col].add(grass);
                    add(tile[row][col]);
                }
            }
        }
    }

    public void posCharacter(JLabel chr, int x, int y){
        tile[y][x].add(chr, JLayeredPane.DRAG_LAYER);
    }

    public void checkIfRabbitWasEaten(JLabel chr,int x, int y, JTextField _eaten){
        if(chr instanceof Fox){
            if(tile[y][x].getComponentCount() == 3){
                ((Fox) chr).removeRabbitListener((Rabbit) tile[y][x].getComponent(0));
                removeCharacter(x,y,0);
                ((Fox) chr).setEATEN_RABBITS(((Fox) chr).getEATEN_RABBITS()+1);
                _eaten.setText(String.valueOf(((Fox) chr).getEATEN_RABBITS()));
            }
        }
    }

    public void removeCharacter(int x, int y, int index){
        tile[y][x].remove(index);
        tile[y][x].revalidate();
        tile[y][x].repaint();
    }

    public boolean isOccupied(int x, int y){
        int size = tile[y][x].getComponentCount(); // get the size of panel
        if(size >= 2){
            return true;
        }else{
            return false;
        }
    }


    public int getNUMBER_RABBIT() {
        return NUMBER_RABBIT;
    }

    public void setNUMBER_RABBIT(int NUMBER_RABBIT) {
        this.NUMBER_RABBIT = NUMBER_RABBIT;
    }

}
