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

public class Dice{
    //JToggleButton dice[];

    Die [] dice;
    DicePanel panel;

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
        panel.update_panel();
    }

    //need to add Dice functions like roll_dice and hold_die
    public void roll_dice(){
        for(Die die : dice){
            die.roll();
        }
    }

    class Die{
        private int value;
        private boolean held;

        public Die(){
            value = 0;
            held = false;
        }

        public int getValue(){
            return value;
        }

        void toggle_hold(){
            held = !held;
        }

        void roll(){
            if (!held){
                value = ThreadLocalRandom.current().nextInt(1, 7);
            }
        }

    }

    class DicePanel extends JPanel {
    

        void update_panel(){
            setLayout(new FlowLayout());

            for (Die die : dice){

                ImageIcon imageIcon = new ImageIcon(getClass().getResource(iconPaths[die.getValue()])); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                Icon face = new ImageIcon(newimg);  // transform it back

                add(new JToggleButton(face));
                // we'll need to keep these in a arr like you had it so we can reference them, also unsure as to
                // whether to have each die contain the toggle button and they're all added to the dice panel or
                // just to keep die as a data class
            }
        }

    }

}
