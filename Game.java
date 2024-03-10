import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.*;

import javax.swing.JOptionPane;

public class Game {

	//Game details
	private int Lives = 3;
	private int Score;
	private String name;
	private Scores S;
	private HighScore hs;
	private Latestrun ls;

	Font largeFont = new Font("Arial", Font.BOLD, 20);
	Font largerFont = new Font("Arial", Font.BOLD, 40);
	Random random;
	private boolean pause = false;
	private boolean win = false;
	private boolean lose = false;

	public void togglePause() {
        pause = !pause;
    }

    public boolean isPaused() {
        return pause;
    }

	public void toggleWin(){
		win = !win;
	}

	public boolean isWin(){
		return win;
	}

	public void toogleLose(){
		lose = !lose;
	}

	public boolean isLose(){
		return lose;
	}

	//Boll details
	Boll boll;
	private int bollXpos = 400;
	private int bollYpos = 400;
	private int bollWidth = 20;
	private int bollHeight = 20;

	//Player details
	Bat bat;
	private int playerXpos = 300;
	private int playerYpos = 550;
	private int playerWidth = 100;
	private int playerHeight = 10;

	//Bricks
	private final ArrayList<Sprite> sprites;
	private BrickCollection brickCollecton;

	
	public Game(GameBoard board, Scores S) {
		this.S = S;
		hs = new HighScore(name, Score);
		ls = new Latestrun();
		random = new Random();
		sprites = new ArrayList<Sprite>();
		boll = new Boll(bollXpos, bollYpos,bollWidth,bollHeight, Color.white);
		bat = new Bat(playerXpos, playerYpos, playerWidth, playerHeight, Color.red);
		brickCollecton = new BrickCollection(0, 0, 0, 0);

		Wall left = new Wall(0, 0, 5, 800, Color.gray);
		Wall right = new Wall(795, 0, 5, 600, Color.gray);
		Wall top = new Wall(0, 50, 800, 5, Color.gray);
		Wall ex1 = new Wall(700, 15, 40, 20, Color.blue);
		Wall ex2 = new Wall( 600, 15, 40, 20, Color.green);

		sprites.add(left);
		sprites.add(right);
		sprites.add(top);
		sprites.add(ex1);
		sprites.add(ex2);
	}

	
	public void update(Keyboard keyboard) {
			if(!pause){
		for(Sprite sp : sprites){
			sp.update(keyboard);
		}
		boll.update(keyboard);
		bat.update(keyboard);
		brickCollecton.update(keyboard);
		batCollision();
		brickCollision();
		lostBall();
		winBall();
	}
}
//------------------------------------------------------------------------------------
	public void batCollision(){
		if(bat.collide().intersects(boll.collide())){
			boll.bounce();
		}
	}

	public void brickCollision(){
		for (int i = 0; i < brickCollecton.getSize(); i++) {
			if(brickCollecton.get(i).collide().intersects(boll.collide())){
				if(random.nextDouble()<0.5){
					boll.bounce();
					Score ++;
				}
				if (brickCollecton.get(i).getlife() <=1 ) {
					brickCollecton.removeBrick(i);
                    boll.bounce();
					Score ++;
			    }
				break;
	        }
        }
	}

	public void lostBall(){
		if(boll.getY()>=600){
			Lives--;
			if(Lives!=0){
				boll = new Boll(bollXpos, bollYpos, bollWidth, bollHeight, Color.white);
			} else {
				lose = true;
				pause = true;
			S.setName(JOptionPane.showInputDialog("Enter your name"));
			S.setName(S.getName().toUpperCase());
			if(S.getName().length() > 3){
				S.setName(S.getName().substring(0, 3));
			}
			ls.addLs(Score);
		}
			}
		}
	
	public void winBall(){
		if (brickCollecton.getSize() == 0){
			win = true;
			pause = true;

			S.setName(JOptionPane.showInputDialog("Enter your name"));
			S.setName(S.getName().toUpperCase());
			if(S.getName().length() > 3){
				S.setName(S.getName().substring(0, 3));
			}
			ls.addLs(Score);
		}
	}
//------------------------------------------------------------------------------------
	public void draw(Graphics2D graphics) {
		for(Sprite sp : sprites){
			sp.draw(graphics);
		}
		boll.draw(graphics);
		bat.draw(graphics);
		brickCollecton.draw(graphics);
		graphics.setColor(Color.white);
		graphics.drawString("=1 points", 740, 30);
		graphics.drawString("=2 points", 640, 30);
		graphics.setFont(largeFont);
		graphics.drawString("SASSE BREAKOUT", 300, 35);
		graphics.drawString("Score:" +Score, 30, 20);
		graphics.drawString("Lives:" +Lives, 30, 40);
		if(lose){
			graphics.setFont(largerFont);
			graphics.setColor(Color.green);
			graphics.drawString("YOU LOSE", 300, 350);
		}
		if(win){
			graphics.setFont(largerFont);
			graphics.setColor(Color.green);
			graphics.drawString("YOU WIN", 300, 350);
		}
	}
	public int getScore(){
		return Score;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}