package model;

import java.util.Random;

import view.BattleView;


/** 
 * 封装困难敌船属性和功能的类 
 */  
public class HardEnemy extends Character{
      
    /*敌舰类私有成员*/  
    private int xspeed = 1; //水平移动的速度为1  
    private int yspeed = 2; //垂直移动的速度为2  
    private int score=5;
      
    //对外提供的读取敌舰分数
    public int getScore(){  
        return score;  
    }  
      
    /** 
     * 敌舰的无参构造方法 
     */  
    public HardEnemy(){  
        //step1: 从主程序中获取敌舰图片的静态变量
        image = Game.hardenemy;  
        //step2: 使用图片宽高设置对象宽高  
        width = image.getWidth();  
        height= image.getHeight();  
        //step3: 设置敌舰开始下落的高度  
        y = -height;  
        //step4:  敌舰对象开始下落的x坐标在0~（界面宽度 - 敌舰图片宽度）之前随机  
        Random r = new Random();  
        x = r.nextInt(BattleView.WIDTH - width);   
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
    }  
  
    @Override  
    public boolean outOfBounds() {  
        //y坐标>游戏界面，越界  
        return y > BattleView.HEIGHT;  
    } 

}
