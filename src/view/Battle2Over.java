package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Battle2Over extends JFrame{
	/** 信息窗口的宽度 */
	public static final int INFO_W = 1280;
	/** 信息窗口的高度 */
	public static final int INFO_H = 680;
	public static Battle2Over OverView;
	public Battle2Over(String name,int status){
		super(name);
		OverView=this;
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(40, 20);
		this.setPreferredSize(new Dimension(INFO_W, INFO_H));
		// 添加成员组件
		JPanel contentPane=new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel picture = new JLabel("");
	    picture.setIcon(new ImageIcon("img\\B2"+status+".png"));
	    picture.setBounds(0, 0, M2BattleView.INFO_W, M2BattleView.INFO_H);
	    contentPane.add(picture);
	    
	    contentPane.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Battle2Over.OverView.dispose();
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
	    	
	    });
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
}
