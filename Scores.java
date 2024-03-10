import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Scores extends JFrame implements ActionListener{
    
    private JPanel Latestrun_panel;
    private JPanel HighScore_panel;

    private JList<HighScore> highscore_list;
    private DefaultListModel<HighScore> highscore;

    private Latestrun LS;

    private JButton restart;
    private GameBoard board;

    public Scores(GameBoard board){
        this.board = board;
        this.highscore = new DefaultListModel<>();
        highscore_list = new JList<>(highscore);
        restart = new JButton("restart");

        setLayout(new GridLayout(3, 1));
        HighScore_panel = new JPanel();
        HighScore_panel.setBackground(new Color(0,0,255));
        HighScore_panel.add(new JLabel("Highscore"), BorderLayout.NORTH);
        HighScore_panel.add(highscore_list, BorderLayout.CENTER);
        HighScore_panel.setFocusable(false);
        add(HighScore_panel);

        Latestrun_panel = new JPanel();
        Latestrun_panel.setBackground(new Color(0,255,0));
        Latestrun_panel.add(new JLabel("Latestruns"), BorderLayout.NORTH);
        Latestrun_panel.add(LS.getLastrun(), BorderLayout.CENTER);
        add(Latestrun_panel);

        restart.addActionListener(this);
        restart.setBackground(new Color(255,255,255));
        add(restart);

        setSize(300,600);
        setLocation(800,0);
        setFocusable(false);
        setVisible(true);
        }

        public void actionPerformed(ActionEvent e){
            if(e.getSource() == restart){
                board.Restart( new Game(board, this));
            }
        }

        public void addHS(String name, int score){
            HighScore newScore = new HighScore(name, score);
            highscore.addElement(newScore);
            sortera();
            if(highscore.size()>10){
                highscore.remove(9);
            }
        }

        public void sortera(){
            ArrayList<HighScore> tmp = new ArrayList<>();
            for(int i = 0; i <highscore.size(); i++){
                tmp.add(highscore.getElementAt(i));
            }
            Collections.sort(tmp, Comparator.comparingInt(HighScore::getScore).reversed());
            highscore.clear();
            for(HighScore newScore : tmp){
                highscore.addElement(newScore);
            }
        }

    }
