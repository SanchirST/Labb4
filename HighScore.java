import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class HighScore {
    private JList<String> highscore_list;
    private DefaultListModel<String> highscore;

    public HighScore() {
        highscore = new DefaultListModel<>();
        highscore_list = new JList<>(highscore);
    }

    public void addHs(int Score, String name){
            highscore.addElement(name+ ": "+Score);
            sortera();
            if(highscore.size()>10){
                highscore.remove(9);
            }
        }

    public void sortera() {
    ArrayList<String> tmp = new ArrayList<>();
    for (int i = 0; i < highscore.size(); i++) {
        tmp.add(highscore.getElementAt(i));
    }

    Collections.sort(tmp, new Comparator<String>() {
        public int compare(String j1, String j2) {
            int score1 = Integer.parseInt(j1.substring(j1.lastIndexOf(": ") + 1).trim());
            int score2 = Integer.parseInt(j2.substring(j2.lastIndexOf(": ") + 1).trim());
            return Integer.compare(score2, score1);
        }
    });
    highscore.clear();
    highscore.addAll(tmp);
}


    public DefaultListModel<String> getHighscore(){
        return highscore;
    }

    public JList<String> getHighscore_list(){
        return highscore_list;
    }
}
