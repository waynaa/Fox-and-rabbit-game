package src;
import javax.swing.*;
import java.util.Random;

public class Rabbit extends JLabel implements RabbitInterface{
    private ImageIcon profile_pic = new ImageIcon("assets/rabbit.png");
    private int x = 10;
    private int y = 10;
    private GrassField grassField;

    @Override
    public void foxMove() { // kapag gumalaw yung fox mag dedession yung indivdual rabbits kung mag momove ba sila
        if(shallIMove()){
            generateRandomMove();
        }
    }

    public boolean shallIMove(){ // costumizable, random true or false, pwede ding if malapit yung distance
        Random random = new Random();
        return random.nextBoolean();
    }

    public void generateRandomMove(){
        // x-1 x+1 ; y+1 y-1
            Random random = new Random();
            int move = random.nextInt(4);
            int _x = this.x;
            int _y = this.y;

            switch (move){
                case 0:
                    _x++;
                    break;
                case 1:
                    _x--;
                    break;
                case 2:
                    _y++;
                    break;
                case 3:
                    _y--;
                    break;
            }


            if(!grassField.isOccupied(_x, _y)  ){
                grassField.removeCharacter(x,y,0);
                x = _x;
                y = _y;
                grassField.posCharacter(this,x,y);
            }

    }

    public void setGrassField(GrassField grassField){
        this.grassField = grassField;
    }

    public Rabbit(int x,int y){
        this.x = x;
        this.y = y;
        setIcon(profile_pic);
        setBounds(0,0,22,22);
    }
}
