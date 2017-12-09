package controller;
import view.MStartView;
import view.ContentPane;
import view.M2BattleView;
import view.MChooseView;
import view.MRoadView;
import view.MShopView;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.Event;
import model.Player;


public class StartGame {
	public static MStartView sv;
	public static MChooseView cv;
	public static MRoadView rv;
	public static M2BattleView bv2;
	public static MShopView shopv;
	//wzw edit-add
	/** 总距离*/
	public static final int DISTANCE=100;
	/** 日期*/
	public static String date="1398-02-11";
	public static Random eRand=new Random();
	public static int curEventId;
	public static Event curEvent=null;
	//wzw
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StartGame.sv=new MStartView("Neptune");
		//wzw edit-add
		new Player(0);
		Player.showDetails();
		Event.initialize();
		curEventId=8;
		curEvent=Event.find(curEventId);
		//wzw
	}
	
	//wzw edit-add
	/**
	 * 游戏是否结束
	 * @return 是否
	 */
	public static boolean gameOver() {
		if(Player.food<=0||Player.hp<=0||Player.water<=0||Player.distance>=DISTANCE) {
			showResult();
			return true;
		}
		return false;
	}

	/**
	 * 展示游戏结果
	 */
	public static void showResult() {
		if((Player.food<=0||Player.hp<=0||Player.water<=0)&&Player.distance<DISTANCE) {
			System.out.println("you lose");
			MChooseView.contentPane.removeAll();
			Font font1=new Font("Monospaced",Font.BOLD,22);//设置字体格式和大小
			JLabel youLose=new JLabel("you lose!");
			youLose.setFont(font1);
			youLose.setBounds(300, 200, 200, 40);
			MChooseView.contentPane.add(youLose);
//			JTextArea result=new JTextArea(StartGame.date+"："+Player.distance);
//			result.setLineWrap(true);
//			result.setFont(new Font("标楷体", Font.BOLD, 16));
//			result.setEditable(false);
//			result.setBackground(new Color(135,206,235));
//			result.setBounds(40, 50, 400, 150);
//			MChooseView.contentPane.add(result);
			JLabel picture = new JLabel("");
		    picture.setIcon(new ImageIcon("img\\back.png"));
		    picture.setBounds(0, 0, MChooseView.INFO_W, MChooseView.INFO_H);
			MChooseView.contentPane.add(picture);
			MChooseView.contentPane.repaint();
		}else if(Player.food>=0 && Player.hp>=0 && Player.water>=0 && Player.distance>=DISTANCE) {
			System.out.println("you win");
			MChooseView.contentPane.removeAll();
			Font font1=new Font("Monospaced",Font.BOLD,22);//设置字体格式和大小
			JLabel youWin=new JLabel("you win!");
			youWin.setFont(font1);
			youWin.setBounds(300, 200, 200, 40);
			MChooseView.contentPane.add(youWin);
//			JTextArea result=new JTextArea(StartGame.date+"："+Player.hp+"\n"+Player.food+"\n"+Player.money+"\n"+Player.money+"\n"+Player.water);
//			result.setLineWrap(true);
//			result.setFont(new Font("标楷体", Font.BOLD, 16));
//			result.setEditable(false);
//			result.setBackground(new Color(135,206,235));
//			result.setBounds(40, 50, 400, 150);
//			MChooseView.contentPane.add(result);
			JLabel picture = new JLabel("");
		    picture.setIcon(new ImageIcon("img\\back.png"));
		    picture.setBounds(0, 0, MChooseView.INFO_W, MChooseView.INFO_H);
			MChooseView.contentPane.add(picture);
			MChooseView.contentPane.repaint();
		}
	}
	//wzw

	public static void timeChange() throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date d=formatter.parse(date);
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, 3);
		date=formatter.format(c.getTime());
	}
}
