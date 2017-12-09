package view;

import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Player;

public class MRoadView extends JFrame{
	/** 信息窗口的宽度 */
	private static final int INFO_W = 300;
	/** 信息窗口的高度 */
	private static final int INFO_H = 540;
    public static RoadView roadView=new RoadView();
	public MRoadView(String name){
		super(name);
		roadViewInit();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	private void roadViewInit() {
		this.setLocation(950, 100);
		this.setPreferredSize(new Dimension(INFO_W, INFO_H));
		// 添加成员组件
        this.setContentPane(roadView);
        roadView.setLayout(null);
        roadView.setBackground(new Color(220,220,220));


	    
	}
}
