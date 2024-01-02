package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Main extends JFrame implements KeyListener {
    private JPanel main, top, bottom, left, right;
    private Fox fox = new Fox();
    private GrassField grassField;
    private JTextField _bunnies, _eaten, _pos;


    public static void main(String[] args) {
        Main game = new Main();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(660,776);
        game.setResizable(false);
        game.setVisible(true);
        game.setLocationRelativeTo(null);


    }

    public Main(){
        tiles_init();
    }

    private void tiles_init(){
        main = new JPanel(new BorderLayout());
        Top();
        Bottom();
        Left();
        Right();


        InitializeGame();
        add(main);
        addKeyListener(this);

    }

    private void InitializeGame(){
        grassField = new GrassField();
        spawnRabbit();
        PlaceFox();
        main.add(grassField);
    }

    private void PlaceFox(){
        while(true){
            int x = new Random().nextInt(22 - 2 + 1) + 2;
            int y = new Random().nextInt(22 - 2 + 1) + 2;
            if(!grassField.isOccupied(x,y)){
                grassField.posCharacter(fox,x,y);
                fox.setX(x);
                fox.setY(y);
                break;
            }
        }
    }

    private void Top(){
        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(150,100));

        JLabel title = new JLabel("Fox vs Rabbit");
        top.add(title);

        main.add(top, BorderLayout.NORTH);
    }

    private void Bottom(){
        bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setPreferredSize(new Dimension(150,100));

        JLabel bunnies = new JLabel("Bunnies in meadow: ");
        _bunnies = new JTextField(8);
        _bunnies.setEditable(false);
        _bunnies.setFocusable(false);


        JLabel eaten = new JLabel("Eaten: ");
        _eaten = new JTextField(8);
        _eaten.setEditable(false);
        _eaten.setFocusable(false);


        JLabel pos = new JLabel("You are in tile: ");
        _pos = new JTextField(8);
        _pos.setEditable(false);
        _pos.setFocusable(false);

        bottom.add(bunnies);
        bottom.add(_bunnies);
        bottom.add(eaten);
        bottom.add(_eaten);
        bottom.add(pos);
        bottom.add(_pos);
        main.add(bottom, BorderLayout.SOUTH);
    }

    private void Left(){
        left = new JPanel();
        left.setBackground(Color.WHITE);
        left.setPreferredSize(new Dimension(50,100));
        main.add(left, BorderLayout.EAST);
    }
    private void Right(){
        right = new JPanel();
        right.setBackground(Color.WHITE);
        right.setPreferredSize(new Dimension(50,100));
        main.add(right, BorderLayout.WEST);
    }

    public void spawnRabbit(){
        System.out.println(grassField.NUMBER_RABBIT + " Rabbits");

        if(grassField.getNUMBER_RABBIT() == 50){
            PopUpWindow popUpWindow = new PopUpWindow();
            popUpWindow.actionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popUpWindow.dispose();
                    main.remove(grassField);
                    InitializeGame();
                    revalidate();
                    repaint();
                }
            });
            popUpWindow.setTextOnTitle("You lose!");
            return;
        }
        if(grassField.getNUMBER_RABBIT() == 0){
            PopUpWindow popUpWindow = new PopUpWindow();
            popUpWindow.actionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popUpWindow.dispose();
                    main.remove(grassField);
                    InitializeGame();
                    revalidate();
                    repaint();
                }
            });
            popUpWindow.setTextOnTitle("You Win!");
            return;
        }
        for(int i = 0; i < grassField.getNUMBER_RABBIT(); i++){
            while(true){
                int x = new Random().nextInt(22 - 2 + 1) + 2;
                int y = new Random().nextInt(22 - 2 + 1) + 2;
                if(!grassField.isOccupied(x,y)){
                    Rabbit rabbit = new Rabbit(x,y);
                    rabbit.setGrassField(grassField);
                    fox.addRabitListener(rabbit);
                    grassField.posCharacter(rabbit,x,y);
                    _bunnies.setText(String.valueOf(grassField.getNUMBER_RABBIT()));
                    break;
                }
            }
        }
        int number_rabbit = grassField.getNUMBER_RABBIT();
        if (number_rabbit * 2 <= 50)
            grassField.setNUMBER_RABBIT(number_rabbit* 2);
        else
            grassField.setNUMBER_RABBIT(number_rabbit + (50 - number_rabbit ));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case 38: // UP
                grassField.removeCharacter(fox._getX(),fox._getY(), 0);
                fox.setY(fox._getY()-1);
                grassField.posCharacter(fox,fox._getX(),fox._getY());
                grassField.checkIfRabbitWasEaten(fox,fox._getX(),fox._getY(), _eaten);
                fox.IncrimentStep();
                fox.foxMove_updateAllTheRabbits();
                _pos.setText("("+fox._getX()+","+fox._getY()+")");
                if(fox.getStep() == 3){
                    spawnRabbit();
                }
                break;
            case 37: // LEFT
                grassField.removeCharacter(fox._getX(),fox._getY(),0);
                fox.setX(fox._getX()-1);
                grassField.posCharacter(fox,fox._getX(),fox._getY());
                grassField.checkIfRabbitWasEaten(fox,fox._getX(),fox._getY(), _eaten);
                fox.IncrimentStep();
                fox.foxMove_updateAllTheRabbits();
                _pos.setText("("+fox._getX()+","+fox._getY()+")");
                if(fox.getStep() == 3){
                    spawnRabbit();
                }
                break;
            case 40: // DOWN
                grassField.removeCharacter(fox._getX(),fox._getY(),0);
                fox.setY(fox._getY()+1);
                grassField.posCharacter(fox,fox._getX(),fox._getY());
                grassField.checkIfRabbitWasEaten(fox,fox._getX(),fox._getY(), _eaten);
                fox.IncrimentStep();
                fox.foxMove_updateAllTheRabbits();
                _pos.setText("("+fox._getX()+","+fox._getY()+")");
                if(fox.getStep() == 3){
                    spawnRabbit();
                }
                break;
            case 39: // RIGHT
                grassField.removeCharacter(fox._getX(),fox._getY(),0);
                fox.setX(fox._getX()+1);
                grassField.posCharacter(fox,fox._getX(),fox._getY());
                grassField.checkIfRabbitWasEaten(fox,fox._getX(),fox._getY(), _eaten);
                fox.IncrimentStep();
                fox.foxMove_updateAllTheRabbits();
                _pos.setText("("+fox._getX()+","+fox._getY()+")");
                if(fox.getStep() == 3){
                    spawnRabbit();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
