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

public class Scoresheet{

    enum Category{
        ACES,
        TWOS,
        THREES,
        FOURS,
        FIVES,
        SIXES,
        THREE_KIND,
        FOUR_KIND,
        HOUSE,
        SMALL,
        LARGE,
        YAHTZEE,
        CHANCE
    }

    int [][] scores;
    int players;
    ScoresheetPanel panel;
    static String [] scoringCatagories = {
        "Aces",
        "Twos",
        "Threes",
        "Fours",
        "Fives",
        "Sixes",
        "3 of a kind",
        "4 of a kind",
        "Full House",
        "Small Straight",
        "Large Straight",
        "Yahtzee",
        "Chance"
    };

    public Scoresheet(int player_count){

        players = player_count;
        scores = new int [player_count][13];
        // unsure of how to do prospective scores, maybe that's another arr?
        reset_scores();
        panel = new ScoresheetPanel();
        panel.update_panel();
    }
 
    void reset_scores(){
        for (int player = 0; player < players; player++){
            for (int score = 0; score < 13; score++){
                scores[player][score] = 0;
            }
        }
    }

    // for actually calculating the scores, initially I was thinking of doing a strategy pattern kind
    // of thing where each catagory has an interface, but I think it might be easier to just have a for loop
    // and a switch of the catagory index that returns the score for that catagory in one function
    // the function would take in an int[6] or maybe a Dice object would be easier

    class ScoresheetPanel extends JPanel {

        void update_panel(){
            this.setLayout(new GridLayout(14, players + 1));

            this.add(new JLabel(""));
            for (int player = 0; player < players; player++){
                this.add(new JLabel("Player " + (player + 1)));
            }

            for (int catagory = 0; catagory < 13; catagory++){
                this.add(new JLabel(scoringCatagories[catagory]));
                for (int player = 0; player < players; player++){
                    this.add(new JLabel("" + scores[player][catagory]));
                }
            }

            //draw borders
            boolean first = true;
            for (Component component : this.getComponents())
            {
                if(first){
                    first = false;
                    continue;
                }
                JComponent jcomponent = (JComponent)component;
                jcomponent.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

    }

}