import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Sprite {
	private int x, y, width, height;

	private Color color;
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public Color getColor() { return this.color; }
	public void setX(int x) { this.x = x; };	
	public void setY(int y) { this.y = y; };
	public void setWidth(int width) { this.width = width; };
	public void setHeight(int height) { this.height = height; };
	public void setColor(Color color) { this.color = color; };

	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public abstract void update(Keyboard keyboard);
	public abstract void draw(Graphics2D graphics);
}
