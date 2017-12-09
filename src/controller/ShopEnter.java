package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopEnter implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(StartGame.shopv.isVisible()==false) {
			StartGame.shopv.setVisible(true);
		}else if(StartGame.shopv.isVisible()==true) {
			StartGame.shopv.setVisible(false);
		}
	}

}
