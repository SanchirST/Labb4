import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Brick extends Sprite{
    private Color color;
    private int life;
    
    public Brick(int x, int y, int width, int height, Color color, int life) {
        super(x, y, width, height);
        this.color = color;
        this.life = life;
    }

    @Override
    public void update(Keyboard keyboard) {
        if(life == 1){
            color = Color.green;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public int getlife() {
        return life--;
    }

    public void setlife(int life){
        this.life = life;
    }   
    public Rectangle collide(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Brick get(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}
