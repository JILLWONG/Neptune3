package view;

import controller.StartGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MStartView extends JFrame{
	/** 信息窗口的宽度 */
	private static final int INFO_W = 960;
	/** 信息窗口的高度 */
	private static final int INFO_H = 600;
	public static JLabel start;
	public static JPanel contentPane;
	public static int picNum=2;
	public MStartView(String name){
		super(name);
		startViewInit();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	private void startViewInit() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 100);
		this.setPreferredSize(new Dimension(INFO_W, INFO_H));
		// 添加成员组件
        contentPane=new JPanel();
        this.setContentPane(contentPane);
		contentPane.setLayout(null);
		int labelHigh=100;
		int labelWidth=200;
		start=new JLabel("START");
		Font font=new Font("Monospaced",Font.BOLD,60);//设置字体格式和大小
		start.setFont(font);
		start.setBounds((INFO_W-labelWidth)/2, INFO_H/2+100, labelWidth, labelHigh);
		start.setForeground(new Color(255,255,255));
		
		final JLabel picture = new JLabel("");
	    picture.setIcon(new ImageIcon("img\\StartBG.png"));
	    picture.setBounds(0, 0, INFO_W, INFO_H);
	    
		start.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				start.setVisible(false);
				picture.setVisible(false);
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
				start.setForeground(new Color(238,220,130));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				start.setForeground(new Color(255,255,255));
			}
		});
	    
	    final JLabel picture1 = new JLabel("");
	    picture1.setIcon(new ImageIcon("img\\Story1.png"));
	    picture1.setBounds(0, 0, INFO_W, INFO_H);
	    picture1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				picture1.setVisible(false);
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
	    
	    JLabel picture2 = new JLabel("");
	    picture2.setIcon(new ImageIcon("img\\Story2.png"));
	    picture2.setBounds(0, 0, INFO_W, INFO_H);
	    picture2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				StartGame.sv.setVisible(false);
				StartGame.cv=new MChooseView("Neptune");
				StartGame.rv=new MRoadView("进度");
				StartGame.shopv=new MShopView("恶魔商店");
				StartGame.shopv.setVisible(false);
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
	    contentPane.add(start);
		contentPane.add(picture);
	    contentPane.add(picture1);
	    contentPane.add(picture2);
	   
	}
}