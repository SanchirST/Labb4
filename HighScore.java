import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class HighScore {
    private JList<String> highscore_list;
    private DefaultListModel<String> highscore_model;

    public HighScore() {
        highscore_model = new DefaultListModel<>();
        highscore_list = new JList<>(highscore_model);
    }

    public void addHs(int score, String name){
            highscore_model.addElement(name+ ": "+score);
            sortera(highscore_model);
            if(highscore_model.size()>10){
                highscore_model.remove(10);
            }
        }

    public void sortera(DefaultListModel<String> model) {
    ArrayList<String> tmp = new ArrayList<>();
    for (int i = 0; i < model.size(); i++) {
        tmp.add(model.getElementAt(i));
    }
    Collections.sort(tmp, new Comparator<String>() {
        public int compare(String s1, String s2){
                return extractInt(s2)-extractInt(s1);
        }
        int extractInt(String s){
            String num = s.replaceAll("\\D", "");
            return num.isEmpty() ? 0: Integer.parseInt(num);
        }
    });
    model.removeAllElements();
    for(String s : tmp){
        highscore_model.addElement(s);
    }
}

public DefaultListModel<String> getHigh_score_model() {
	return highscore_model;
}
public void setHigh_score_model(DefaultListModel<String> highscore_model) {
	this.highscore_model = highscore_model;
}
public JList<String> getHighscore_list() {
	return highscore_list;
}
public void setHigh_score_list(JList<String> highscore_list) {
	this.highscore_list = highscore_list;
}
}
