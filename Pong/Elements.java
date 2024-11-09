	package Pong;
	
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.util.Random;
	import javax.swing.JPanel;
	
	public class Elements extends JPanel{
		
		private static final long serialVersionUID = 1L;
		static boolean startgame=false,gameover;
		static int[]wincount = new int[2];
		final int width=800,hight=600;
		final int bar_width =10;
		final int bar_hight =70;
		final int ballsize = 20;
		final int x=bar_hight/5;
		final int Rbarx = width-bar_width-20;
		final int Lbarx = 20;
		final int barspeed=5;
		int Rbary = hight/2-bar_hight/2;
		int Lbary = hight/2-bar_hight/2;
		int ballx = width/2-ballsize/2;
		int bally = hight/2-ballsize/2;
		int ballspeed = 4;
		int Ydirection = 1;
		int Xdirection = -ballspeed;
		int speedcount ;
		Random ran = new Random();
		int r = ran.nextInt(150)+1;
		int g = ran.nextInt(150)+1;
		int b = ran.nextInt(150)+1;
		Color ballcolor = new Color(r,g,b);
		Elements() {
			this.setPreferredSize(new Dimension(width,hight));
			this.setBackground(Color.BLACK);
		}
		
		@Override
		public void paint(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;
			super.paint(g);
			startgame(g);
			if(startgame) {
			g2D.setColor(Color.WHITE);
			g2D.setFont(new Font("Courier", Font.CENTER_BASELINE, 30));
            g2D.drawString(String.valueOf(wincount[0]), 185,50);
            g2D.drawString(String.valueOf(wincount[1]), 585,50);
			g2D.setColor(Color.GRAY);
			g2D.fillRect(Lbarx, Lbary, bar_width, bar_hight);
			g2D.fillRect(Rbarx, Rbary, bar_width, bar_hight);
			g2D.setColor(ballcolor);
			g2D.drawLine(width/2, 25, width/2, hight-25);
			g2D.fillOval(ballx, bally, ballsize, ballsize);
			gameover(g);
			}
		}
		public void ballcolor() {
			int r = ran.nextInt(205)+50;
			int g = ran.nextInt(205)+50;
			int b = ran.nextInt(205)+50;
			ballcolor=new Color(r,g,b);
		}
		public void moveball(){
			
			if(bally<=0)Ydirection=-Ydirection;
			if(bally+ballsize>=hight)Ydirection=-Ydirection;
			bally=bally+Ydirection;
			
			if(ballx==Lbarx+bar_width){
				for(int i = Lbary;i<=Lbary+bar_hight;i++) {
					if(bally==i || bally+ballsize==i) {
						ballcolor();
						Xdirection=ballspeed;
						speedcount+=1;
						if(ballspeed<11 && speedcount==6) {ballspeed+=2;speedcount=0;}
						if (i<Lbary+x && Ydirection>-5) {Ydirection=Ydirection-2;break;}
						else if (i>Lbary+x && i<Lbary+x*2 && Ydirection>-5) {Ydirection=Ydirection-1;break;}
						//else if (i>Lbary+x*2 && i<Lbary+x*3) {Ydirection=0;break;}
						else if (i>Lbary+x*3 && i<Lbary+x*4&& Ydirection<5) {Ydirection=Ydirection+1;break;}
						else if (i>Lbary+x*4 && i<Lbary+x*5&& Ydirection<5) {Ydirection=Ydirection+2;break;}						
						break;
					}
				}
			}
			if(ballx+ballsize==Rbarx) {
				for(int i = Rbary;i<=Rbary+bar_hight;i++) {
					if(bally==i || bally+ballsize==i) {
						ballcolor();
						Xdirection=-ballspeed;
						speedcount+=1;
						if(ballspeed<9&& speedcount==6) {ballspeed+=2;speedcount=0;}
						if (i<Rbary+x && Ydirection>-5) {Ydirection=Ydirection-2;break;}
						else if (i>Rbary+x && i<Rbary+x*2 && Ydirection>-5) {Ydirection=Ydirection-1;break;}
						//else if (i>Rbary+x*2 && i<Rbary+x*3) {Ydirection=0;break;}
						else if (i>Rbary+x*3 && i<Rbary+x*4 && Ydirection<5) {Ydirection=Ydirection+1;break;}
						else if (i>Rbary+x*4 && i<Rbary+x*5 && Ydirection<5) {Ydirection=Ydirection+2;break;}				
						break;
					}
				}
			}
			ballx=ballx+Xdirection;
			
		}
		public void moveleftbar(String direction){
			
			switch(direction) {
			case "down":if (Lbary+bar_hight <= hight)Lbary+=barspeed;
				break;
			case "up"  :if (Lbary >=0)Lbary-=barspeed;
				break;
			}	
		}
		public void moverightbar(String direction){
		
			switch(direction) {
			case "down":if (Rbary+bar_hight <= hight)Rbary+=barspeed;
				break;
			case "up"  :if (Rbary >=0)Rbary-=barspeed;
				break;
			}	
		}
		public void startgame(Graphics g) {
			if(!startgame) {
				Graphics2D g2D = (Graphics2D) g;
				super.paint(g);
				g2D.setColor(ballcolor);
				g2D.setFont(new Font("Courier", Font.CENTER_BASELINE, 30));
	            g2D.drawString("Welcom to pong game", 220,240);
	            g2D.setColor(Color.GRAY);
				g2D.fillRect(270, 350, bar_width, bar_hight);
				g2D.fillRect(470, 330, bar_width, bar_hight);
				g2D.setColor(ballcolor);
				g2D.fillOval(300, 380, ballsize, ballsize);
			}
		}
		public void gameover(Graphics g) {
			if(gameover) {
				Graphics2D g2D = (Graphics2D) g;
				super.paint(g);
				g2D.setColor(Color.WHITE);
				g2D.setFont(new Font("Courier", Font.CENTER_BASELINE, 30));
	            g2D.drawString(String.valueOf(wincount[0]+" : "+wincount[1]), 350,180);
				g2D.setColor(ballcolor);
	            g2D.drawString("oops you missed the ball", 210,240);
				g2D.setColor(Color.WHITE);
				g2D.setFont(new Font("Courier", Font.CENTER_BASELINE, 20));
	            g2D.drawString("presse 'enter' to continue", 285,280);
	            g2D.setFont(new Font("Courier", 300, 15));
	            g2D.drawString("presse 'r' to start over", 310,315);
			}
		}
		public void replay() {
			 Rbary = hight/2-bar_hight/2;
			 Lbary = hight/2-bar_hight/2;
			 ballx = width/2-ballsize/2;
			 bally = hight/2-ballsize/2;
			 ballspeed = 4;
			 Ydirection = 1;
			 Xdirection = -ballspeed;
			 speedcount = 0 ;
			 gameover=false;
		}
		public void startover() {
			replay();
			wincount[0] = 0;
			wincount[1] = 0;
		}
	}
	
	
	
	
	
