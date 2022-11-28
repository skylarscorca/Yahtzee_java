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
      frame.setSize(500, 500); // set frame size
      frame.setVisible(true); // display frame
   }
}

class YahtzeePanel extends JPanel implements ActionListener
{
    private JPanel scorecard1, scorecard2;
    private JToggleButton dice[];
    private JButton roll;
    private JButton play;

    public YahtzeePanel()
    {
        start_game();
    }

    public void start_game()
    {
        //initialize data
        scorecard1 = new JPanel();
        scorecard2 = new JPanel();
        score_init(scorecard1);
        score_init(scorecard2);
        roll = new JButton("Roll");
        roll.addActionListener(this);
        play = new JButton("Play");
        play.addActionListener(this);

        //set up view
        setLayout(new FlowLayout());
        add(scorecard1);
        add(scorecard2);
        start_round();
    }

    //only updates dice and buttons, not scorecards
    public void start_round()
    {
        dice_init();
        for(int i = 0; i < 5; i++){
            add(dice[i]);
        }
        play.setEnabled(false);
        add(roll);
        add(play);
    }

    public void score_init(JPanel panel)
    {
        panel.setLayout(new GridLayout(13, 2));
        panel.add(new JLabel("Aces"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Twos"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Threes"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Fours"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Fives"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Sixes"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("3 of a kind"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("4 of a kind"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Full House"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Small Straight"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Large Straight"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("YAHTZEE"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Chance"));

        //set up borders
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        for (Component component : panel.getComponents())
        {
            JComponent jcomponent = (JComponent)component;
            jcomponent.setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }

    public void dice_init()
    {
        dice = new JToggleButton[5];
        for(int i = 0; i < 5; i++){
            Icon initial_face = new ImageIcon(getClass().getResource("Face_1.png"));
            dice[i] = new JToggleButton(initial_face);
        }
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}