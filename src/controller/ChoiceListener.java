package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import model.Player;
import view.ContentPane;
import view.MChooseView;

public class ChoiceListener implements ActionListener{

	private int choice;
	private ContentPane cp;
	
	public ChoiceListener(int choice,ContentPane cp) {
		this.setChoice(choice);
		this.setCp(cp);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Player.distance++;//每次选择向前航行1
		if(StartGame.curEvent.getChoices()[this.getChoice()].getIsFight()==1) {
			GameController gc=new GameController();
	    	gc.listener();
	    	gc.action();
		}
		ContentPane.textLabel2.append(StartGame.date+"："+StartGame.curEvent.getChoices()[this.choice].getDiary()+"\n");
		ContentPane.textLabel2.setCaretPosition(ContentPane.textLabel2.getText().length());
		try {
			StartGame.timeChange();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StartGame.curEvent.getChoices()[this.choice].changeAttribute();
		try {
			StartGame.curEvent=StartGame.curEvent.getChoices()[this.choice].newEvent(StartGame.curEvent.getChoices()[this.choice].getNextID());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ContentPane.textLabel.setText(StartGame.date+"："+StartGame.curEvent.getDescription());	
		ContentPane.choice[0].setText(StartGame.curEvent.getChoices()[0].getDescription());
		ContentPane.choice[1].setText(StartGame.curEvent.getChoices()[1].getDescription());
		ContentPane.choice[2].setText(StartGame.curEvent.getChoices()[2].getDescription());
		ContentPane.choice[3].setText(StartGame.curEvent.getChoices()[3].getDescription());
		ContentPane.tattr[0].setText(""+Player.hp);
		ContentPane.tattr[1].setText(""+Player.money);
		ContentPane.tattr[2].setText(""+Player.food);
		ContentPane.tattr[3].setText(""+Player.water);
		ContentPane.tattr[4].setText(""+Player.torpedo);
		
		ContentPane.textLabel2.setVisible(false);
		ContentPane.textLabel.setVisible(false);
		ContentPane.js.setVisible(false);
		ContentPane.choice[0].setVisible(false);
		ContentPane.choice[1].setVisible(false);
		ContentPane.choice[2].setVisible(false);
		ContentPane.choice[3].setVisible(false);
		ContentPane.picture.setVisible(true);
		
		if(StartGame.gameOver()) {
			// TODO
		}
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public ContentPane getCp() {
		return cp;
	}
	public void setCp(ContentPane cp) {
		this.cp = cp;
	}

}
