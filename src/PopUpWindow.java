package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpWindow extends JFrame {
    private JLabel title;
    private JButton btn;

    public PopUpWindow(){
        setSize(200,150);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);

        JPanel main = new JPanel(new GridBagLayout());
        title = new JLabel();
        btn = new JButton("Continue");
        btn.setSize(200,200);
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 0.1;
        gc.weightx = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        main.add(title,gc);
        gc.gridx = 0;
        gc.gridy = 1;
        main.add(btn,gc);

        add(main);
    }
    public void setTextOnTitle(String text){
        title.setText(text);
    }

    public void actionListener(ActionListener listener){
        btn.addActionListener(listener);
    }

}
