package src;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Fox extends JLabel {
    // Character Properties
    private ImageIcon profile_pic = new ImageIcon("assets/fox.png");
    private int x = 10;
    private int y = 10;
    private int step = 0;
    private int EATEN_RABBITS = 0;
    private List<RabbitInterface> rabbitListenerList;


    public Fox() {
        rabbitListenerList = new ArrayList<>();
        setIcon(profile_pic);
        setBounds(0,0,22,22);
    }

    public void addRabitListener(RabbitInterface rabbitInterface){
        rabbitListenerList.add(rabbitInterface);
    }

    public void removeRabbitListener(Rabbit rabbit){
        rabbitListenerList.remove(rabbit);
    }

    public void foxMove_updateAllTheRabbits(){
        for (RabbitInterface rabbitInterface : rabbitListenerList){
            rabbitInterface.foxMove();
        }
    }

    public void setX(int x) {
        if( x >= 2 && x < 22 )
            this.x = x;
    }

    public void setY(int y) {
        if( y >= 2 && y < 22 )
            this.y = y;
    }

    public int _getY() {
        return y;
    }

    public int _getX() {
        return x;
    }

    public int getStep() {
        return step;
    }

    public void IncrimentStep(){
        if (step != 3)
            step++;
        else
            step = 0;
    }

    public int getEATEN_RABBITS() {
        return EATEN_RABBITS;
    }

    public void setEATEN_RABBITS(int EATEN_RABBITS) {
        this.EATEN_RABBITS = EATEN_RABBITS;
    }
}
