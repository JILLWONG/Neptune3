package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;

public class Battle2Attr extends JPanel{
	public static JLabel Tnum;
	public static JLabel TBlood;
	public static JLabel TBloodN;
	public Battle2Attr() {
		Font font18=new Font("Monospaced",Font.BOLD,18);//设置字体格式和大小
		Font font14=new Font("Monospaced",Font.BOLD,14);//设置字体格式和大小
		JLabel T1=new JLabel("目标");
		T1.setFont(font18);
		T1.setBounds(20, 40, 180, 30);
		this.add(T1);
		
		JLabel Tgoal=new JLabel("躲避海盗 抵达终点");
		Tgoal.setFont(font14);
		Tgoal.setBounds(40, 70, 180, 30);
		this.add(Tgoal);
		
		JLabel T2=new JLabel("敌人数量");
		T2.setFont(font18);
		T2.setBounds(20, 140, 180, 30);
		this.add(T2);
		
		Tnum=new JLabel("8");
		Tnum.setFont(font18);
		Tnum.setBounds(40, 170, 180, 30);
		this.add(Tnum);
		
		JLabel T3=new JLabel("血量");
		T3.setFont(font18);
		T3.setBounds(20, 240, 180, 30);
		this.add(T3);

		TBloodN=new JLabel(Player.hp+"%",JLabel.CENTER);
		TBloodN.setBounds(20, 270, 160, 30);
		TBloodN.setForeground(Color.black);
		this.add(TBloodN);
		
		TBlood=new JLabel("");
		TBlood.setBounds(20, 270, 160, 30);
		TBlood.setOpaque(true);
		TBlood.setBackground(new Color(238,99,99));
		this.add(TBlood);
		
		JLabel T4=new JLabel("");
		T4.setBounds(20, 270, 160, 30);
		T4.setOpaque(true);
		T4.setBackground(Color.WHITE);
		this.add(T4);
	}
	public void paint(Graphics g)  
    {  
        super.paint(g);
        TBlood.setBounds(20, 270, (int)(Player.hp*1.6), 30);
        Tnum.setText(String.valueOf(Battle2View.enemyLeft));
        TBloodN.setText(Player.hp+"%");
    }
}
