package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import controller.ChoiceListener;
import controller.StartGame;
import model.Player;

public class ContentPane extends JPanel{
	public static JButton[] choice=new JButton[4];
	public static JLabel[] tattr=new JLabel[5];
	public static JTextArea textLabel;
	public static JTextArea textLabel2;
	public static JLabel picture;
	public static JScrollPane js;
	
	public ContentPane() {
		Font font14=new Font("Monospaced",Font.BOLD,14);//设置字体格式和大小
		Font font=new Font("Monospaced",Font.BOLD,18);//设置字体格式和大小
		Font font1=new Font("Monospaced",Font.BOLD,22);//设置字体格式和大小
		
		/** 遮罩图片*/
		picture = new JLabel("");
	    picture.setIcon(new ImageIcon("img\\background.jpg"));
	    picture.setBounds(0, 0, MChooseView.INFO_W, MChooseView.INFO_H);
	    picture.setVisible(false);
	    picture.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ContentPane.picture.setVisible(false);
				ContentPane.textLabel2.setVisible(true);
				ContentPane.textLabel.setVisible(true);
				ContentPane.choice[0].setVisible(true);
				ContentPane.choice[1].setVisible(true);
				ContentPane.choice[2].setVisible(true);
				ContentPane.choice[3].setVisible(true);
				ContentPane.js.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	    this.add(picture);
		/** 左上角信息区域 */
		JPanel text=new JPanel();//显示选择区域
		text.setBackground(new Color(135,206,235));
		text.setBounds(30, 30, 500, 230);
		text.setLayout(null);
		this.add(text);
		textLabel=new JTextArea(StartGame.date+"："+StartGame.curEvent.getDescription());
		textLabel.setLineWrap(true);
		textLabel.setFont(new Font("标楷体", Font.BOLD, 16));
		textLabel.setEditable(false);
		textLabel.setBackground(new Color(135,206,235));
		textLabel.setBounds(40, 50, 400, 150);
		text.add(textLabel);

		JLabel ttextTitle=new JLabel("事件");
		ttextTitle.setFont(font1);
		ttextTitle.setBounds(230, 5, 200, 40);
		text.add(ttextTitle);
		
		/** 左下角选择区域 */
		JPanel choiceAera=new JPanel();
		choiceAera.setBounds(30, 280, 500, 200);
		choiceAera.setBackground(new Color(135,206,235));
		this.add(choiceAera);
		choiceAera.setLayout(null);
		choice[0] =new JButton(StartGame.curEvent.getChoices()[0].getDescription());
		choice[0].addActionListener(new ChoiceListener(0,this));
		choice[0].setBounds(50, 60, 170, 50);
		choice[0].setFont(font14);
		choiceAera.add(choice[0]);
		choice[1]=new JButton(StartGame.curEvent.getChoices()[1].getDescription());
		choice[1].addActionListener(new ChoiceListener(1,this));
		choice[1].setBounds(280, 60, 170, 50);
		choice[1].setFont(font14);
		choiceAera.add(choice[1]);
		choice[2] =new JButton(StartGame.curEvent.getChoices()[2].getDescription());
		choice[2].addActionListener(new ChoiceListener(2,this));
		choice[2].setBounds(50, 130, 170, 50);
		choice[2].setFont(font14);
		choiceAera.add(choice[2]);
		choice[3] =new JButton(StartGame.curEvent.getChoices()[3].getDescription());
		choice[3].addActionListener(new ChoiceListener(3,this));
		choice[3].setBounds(280, 130, 170, 50);
		choice[3].setFont(font14);
		choiceAera.add(choice[3]);
		
		JLabel tchoiceTitle=new JLabel("选择");
		tchoiceTitle.setFont(font1);
		tchoiceTitle.setBounds(230, 5, 200, 40);
		choiceAera.add(tchoiceTitle);
		
		/** 右上角航行日志区域 */
		JPanel itemAera=new JPanel();
		itemAera.setBounds(580, 30, 250, 230);
		itemAera.setBackground(new Color(135,206,235));
		this.add(itemAera);
		itemAera.setLayout(null);
		textLabel2=new JTextArea("1398-02-08：你指挥Neptune号开启了XX之旅。\n");
		textLabel2.setVisible(true);
		textLabel2.setLineWrap(true);
		textLabel2.setFont(new Font("标楷体", Font.BOLD, 16));
		textLabel2.setEditable(false);
		textLabel2.setBackground(new Color(135,206,235));
		textLabel2.setBounds(0, 0, 160, 150);
		js=new JScrollPane(textLabel2);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		js.setBounds(50, 50, 180, 150);
		itemAera.add(js);
		//itemAera.add(textLabel2);	
		

		JLabel tItemTitle=new JLabel("航行日志");
		tItemTitle.setFont(font1);
		tItemTitle.setBounds(100, 10, 180, 40);
		itemAera.add(tItemTitle);
		
		
		/** 右下角属性区域 */
		JPanel attrAera=new JPanel();
		attrAera.setBounds(580, 280, 250, 200);
		attrAera.setBackground(new Color(135,206,235));
		this.add(attrAera);
		attrAera.setLayout(null);
		JLabel tAttrTitle=new JLabel("属性");
		tAttrTitle.setFont(font1);
		tAttrTitle.setBounds(100, 10, 180, 40);
		attrAera.add(tAttrTitle);
		JLabel tBlood=new JLabel("血量：    %");
		tBlood.setFont(font);
		tBlood.setBounds(70, 50, 180, 30);
		attrAera.add(tBlood);
		tattr[0]=new JLabel(""+Player.hp);
		tattr[0].setBounds(120, 50, 60, 30);
		tattr[0].setFont(font);
		attrAera.add(tattr[0]);
		
		JLabel tMoney=new JLabel("金钱：    G");
		tMoney.setFont(font);
		tMoney.setBounds(70, 80, 180, 30);
		attrAera.add(tMoney);
		tattr[1]=new JLabel(""+Player.money);
		tattr[1].setBounds(120, 80, 60, 30);
		tattr[1].setFont(font);
		attrAera.add(tattr[1]);
		
		JLabel tFood=new JLabel("食物：   包");
		tFood.setFont(font);
		tFood.setBounds(70, 110, 180, 30);
		attrAera.add(tFood);
		tattr[2]=new JLabel(""+Player.food);
		tattr[2].setBounds(120, 110, 60, 30);
		tattr[2].setFont(font);
		attrAera.add(tattr[2]);
		
		JLabel tWater=new JLabel("淡水：   桶");
		tWater.setFont(font);
		tWater.setBounds(70, 140, 180, 30);
		attrAera.add(tWater);
		tattr[3]=new JLabel(""+Player.water);
		tattr[3].setBounds(120, 140, 60, 30);
		tattr[3].setFont(font);
		attrAera.add(tattr[3]);
		
		JLabel ttorpedo=new JLabel("鱼雷：   颗");
		ttorpedo.setFont(font);
		ttorpedo.setBounds(70, 170, 180, 30);
		attrAera.add(ttorpedo);
		tattr[4]=new JLabel(""+Player.torpedo);
		tattr[4].setBounds(120, 170, 60, 30);
		tattr[4].setFont(font);
		attrAera.add(tattr[4]);
	}
	
}
