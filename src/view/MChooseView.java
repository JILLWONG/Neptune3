package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MChooseView extends JFrame{
	/** 信息窗口的宽度 */
	public static final int INFO_W = 860;
	/** 信息窗口的高度 */
	public static final int INFO_H = 540;
	
	public static ContentPane contentPane;

	public MChooseView(String name){
		super(name);
		chooseViewInit();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	private void chooseViewInit() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(50, 100);
		this.setPreferredSize(new Dimension(INFO_W, INFO_H));
		// 添加成员组件
		contentPane=new ContentPane();
        this.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(220,220,220));
		JLabel picture = new JLabel("");
	    picture.setIcon(new ImageIcon("img\\back.png"));
	    picture.setBounds(0, 0, INFO_W, INFO_H);
	    contentPane.add(picture);
	}
	
}