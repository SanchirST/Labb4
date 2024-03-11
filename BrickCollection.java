import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BrickCollection{

    private ArrayList<Brick> bricks;

    public BrickCollection(int x, int y, int width, int height) {
        bricks = new ArrayList<Brick>();
        BrickAddons();
        }

    public void BrickAddons(){
        for( int i = Const.noll; i < Const.col; i++){
            for( int j = Const.noll; j < Const.row; j++){
                bricks.add(new Brick(Const.brickX+i*80, Const.brickY+j*45, Const.brickWIDTH, Const.brickHEIGHT, Color.blue, Const.life));
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
        if (i >= Const.noll && i < bricks.size()) {
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
