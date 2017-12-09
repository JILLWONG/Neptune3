package model;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class B2enemy {
	public JLabel enemy=new JLabel();
	public int enemyX;//坐标
	public int enemyY;
	public int enemyEX;//结束
	public int enemyEY;
	public int enemySX;//开始
	public int enemySY;
	public int enemyKX;//步伐
	public int enemyKY;
	public Boolean enemyW;
	
	public B2enemy() {
		enemy.setIcon(new ImageIcon("img\\d1.png"));
	}

	public JLabel getEnemy() {
		return enemy;
	}

	public void setEnemy(JLabel enemy) {
		this.enemy = enemy;
	}

}
