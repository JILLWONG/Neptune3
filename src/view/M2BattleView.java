package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.playerController;

public class M2BattleView extends JFrame{
	/** 信息窗口的宽度 */
	public static final int INFO_W = 1280;
	/** 信息窗口的高度 */
	public static final int INFO_H = 680;
	public static JLabel boat;
	public M2BattleView(String name){
		super(name);
		battleViewInit();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	private void battleViewInit() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(40, 20);
		this.setPreferredSize(new Dimension(INFO_W, INFO_H));
		// 添加成员组件
        JPanel contentPane=new JPanel();
        this.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(220,220,220));
		initBattleAera(contentPane);
	}
	private void initBattleAera(JPanel contentPane) {		
		/*左侧属性*/
		Battle2Attr leftAera=new Battle2Attr();
		leftAera.setBounds(20, 20, 200, 610);
		leftAera.setBackground(new Color(144,238,144));
		this.add(leftAera);
		leftAera.setLayout(null);		
		
		/*右侧游戏*/
		Battle2View battleAera=new Battle2View(leftAera,this);
		battleAera.setBounds(230, 20, 1030, 610);
		battleAera.setBackground(new Color(135,206,235));
		contentPane.add(battleAera);
		battleAera.setLayout(null);
		this.addKeyListener(battleAera);
		playerController pc=new playerController();
		battleAera.addMouseListener(pc);
	}
}