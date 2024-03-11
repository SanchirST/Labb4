import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.*;

import javax.swing.JOptionPane;

public class Game {

	//Game details
	private int Lives = 3;
	private int Score;
	private String name = "";
	private Score S;

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

	Boll boll;
	Bat bat;

	private final ArrayList<Sprite> sprites;
	private BrickCollection brickCollecton;

	
	public Game(GameBoard board, Score S) {
		this.S = S;
		random = new Random();
		sprites = new ArrayList<Sprite>();
		boll = new Boll(Const.BollposX, Const.BollposY,Const.BollWidth,Const.BollHeight, Color.white);
		bat = new Bat(Const.playerXpos, Const.playerYpos, Const.playerWidth, Const.playerHeight, Color.red);
		brickCollecton = new BrickCollection(Const.noll,Const.noll,Const.noll,Const.noll);

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
		for (int i = Const.noll; i < brickCollecton.getSize(); i++) {
			if(brickCollecton.get(i).collide().intersects(boll.collide())){
				if(random.nextDouble()<0.5){
					boll.bounce();
					Score ++;
				}
				if (brickCollecton.get(i).getlife() <=Const.ett ) {
					brickCollecton.removeBrick(i);
                    boll.bounce();
					Score ++;
			    }
				break;
	        }
        }
	}

	public void lostBall(){
		if(boll.getY()>=Const.death){
			Lives--;
			if(Lives!=Const.noll){
				boll = new Boll(Const.BollposX, Const.BollposY,Const.BollWidth,Const.BollHeight, Color.white);
			} else {
				lose = true;
				pause = true;
			while(name.length()!=Const.max){
				name = JOptionPane.showInputDialog("Enter your name(MAX 3)");
			}
			name = name.toUpperCase();
			S.getHS().addHs(getScore(), name);
			S.getLS().addLs(getScore());
		}
	}
}
	
	public void winBall(){
		if (brickCollecton.getSize() == Const.noll){
			win = true;
			pause = true;
			while(name.length()!=Const.max){
				name = JOptionPane.showInputDialog("Enter your name MAX 3");
			}
			name = name.toUpperCase();
			S.getHS().addHs(getScore(), name);
			S.getLS().addLs(getScore());
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

	public void setScore(int Score){
		this.Score = Score;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

}