package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Player;
import view.ContentPane;
import view.MChooseView;
import view.ShopView;

public class ShopController implements MouseListener{
	int num;
	ShopView shopView;
	public ShopController(int num,ShopView shopView) {//0,1,2,3分别代表第1、2、3、4个交换
		this.num=num;
		this.shopView=shopView;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int lossNum=shopView.vl[num];
		int getNum=shopView.vr[num];
		String lossItem=shopView.iteml[num].getText();
		String getItem=shopView.itemr[num].getText();
		System.out.println(lossNum);
		if(lossItem.equals("血量")) {
			Player.hp-=lossNum;
		}else if(lossItem.equals("金钱")) {
			Player.money-=lossNum;
		}else if(lossItem.equals("食物")) {
			Player.food-=lossNum;
		}else if(lossItem.equals("淡水")) {
			Player.water-=lossNum;
		}else if(lossItem.equals("距离")) {
			Player.distance-=lossNum;
		}
		if(getItem.equals("血量")) {
			Player.hp+=getNum;
		}else if(getItem.equals("金钱")) {
			Player.money+=getNum;
		}else if(getItem.equals("食物")) {
			Player.food+=getNum;
		}else if(getItem.equals("淡水")) {
			Player.water+=getNum;
		}else if(getItem.equals("距离")) {
			Player.distance+=getNum;
		}		
		//这里需要一个刷新其他页面中这五个属性显示的函数
		ContentPane.tattr[0].setText(""+Player.hp);
		ContentPane.tattr[1].setText(""+Player.money);
		ContentPane.tattr[2].setText(""+Player.food);
		ContentPane.tattr[3].setText(""+Player.water);
		ContentPane.tattr[4].setText(""+Player.torpedo);
		ContentPane.picture.setVisible(true);
		ContentPane.picture.setVisible(false);
		shopView.Init();
		shopView.repaint();
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
