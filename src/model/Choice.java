package model;

import java.sql.SQLException;
import controller.StartGame;

/**
 * 选项类
 * @author 王之威
 *
 */
public class Choice {
	/** 描述*/
	private String description;
	/** 金钱*/
	private int money;
	/** 食物*/
	private int food;
	/** 血量*/
	private int HP;
	/** 水*/
	private int water;
	/** 装备*/
	private int buff;
	/** 航行日志*/
	private String diary;
	/** 下一个事件的ID*/
	private int nextID;
	/** 移动距离*/
	private int diatance;
	/** 是否战斗*/
	private int isFight;
	
	/**
	 * 选项构造函数
	 * @param des 选项描述
	 * @param money 金钱
	 * @param food 食物
	 * @param HP 血量
	 * @param water 水
	 * @param nextID 下一个关联事件ID 
	 */
	public Choice(String des, int diatance,int money ,int food, int HP,int water,String diary,int nextID,int buff,int isFight) {
		this.setDescription(des);
		this.setDiatance(diatance);
		this.setMoney(money);
		this.setFood(food);
		this.setHP(HP);
		this.setWater(water);
		this.setDiary(diary);
		this.setNextID(nextID);
		this.setBuff(buff);
		this.setIsFight(isFight);
	}
	
	public String getDiary() {
		return diary;
	}

	public void setDiary(String diary) {
		this.diary = diary;
	}

	/**
	 * 产生特定的下一个事件
	 * @return 
	 * @throws SQLException 
	 */
	public Event newEvent(int nextID) throws SQLException {
		if(nextID!=0) {
			if(nextID==-10) {
				Player.evil++;
				if(Player.evil<4) {
					if(StartGame.eRand.nextInt(2)==0) {
						nextID=9;
					}else {
						nextID=11;
					}
					//
				}if(Player.evil==4) {
					nextID=10;
				}
			}
			if(nextID==-11) {
				if(StartGame.eRand.nextInt(2)==0) {
					nextID=9;
				}else {
					nextID=11;
				}
			}
			if(nextID==-12) {
				if(Player.evil>0) {
					nextID=12;
				}else {
					nextID=13;
				}
			}
			return Event.find(Math.abs(nextID));
		}else {
			int eventId=1;
			if(StartGame.eRand.nextInt(8)==0) {
				eventId=1;
			}
			if(StartGame.eRand.nextInt(8)==1) {
				eventId=2;
			}
			if(StartGame.eRand.nextInt(8)==2) {
				eventId=3;
			}
			if(StartGame.eRand.nextInt(8)==3) {
				eventId=6;
			}
			if(StartGame.eRand.nextInt(8)==4) {
				eventId=14;
			}
			if(StartGame.eRand.nextInt(8)==5) {
				eventId=21;
			}
			if(StartGame.eRand.nextInt(8)==6) {
				eventId=25;
			}
			if(StartGame.eRand.nextInt(8)==7) {
				eventId=28;
			}
			return Event.find(eventId);
		}
	}
	
	/**
	 * 进入打斗界面
	 */
	public void fight() {
		
	}
	
	/**
	 * 改变玩家属性
	 */
	public void changeAttribute() {
		Player.hp+=this.getHP();
		Player.food+=this.getFood();
		Player.water+=this.getWater();
		Player.money+=this.getMoney();
		Player.distance+=this.getDiatance();
		if(Player.hp<0) {
			Player.hp=0;
		}
		if(Player.food<0) {
			Player.food=0;
		}
		if(Player.water<0) {
			Player.water=0;
		}
		if(Player.distance<0) {
			Player.distance=0;
		}
		if(this.getBuff()==1) {
			Player.torpedo++;
		}
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getNextID() {
		return nextID;
	}
	public void setNextID(int nextID) {
		this.nextID = nextID;
	}

	public int getDiatance() {
		return diatance;
	}

	public void setDiatance(int diatance) {
		this.diatance = diatance;
	}

	public int getBuff() {
		return buff;
	}

	public void setBuff(int buff) {
		this.buff = buff;
	}

	public int getIsFight() {
		return isFight;
	}

	public void setIsFight(int isFight) {
		this.isFight = isFight;
	}
	
}
