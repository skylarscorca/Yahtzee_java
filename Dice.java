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
        "Face_1.png",
        "Face_2.png",
        "Face_3.png",
        "Face_4.png",
        "Face_5.png",
        "Face_6.png",
    };

    public Dice(){
        dice = new Die [6];
        for (int die = 0; die < 6; die++){
            dice[die] = new Die();
        }
        panel = new DicePanel();
        panel.update_panel();
    }

    //need to add Dice functions like roll_dice and hold_die

    class Die{
        public int value;
        boolean held;

        public Die(){
            value = 0;
            held = false;
        }

        void toggle_hold(){
            held = !held;
        }

        void roll(){
            if (!held){
                value = ThreadLocalRandom.current().nextInt(0, 6);
            }
        }

    }

    class DicePanel extends JPanel {
    

        void update_panel(){
            setLayout(new FlowLayout());

            for (Die die : dice){

                ImageIcon imageIcon = new ImageIcon(getClass().getResource(iconPaths[die.value])); // load the image to a imageIcon
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
