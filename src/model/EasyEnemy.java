package model;

import java.util.Random;

import view.BattleView;


/** 
 * 封装敌船属性和功能的类 
 */ 
public class EasyEnemy extends Character{

	private int speed = 2; //敌船每次下落2个单位长度  
    private int score = 5; //敌船包含的奖励分数  
      
    //对外提供的读取敌船奖励分数的方法  
    public int getScore(){  
        return score;  
    }  
      
    /** 
     * 敌船类的无参构造方法 
     */  
    public EasyEnemy(){  
        image = Game.easyenemy;  
        width = image.getWidth();  
        height = image.getHeight();  
        y = -height;  
        Random r = new Random();  
        x = r.nextInt(BattleView.WIDTH - width);  
    }  
  
    @Override  
    public void step() {  
        //敌船每次向下移动一个speed长度  
        y += speed;  
    }  
  
    @Override  
    public boolean outOfBounds() {  
        //敌船y坐标>游戏界面，越界  
        return y > BattleView.HEIGHT;  
    }

}
