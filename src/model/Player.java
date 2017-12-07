package model;

import java.util.Random;

import view.ContentPane;



public class Player extends Character{
	/** 生命值*/
	public static int hp=100;
	/** 食物量*/
	public static int food;
	/** 水*/
	public static int water;
	/** 金钱*/
	public static int money;
	/** 鱼雷*/
	public static int torpedo;
	/** 航行的距离*/
	public static int distance;
	/** 攻击力*/
	public static int attack;
	
	private int doubleFire;
	private int score;
	
	
	public Player(int life) {
		food=100;
		water=100;
		money=100;
		torpedo=0;
		distance=0;
		
		image = Game.hero0;  
        height = image.getHeight();  
        width = image.getWidth();  
        x = 127;  
        y = 388;  
        doubleFire = 0;   
        score = 0; 
	}
	
	public static void showDetails() {
		System.out.println("Hp:"+hp+" food:"+food+" water:"+water+" money:"+money+" distance:"+distance);
	}
	
	/*
	 * @see battle.model.Character#step()
	 * 实玩家图片的切换，实现动画效果
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		Random r=new Random();
		if(r.nextInt(2)==0)
		{
			image=Game.hero0;
		}
		else
		{
			image=Game.hero1;
		}
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/*
	 * 控制玩家的移动
	 * 传入参数为鼠标的x，y坐标
	 */
	public void move(int mouse_x,int mouse_y)
	{
		//使玩家图片中心与鼠标对齐
		this.x=mouse_x-width/2;
		this.y=mouse_y-height/2;
	}
	
	/*
	 * 得分与获得奖励方法
	 */
	public void addScore(Character c)
	{
		if(c instanceof EasyEnemy)//如果是简单敌人
		{
			score+=((EasyEnemy)c).getScore();
		}
		if(c instanceof HardEnemy)//如果是困难敌人
		{
			score+=((HardEnemy)c).getScore();
			doubleFire+=20;
		}
		if(c instanceof Boss)
		{
			score+=((Boss)c).getScore();
		}
	}
	
	/*
	 * 发射子弹可以是一发，也可以是两发，用数组存储
	 */
	public Bullet[] shoot()
	{
		Bullet[] bullets = null;  
        //何时开启双倍火力：  
        if(doubleFire != 0){ //创建双倍火力  
            bullets = new Bullet[2];  
            Bullet b1 = new Bullet(x + width/4 - Game.bullet.getWidth()/2,y + Game.bullet.getWidth());  
            Bullet b2 = new Bullet(x + width*3/4 - Game.bullet.getWidth()/2,y + Game.bullet.getWidth());  
            bullets[0] = b1;  
            bullets[1] = b2;  
            //每创建一个双倍火力，doubleFire-1  
            doubleFire -= 1;  
        }else{  
            //单倍火力：  
            //子弹x坐标：x+英雄机宽度/2-子弹宽度/2  
            //子弹y坐标：y-子弹高度  
            bullets = new Bullet[1];  
            bullets[0] = new Bullet(x + width/2 - Game.bullet.getWidth()/2,y - Game.bullet.getHeight());  
        }  
        return bullets;  
	}
	
	/*
	 * 调用碰撞检测的方法
	 * 返回值为是否碰撞
	 */
	public boolean hit(Character c)
	{
		//调用碰撞检测方法，检测是否碰撞  
        boolean r = Character.boom(this, c);  
        if(r){ //如果碰撞  
            Player.hp--;  
            doubleFire = 0;  
        }  
        return r;  
	}
	
	//获得生命值
	public static int getLife()
	{
		return Player.hp;
	}
	//获得分数
	public int getScore()
	{
		return this.score;
	}
}