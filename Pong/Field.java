package Pong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Field  implements KeyListener ,ActionListener{
	
	Elements elm;
	private JFrame frame;
	int refrech=1;
	Timer timer;
	String ldirection="",rdirection="";
	Field(){
		
		elm = new Elements();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(elm);
		frame.addKeyListener(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Pong (by amin)");
		
		timer = new Timer(refrech,this );
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W :ldirection="up";
			break;
		case KeyEvent.VK_S :ldirection="down";
			break;
		case KeyEvent.VK_ENTER :if(!Elements.startgame)timer.start();Elements.startgame=true;
			if(Elements.gameover)timer.start();elm.replay();Elements.gameover=false;
			break;
		case KeyEvent.VK_SPACE :if(!Elements.startgame)timer.start();Elements.startgame=true;;
			break;
		case KeyEvent.VK_R :if(Elements.startgame)timer.start();elm.startover();;
			break;
		}
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP :rdirection="up";
			break;
		case KeyEvent.VK_DOWN :rdirection="down";
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W :ldirection="";
			break;
		case KeyEvent.VK_S :ldirection="";
			break;

		}
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP :rdirection="";
			break;
		case KeyEvent.VK_DOWN :rdirection="";
			break;
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		elm.moveball();
		if(rdirection!="")elm.moverightbar(rdirection);
		if(ldirection!="") elm.moveleftbar(ldirection);
		if(elm.ballx<0) {
			timer.stop();
			Elements.wincount[1]+=1;
			Elements.gameover=true;
		}
		if(elm.ballx>elm.width) {
			timer.stop();
			Elements.wincount[0]+=1;
			Elements.gameover=true;
		}
		elm.repaint();
	}
}
