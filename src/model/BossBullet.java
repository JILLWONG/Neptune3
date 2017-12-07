package model;

import view.BattleView;

public class BossBullet extends Bullet{
	
	private int x_speed=0;
	private int y_speed=0;	
	
	public BossBullet(int x,int y,int type)
	{
		super(x,y);
		
		switch(type)
		{
		case 1:{this.x_speed=-1;this.y_speed=2;break;}
		case 2:{this.x_speed=-1;this.y_speed=3;break;}
		case 3:{this.x_speed= 0;this.y_speed=3;break;}
		case 4:{this.x_speed= 1;this.y_speed=3;break;}
		case 5:{this.x_speed= 1;this.y_speed=2;break;}
		}
	}
	
	@Override 
	public void step()
	{
		x+=x_speed;
		y+=y_speed;
	}
	
	@Override  
    public boolean outOfBounds() {  
        //子弹的y坐标>边界，越界  
		return y > BattleView.HEIGHT;  
    }
}
