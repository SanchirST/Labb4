import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bat extends Sprite{

    private Color color;

    public Bat(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void update(Keyboard keyboard) {

        if(keyboard.isKeyDown(Key.Left)){
            if(getX()>=10){
                setX(getX()-7);
            }
        }

        if(keyboard.isKeyDown(Key.Right)){
            if(getX()<=690){
                setX(getX()+7);
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        graphics.setColor(Color.white);
        graphics.drawString("ST", getX()+45, getY()+10);
    }

    public Rectangle collide(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}