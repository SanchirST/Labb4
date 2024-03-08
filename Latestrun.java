import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Latestrun {
    private JList<Integer> lastrun;
    private DefaultListModel<Integer> lastrun_model;

    public Latestrun() {
        lastrun_model = new DefaultListModel<>();
        lastrun = new JList<>(lastrun_model);
    }

    public void addLs(int Score){
        lastrun_model.addElement(Score);
        if(lastrun_model.size()>3){
            lastrun_model.remove(0);
        }
    }

    public DefaultListModel<Integer> getLastrun_model(){
        return lastrun_model;
    }
    public void setLastrun_model(DefaultListModel<Integer> lastrun_model){
        this.lastrun_model = lastrun_model;
    }
    public JList<Integer> getLastrun(){
        return lastrun;
    }
    public void setLastrun(JList<Integer> lastrun){
        this.lastrun = lastrun;
    }
}
