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
import javax.swing.ButtonGroup;
import java.io.Serializable;

public class Scoresheet implements Serializable{
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
    transient int [] prospective;
    int players;
    Dice handler_dice;
    YahtzeePanel ypanel;
    transient ScoresheetPanel panel;

    static String [] scoringCategories = {
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

    public Scoresheet(int player_count, Dice dice, YahtzeePanel yahtzeePanel){
        players = player_count;
        handler_dice = dice;
        ypanel = yahtzeePanel;
        scores = new int [player_count][13];
        prospective = new int[] { -1 , -1 };
        // unsure of how to do prospective scores, maybe that's another arr?
        reset_scores();
        panel = new ScoresheetPanel();
        panel.update_panel();
    }

    public void copy(Scoresheet rhs){
        players = rhs.players;
        scores = new int [players][13];

        for (int player = 0; player < players; player++){
            for (int score = 0; score < 13; score++){
                scores[player][score] = rhs.scores[player][score];
            }
        }

        handler_dice.copy(rhs.handler_dice);
    }
 
    void reset_scores(){
        for (int player = 0; player < players; player++){
            for (int score = 0; score < 13; score++){
                scores[player][score] = -1;
            }
        }
    }

    public int compute_score(Category cat, Dice dice){
        int score = 0;
        int [] counts = new int [6];
        boolean [] present = new boolean [7];
        int total = 0;

        switch(cat){
            case ACES: 
                for(int i = 0; i < 5; i++){
                    if(dice.getValue(i) == 1){
                        score += 1;
                    }
                }
                break;
            case TWOS: 
                for(int i = 0; i < 5; i++){
                    if(dice.getValue(i) == 2){
                        score += 2;
                    }
                }
                break;
            case THREES: 
                for(int i = 0; i < 5; i++){
                    if(dice.getValue(i) == 3){
                        score += 3;
                    }
                }
                break;
            case FOURS: 
                for(int i = 0; i < 5; i++){
                    if(dice.getValue(i) == 4){
                        score += 4;
                    }
                }
                break;
            case FIVES: 
                for(int i = 0; i < 5; i++){
                    if(dice.getValue(i) == 5){
                        score += 5;
                    }
                }
                break;
            case SIXES: 
                for(int i = 0; i < 5; i++){
                    if(dice.getValue(i) == 6){
                        score += 6;
                    }
                }
                break;
            case THREE_KIND: 
                //count dice
                for(int i = 0; i < 5; i++){
                    counts[dice.getValue(i) - 1]++;
                    total += dice.getValue(i);
                }

                //check for three of kind
                for(int i = 0; i < 6; i++){
                    if(counts[i] >= 3){
                        score = total;
                    }
                }
                break;
            case FOUR_KIND: 
                //count dice
                for(int i = 0; i < 5; i++){
                    counts[dice.getValue(i) - 1]++;
                    total += dice.getValue(i);
                }

                //check for three of kind
                for(int i = 0; i < 6; i++){
                    if(counts[i] >= 4){
                        score = total;
                    }
                }
                break;
            case HOUSE: 
                //count dice
                for(int i = 0; i < 5; i++){
                    counts[dice.getValue(i) - 1]++;
                }

                boolean two = false;
                boolean three = false;

                //find house
                for(int i = 0; i < 6; i++){
                    if(counts[i] == 2){
                        two = true;
                    }
                    if(counts[i] == 3){
                        three = true;
                    }
                    if(counts[i] == 5){
                        two = three = true;
                    }
                }

                //check for house
                if(two && three){
                    score = 25;
                }
                break;
            case SMALL: 
                //find dice
                for(int i = 0; i < 5; i++){
                    present[dice.getValue(i)] = true;
                }

                //check for small
                if(present[1] && present[2] && present[3] && present[4]){
                    score = 30;
                }
                else if(present[2] && present[3] && present[4] && present[5]){
                    score = 30;
                }
                else if(present[3] && present[4] && present[5] && present[6]){
                    score = 30;
                }
                break;
            case LARGE: 
                //find dice
                for(int i = 0; i < 5; i++){
                    present[dice.getValue(i)] = true;
                }

                //check for large
                if(present[1] && present[2] && present[3] && present[4] && present[5]){
                    score = 40;
                }
                else if(present[2] && present[3] && present[4] && present[5] && present[6]){
                    score = 40;
                }
                break;
            case YAHTZEE: 
                //count dice
                for(int i = 0; i < 5; i++){
                    counts[dice.getValue(i) - 1]++;
                }

                //check for yahtzee
                for(int i = 0; i < 6; i++){
                    if(counts[i] == 5){
                        score = 50;
                    }
                }
                break;
            case CHANCE: 
                for(int i = 0; i < 5; i++){
                    score += dice.getValue(i);
                }
                break;
        }
    
        return score;
    }

    public void play(){
        prospective = new int [] {-1, -1};
        panel.update_panel();
    }

    // for actually calculating the scores, initially I was thinking of doing a strategy pattern kind
    // of thing where each category has an interface, but I think it might be easier to just have a for loop
    // and a switch of the category index that returns the score for that category in one function
    // the function would take in an int[6] or maybe a Dice object would be easier

    class ScoresheetPanel extends JPanel implements ActionListener {
        private ButtonGroup group;
        JToggleButton [] cat_buttons;

        ScoresheetPanel(){
            update_panel();
        }

        void update_panel(){
            this.removeAll();

            this.setLayout(new GridLayout(14, players + 1));

            this.add(new JLabel(""));
            for (int player = 0; player < players; player++){
                this.add(new JLabel("Player " + (player + 1)));
            }

            for (int catagory = 0; catagory < 13; catagory++){
                CategoryToggleButton new_button = new CategoryToggleButton(scoringCategories[catagory]);
                new_button.addActionListener(this);
                new_button.setCatagory(catagory);
                this.add(new_button);
                for (int player = 0; player < players; player++){
                    JLabel label = new JLabel( scores[player][catagory] >= 0 ? "" + scores[player][catagory] : "" );

                    if ( prospective[0] == player && prospective[1] == catagory ){
                        label.setForeground(Color.RED);    
                    }

                    this.add(label);
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

        public void actionPerformed(ActionEvent e){
            int curPlayer = ypanel.getCurPlayer();

            CategoryToggleButton pressed = (CategoryToggleButton)e.getSource();

            ypanel.set_playable(true);

            System.out.println(pressed.getCatagory().toString() + " " + pressed.getCatagory().ordinal());

            if ( prospective[0] != -1){ scores [ prospective[0] ][ prospective[1] ] = -1; }

            if ( scores [ypanel.getCurPlayer() - 1][pressed.getCatagory().ordinal()] < 0){
                scores [ypanel.getCurPlayer() - 1][pressed.getCatagory().ordinal()] = compute_score(pressed.getCatagory(), handler_dice);
                prospective[0] = ypanel.getCurPlayer() - 1;
                prospective[1] = pressed.getCatagory().ordinal();
            };

            update_panel();
        }
    
    }

    class CategoryToggleButton extends JToggleButton{
        private Category cat;

        CategoryToggleButton(String s) { super(s); }

        public Category getCatagory(){ return cat; }

        public void setCatagory(Category catagory){ cat = catagory; }

        public void setCatagory(int catagory){ cat = Category.values()[catagory]; }
    }

}
