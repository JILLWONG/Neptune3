package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;

public class RoadView extends JPanel{
	/** 信息窗口的宽度 */
	private static final int INFO_W = 300;
	/** 信息窗口的高度 */
	private static final int INFO_H = 540;
    JLabel boat = new JLabel("");
	public static int distance=410;//410到10 一共400
	public RoadView() {
	    boat.setIcon(new ImageIcon("img\\boat.png"));
	    boat.setBounds(120, distance, 100, 100);
		this.add(boat);
		JLabel picture = new JLabel("");
	    picture.setIcon(new ImageIcon("img\\road.png"));
	    picture.setBounds(0, 0, INFO_W, INFO_H);
	    this.add(picture);
	}
	public void paint(Graphics g)  
    {  
        super.paint(g); 
        distance=410-4*Player.distance;
        if(distance<10)distance=10;
        if(distance>410)distance=410;
		boat.setBounds(120, distance, 100, 100);
	}
}
