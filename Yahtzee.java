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

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.io.*;

public class Yahtzee
{
    private static JMenuBar mb;
    private static JMenu options;
    static JMenuItem save, load;

   public static void main( String args[] )
   { 
      JFrame frame = new JFrame("Yahtzee");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //menu bar
        mb = new JMenuBar();
        options = new JMenu("Options");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        options.add(save);
        options.add(load);
        mb.add(options);
        frame.setJMenuBar(mb);

      YahtzeePanel yp = new YahtzeePanel(); 
      save.addActionListener(yp);
      load.addActionListener(yp);
      frame.add(yp);
      frame.setSize(550, 450); // set frame size
      frame.setVisible(true); // display frame
   }
}

class YahtzeePanel extends JPanel implements ActionListener, Serializable
{
    private Scoresheet scoresheet;
    private Dice dice;
    private Integer turn, round;
    private transient JButton roll, play;
    private boolean playable;

    public YahtzeePanel()
    {
        //start_game();
        dice = new Dice();
        scoresheet = new Scoresheet(2, dice, this);
        round = 0;
        roll = new JButton("ROLL");
        roll.addActionListener(this);
        play = new JButton("PLAY");
        play.addActionListener(this);

        buildPanel();
        start_round();
    }

    public void buildPanel(){
        this.removeAll();

        setLayout(new FlowLayout());
        add(scoresheet.panel);
        add(dice.panel);
        add(roll);
        add(play);
    }

    //only updates dice and buttons, not scorecards
    public void start_round()
    {
        play.setEnabled(false);
        round++;
        turn = 0;
    }

    private void save_data(){
        try (FileOutputStream f = new FileOutputStream("game_data.txt");
            ObjectOutputStream s = new ObjectOutputStream(f)) 
        {
            s.writeObject(this);
            s.close();
            System.out.println("Successfully saved game!");
        } catch (IOException error) {
            //error.printStackTrace();
            System.out.println("IO save error: file stream");
        }
    }

    private void load_data(){
        //read data
        YahtzeePanel loaded_panel = null;
        try(FileInputStream in = new FileInputStream("game_data.txt");
            ObjectInputStream s = new ObjectInputStream(in)) {
            loaded_panel = (YahtzeePanel) s.readObject();
            s.close();

            //load into current panel
            this.scoresheet = loaded_panel.scoresheet;
            this.dice.copy(loaded_panel.dice);
            this.turn = loaded_panel.turn;
            this.round = loaded_panel.round;
            this.playable = loaded_panel.playable;

            dice.panel.update_dice_buttons();

            buildPanel();
            scoresheet.panel.update_panel();

            System.out.println("Loaded previous game!");
        } 
        catch (IOException error) {
            //error.printStackTrace();
            System.out.println("IO load error");
        }
        catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("class not found");
        }
    }
    public void set_playable(boolean b){ play.setEnabled(b); }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == roll){
            dice.roll_dice();
            turn++;
            if(turn >= 3){
                roll.setEnabled(false);
            }
            dice.panel.update_dice_buttons();
        }
        else if(e.getSource() == play){
            scoresheet.play();
        }
        else if(e.getSource() == Yahtzee.load){
            load_data();
        }
        else if(e.getSource() == Yahtzee.save){
            save_data();
        }

    }
}