import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Score extends JFrame implements ActionListener{
    
    private JPanel Latestrun_panel;
    private JPanel HighScore_panel;

    private Latestrun LS;
    private HighScore HS;

    private JButton restart;
    private GameBoard board;

    public Score(GameBoard board){
        this.board = board;

        restart = new JButton("restart");
        LS = new Latestrun();
        HS = new HighScore();

        setLayout(new GridLayout(3, 1));
        HighScore_panel = new JPanel();
        HighScore_panel.setBackground(new Color(0,0,255));
        HighScore_panel.add(new JLabel("Highscore"), BorderLayout.NORTH);
        HighScore_panel.add(HS.getHighscore_list(), BorderLayout.CENTER);
        HighScore_panel.setFocusable(false);
        add(HighScore_panel);

        Latestrun_panel = new JPanel();
        Latestrun_panel.setBackground(new Color(0,255,0));
        Latestrun_panel.add(new JLabel("Latestruns"), BorderLayout.NORTH);
        Latestrun_panel.add(LS.getLastrun(), BorderLayout.CENTER);
        Latestrun_panel.setFocusable(false);
        add(Latestrun_panel);

        restart.addActionListener(this);
        restart.setBackground(new Color(255,255,255));
        add(restart);
        
        setSize(300,600);
        setLocation(800,0);
        setVisible(true);
        }

        public void actionPerformed(ActionEvent e){
            if(e.getSource() == restart){
                board.Restart( new Game(board, this));
            }
        }
        
        public Latestrun getLS(){
            return LS;
        }

        public void setLS(Latestrun LSs){
            LS = LSs;
        }

        public HighScore getHS(){
            return HS;
        }

        public void setHS(HighScore HSs){
            HS = HSs;
        }
}
