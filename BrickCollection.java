import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BrickCollection{

    private ArrayList<Brick> bricks;
    private int col = 10;
    private int row = 4;

    public BrickCollection(int x, int y, int width, int height) {
        bricks = new ArrayList<Brick>();
        BrickAddons();
        }

    public void BrickAddons(){
        for( int i = 0; i < col; i++){
            for( int j = 0; j < row; j++){
                bricks.add(new Brick(7+i*80, 70+j*45, 65, 25, Color.blue, 2));
            }
        }
    }

    public void update(Keyboard keyboard) {
        for( Brick br: bricks){
            br.update(keyboard);
        }
    }

    public void draw(Graphics2D graphics) {
        for( Brick br : bricks){
            br.draw(graphics);
        }
    }

    public void removeBrick(int i) {
        if (i >= 0 && i < bricks.size()) {
            bricks.remove(i);
        }
    }

    public int getSize(){
        return bricks.size();
    }

    public Brick get(int i){
        return bricks.get(i);
}
}
