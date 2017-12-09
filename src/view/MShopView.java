package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MShopView extends JFrame{
	/** 信息窗口的宽度 */
	public static final int INFO_W = 800;
	/** 信息窗口的高度 */
	public static final int INFO_H = 500;
	public MShopView(String name){
		super(name);
		ViewInit();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	private void ViewInit() {
		this.setLocation(200, 100);
		this.setPreferredSize(new Dimension(INFO_W, INFO_H));
		ShopView contentPane=new ShopView();
        this.setContentPane(contentPane);
		contentPane.setLayout(null);
	    
	    /**初始化商店商品*/
	    //可以交换的 ：血量/金钱/食物/淡水/距离
    	
}
}
