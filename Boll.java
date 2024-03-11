import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Boll extends Sprite{

    private Color color;
    private float BallX = 2; 
    private float BallY = 3;
    private int unitX = 1;
    private int unitY = 1;


    public Boll(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;

    }

    @Override
    public void update(Keyboard keyboard) {
        
        if (keyboard.isKeyDown(Key.Space)) {
            if (keyboard.isKeyDown(Key.Left)) {
                setX(getX()-5);
            }
            if (keyboard.isKeyDown(Key.Right)) {
                setX(getX()+5);
            }
            if (keyboard.isKeyDown(Key.Up)) {
                setY(getY()-5);    
            }
            if (keyboard.isKeyDown(Key.Down)) {
                setY(getY()+5);
            }
        } else{
            setX((int)(getX()+BallX*unitX));
            setY((int)(getY()+BallY*unitY));
            
            if(getX() <= Const.leftWALL || getX()>= Const.rightWALL){
                BallX = -BallX;
            }
            if(getY() <= Const.topWALL){
                BallY = -BallY;
            }
        }

    }
    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillOval ((int) getX(), getY(), getWidth(), getHeight());
    }

    public float getBallX() {
		return BallX;
	}

	public void setBallX(float BallX) {
		this.BallX = BallX;
	}

	public float getBallY() {
		return BallY;
	}

	public void setBallY(float BallY) {
		this.BallY = BallY;
	}

    public Rectangle collide(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void bounce(){
        BallY = -BallY;
    }
}