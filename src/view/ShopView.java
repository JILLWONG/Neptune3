package view;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ShopController;

public class ShopView extends JPanel{
	public JLabel[] iteml=new JLabel[4];
	public JLabel[] valuel=new JLabel[4];
	public static int[] vl=new int[4];
	public static int[] vr=new int[4];
	public JLabel[] valuer=new JLabel[4];
	public JLabel[] itemr=new JLabel[4];
	public JButton[] exchange=new JButton[4];
	private String[] itemName= {"血量","金钱","食物","淡水","距离"};
	public ShopView(){
		Initnew();

		JLabel picture = new JLabel("");
	    picture.setIcon(new ImageIcon("img\\shop.png"));
	    picture.setBounds(0, 0, MShopView.INFO_W, MShopView.INFO_H);
	    this.add(picture);
	}
	public void Initnew() {
		Font font=new Font("Monospaced",Font.BOLD,16);//设置字体格式和大小
		int rand;
	    for(int i=0;i<4;i++) {
	    	vl[i]=(int)Math.round(Math.random()*20+5);
	    	valuel[i]=new JLabel(String.valueOf(vl[i]));
	    	valuel[i].setFont(font);
	    	valuel[i].setBounds(140, 190+i*50, 60, 40);
	    	this.add(valuel[i]);
	    	
	    	rand=(int)Math.round(Math.random()*4);
	    	iteml[i]=new JLabel(itemName[rand]);
	    	iteml[i].setFont(font);
	    	iteml[i].setBounds(160, 190+i*50, 60, 40);
	    	this.add(iteml[i]);
	    	
	    	exchange[i]=new JButton("交换");
	    	exchange[i].addMouseListener(new ShopController(i,this));
	    	exchange[i].setBounds(210, 200+i*50, 60, 20);
	    	this.add(exchange[i]);
	    	
	    	vr[i]=(int)Math.round(Math.random()*5+1);
	    	valuer[i]=new JLabel(String.valueOf(vr[i]));
	    	valuer[i].setFont(font);
	    	valuer[i].setBounds(290, 190+i*50, 60, 40);
	    	this.add(valuer[i]);
	    	
	    	rand=(int)Math.round(Math.random()*4);
	    	itemr[i]=new JLabel(itemName[rand]);
	    	itemr[i].setFont(font);
	    	itemr[i].setBounds(310, 190+i*50, 60, 40);
	    	this.add(itemr[i]);
	    }
	}
	public void Init() {
		int rand;
	    for(int i=0;i<4;i++) {
	    	vl[i]=(int)Math.round(Math.random()*20+5);
	    	valuel[i].setText(String.valueOf(vl[i]));
	    	
	    	rand=(int)Math.round(Math.random()*4);
	    	iteml[i].setText(itemName[rand]);
	    	
	    	//exchange[i].addMouseListener(new ShopController(i,this));
	    	
	    	vr[i]=(int)Math.round(Math.random()*5+1);
	    	valuer[i].setText(String.valueOf(vr[i]));
	    	
	    	rand=(int)Math.round(Math.random()*4);
	    	itemr[i].setText(itemName[rand]);
	    }
	}
	public void paint(Graphics g)  
	{   
	    super.paint(g);  
	}
}
