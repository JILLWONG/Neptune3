package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.Battle2View;

public class playerController implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		int disx=x-Battle2View.x-30;
		int disy=y-Battle2View.y-30;
		System.out.println("x:"+x+" y:"+y+" disx:"+disx+" disy:"+disy);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
