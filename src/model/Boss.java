package model;

import java.util.Random;

import view.BattleView;



public class Boss extends Character{

	/*敌舰类私有成员*/  
    private int xspeed = 1; //水平移动的速度为1  
    private int yspeed = 1; //垂直移动的速度为2  
    private int score=200;
    private int attacktype=-1;//boss的攻击方式，-1表示攻击间隔时不攻击
    private int hp=40;
      
    //对外提供的读取敌舰分数
    public int getScore(){  
        return score;  
    }  
    
    public int getHp()
    {
    	return this.hp;
    }
    public void takeDamage()
    {
    	this.hp--;
    }
    
    public void stopAttack()
    {
    	this.attacktype=-1;
    }
    /** 
     * 敌舰的无参构造方法 
     */  
    public Boss(){  
        //step1: 从主程序中获取敌舰图片的静态变量
        this.image = Game.boss;  
        //step2: 使用图片宽高设置对象宽高  
        this.width = image.getWidth();  
        this.height= image.getHeight();  
        //step3: 设置敌舰开始下落的高度  
        this.y = -height;  
        this.x = 127;  
    }  
  
    @Override  
    public void step() {  
        //每次x移动一个xspeed，y移动一个yspeed  
        x += xspeed;  
        y += yspeed;  
        //大飞机不能起出边界，一旦超出那么xspeed*（-1），相当于反向移动  
        if(x < 0 || x > BattleView.WIDTH - width){  
            xspeed *= -1;  
        }
        if(y<-height||y>50)
        {
        	yspeed*=-1;
        }
    }  
  
    @Override  
    public boolean outOfBounds() {  
        //y坐标>游戏界面，越界  
        return y > BattleView.HEIGHT;  
    }
    
    public int getAttackType()
    {
    	return this.attacktype;
    }
    
    public void beginShoot()
    {
    	Random random=new Random();
    	this.attacktype=random.nextInt(4);
    }
    
    public BossBullet[] shoot()
	{
		BossBullet[] bullets = new BossBullet[5];  
		
		BossBullet b1=null;
		BossBullet b2=null;
		BossBullet b3=null;
		BossBullet b4=null;
		BossBullet b5=null;
		
		switch(attacktype)
		{
		case 0:{
			b1 = new BossBullet(x + width/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),3);  
			b2 = new BossBullet(x + width*2/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),3);
			b3 = new BossBullet(x + width*3/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),3);
			b4 = new BossBullet(x + width*4/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),3);
			b5 = new BossBullet(x + width*5/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),3);
			break;
		}
		case 1:{
			b1 = new BossBullet(x + width/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),1);  
			b2 = new BossBullet(x + width*2/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),2);
			b3 = new BossBullet(x + width*3/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),3);
			b4 = new BossBullet(x + width*4/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),4);
			b5 = new BossBullet(x + width*5/6 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth()+Game.boss.getHeight(),5);
			break;
		}
		case 2:{
			b1 = new BossBullet(BattleView.WIDTH*1/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);  
			b2 = new BossBullet(BattleView.WIDTH*2/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			b3 = new BossBullet(BattleView.WIDTH*3/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			b4 = new BossBullet(BattleView.WIDTH*4/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			b5 = new BossBullet(BattleView.WIDTH*5/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			break;
		}
		case 3:{
			b1 = new BossBullet(BattleView.WIDTH*6/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);  
			b2 = new BossBullet(BattleView.WIDTH*7/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			b3 = new BossBullet(BattleView.WIDTH*8/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			b4 = new BossBullet(BattleView.WIDTH*9/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			b5 = new BossBullet(BattleView.WIDTH*10/11 - Game.bullet.getWidth()/2,y - Game.bullet.getWidth(),3);
			break;
		}
		}
		
		bullets[0]=b1;
		bullets[1]=b2;
		bullets[2]=b3;
		bullets[3]=b4;
		bullets[4]=b5;
		
        return bullets;  
	}

}
