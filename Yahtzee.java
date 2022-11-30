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
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.Component;

public class Yahtzee
{

   public static void main( String args[] )
   { 
      JFrame frame = new JFrame("Yahtzee");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      YahtzeePanel yp = new YahtzeePanel(); 
      frame.add(yp);
      frame.setSize(700, 500); // set frame size
      frame.setVisible(true); // display frame
   }
}

class YahtzeePanel extends JPanel implements ActionListener
{
    private Scoresheet scoresheet;
    private Dice dice;
    private Integer turn, round;
    private JButton roll, play;

    public YahtzeePanel()
    {
        //start_game();
        scoresheet = new Scoresheet(2);
        turn = 3;
        round = 0;
        roll = new JButton("ROLL");
        play = new JButton("PLAY");

        setLayout(new FlowLayout());
        add(scoresheet.panel);
        start_round();
    }

    //only updates dice and buttons, not scorecards
    public void start_round()
    {
        dice = new Dice();
        add(dice.panel);
        play.setEnabled(false);
        add(roll);
        add(play);
        round++;
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}