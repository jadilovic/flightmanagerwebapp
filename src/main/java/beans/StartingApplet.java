package beans;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class StartingApplet extends Applet implements Runnable{

	int x1 = 0;
	Dog d1 = new Dog("first", x1);
	int x2 = 0;
	Dog d2 = new Dog("second", x2);
	int x3 = 0;
	Dog d3 = new Dog("third", x3);
	int x4 = 0;
	Dog d4 = new Dog("fourth", x4);
	int y = 0;
	int sides = 50;
	private Image i;
	private Graphics doubleG;
	boolean finish;
	
	@Override
	public void init() {
		this.setSize(1350, 650);
	}
	
	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		while(x1 < 1200 || x2 < 1200 || x3 < 1200 || x4 < 1200) {
			int move1 = (int) (Math.random() * 5);
			int move2 = (int) (Math.random() * 5);
			int move3 = (int) (Math.random() * 5);
			int move4 = (int) (Math.random() * 5);
			x1 += move1;
			d1.setNum(x1);
			x2 += move2;
			d2.setNum(x2);
			x3 += move3;
			d3.setNum(x3);
			x4 += move4;
			d4.setNum(x4);
			//doMath();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		finish = true;
		repaint();
	}

	@Override
	public void update(Graphics g) {
		if(i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
			g.setColor(Color.CYAN);
			g.fillOval(x1, y, sides, sides);
			g.drawString(d1.getNum() + ", ", x1, y + 60);

			g.setColor(Color.BLACK);
			g.fillOval(x2, y + 100, sides, sides);
			g.drawString(d2.getNum() + ", ", x2, y + 100);
			
			g.setColor(Color.BLUE);
			g.fillOval(x3, y + 200, sides, sides);
			g.drawString(d3.getNum() + ", ", x3, y + 200);

			g.setColor(Color.RED);
			g.fillOval(x4, y + 300, sides, sides); 
			g.drawString(d4.getNum() + ", ", x4, y + 300);
			
			if(finish == true) {
				g.drawString(d1.getName() + ", " + d1.getNum(),150,400);  
				g.drawString(d2.getName() + ", " + d2.getNum(),150,450);  
				g.drawString(d3.getName() + ", " + d3.getNum(),150,500);  
				g.drawString(d4.getName() + ", " + d4.getNum(),150,550);  
			}
	}
	
	@Override
	public void stop() {
		
	}
	
	@Override
	public void destroy() {
		
	}

}
