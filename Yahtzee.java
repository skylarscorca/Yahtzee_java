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
    JPanel scorecard1, scorecard2;

    public YahtzeePanel()
    {
        start_game();
    }

    public void start_game()
    {
        scorecard1 = new JPanel();
        scorecard2 = new JPanel();
        score_init(scorecard1);
        score_init(scorecard2);
        setLayout(new FlowLayout());
        add(scorecard1);
        add(scorecard2);
    }

    public void score_init(JPanel panel)
    {
        //panel = new JPanel();
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
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}