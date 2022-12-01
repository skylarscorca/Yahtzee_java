import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;
import java.io.Serializable;

public class Dice implements Serializable{
    private Die [] dice;
    transient DicePanel panel;

    static String [] iconPaths = {
        "Face_0.png",
        "Face_1.png",
        "Face_2.png",
        "Face_3.png",
        "Face_4.png",
        "Face_5.png",
        "Face_6.png",
    };

    public Dice(){
        dice = new Die [5]; //there should be five
        for (int i = 0; i < 5; ++i){
            dice[i] = new Die();
        }
        panel = new DicePanel();
    }

    public void copy(Dice rhs){
        for (int i = 0; i < 5; ++i){
            dice[i] = rhs.dice[i];
        }
    }

    public int getValue(int die){
        return dice[die].getValue();
    }

    //need to add Dice functions like roll_dice and hold_die
    public void roll_dice(){
        for(int i = 0; i < 5; i++){
            if(!panel.dice_buttons[i].isSelected()){
                dice[i].roll();
            }
        }
    }

    class Die implements Serializable{
        private int value;

        public Die(){
            value = 0;
        }

        public int getValue(){
            return value;
        }

        void roll(){
            value = ThreadLocalRandom.current().nextInt(1, 7);
        }
    }

    class DicePanel extends JPanel {
        private JToggleButton dice_buttons[];

        public DicePanel(){
            dice_buttons = new JToggleButton[5];

            for(int i = 0; i < 5; i++){
                ImageIcon imageIcon = new ImageIcon(getClass().getResource(iconPaths[0])); // load the initial image to a imageIcon
                Image image = imageIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                Icon face = new ImageIcon(newimg);  // transform it back

                dice_buttons[i] = new JToggleButton(face);

                add(dice_buttons[i]);
            }
        }

        void update_dice_buttons(){
            for(int i = 0; i < 5; i++){
                ImageIcon imageIcon = new ImageIcon(getClass().getResource(iconPaths[dice[i].getValue()])); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                Icon face = new ImageIcon(newimg);  // transform it back

                dice_buttons[i].setIcon(face);
            }
        }
    }
}
