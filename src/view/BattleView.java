package view;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import model.Game;
import model.Player;



public class BattleView extends JPanel{
	
	private static final long serialVersionUID = 1L; 
	
	 //背景图片的大小320*568  
    public static final int WIDTH = 890;  
    public static final int HEIGHT = 568;  
    //游戏界面固定大小336*607  
    public static final int FRAME_WIDTH = 906;  
    public static final int FRAME_HEIGHT = 607;
    
    private Game game;
    
    public static JFrame frame;
    
    public void initialization(Game game0)
    {
    	this.game=game0;
    	/* 
         * java中绘制窗体：JFrame对象——窗框 
         * 要想在窗体中绘制内容，还需要嵌入背景面板——JPanel 
         */  
        frame = new JFrame("Game");  
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);//(336, 607);  
        frame.setAlwaysOnTop(true); //设置窗体置顶  
        frame.setLocation(350, 100);
        
        //设置窗体关闭同时，退出程序  
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
          
        /*在窗体中嵌入背景面板对象——JPanel*/  
        frame.add(this); //将背景面板对象嵌入到窗体对象中  
        /*窗体默认不可见！必须调用setVisible方法才能显示窗体*/  
        frame.setVisible(true); //自动调用窗体的paint方法  
    }
    
    public void paint(Graphics g) {  
        //step1: 绘制背景图片  
        g.drawImage(Game.background, 0, 0, null);  
        //step2: 绘制英雄机  
        paintHero(g);  
        //step3: 批量绘制敌人数组  
        paintFlyers(g);  
        //step4: 批量绘制子弹数组  
        paintBullets(g);  
        //绘制分数和生命值  
        paintScore_Life(g);
        //绘制boss子弹
        paintBossBullets(g);
        
        //判断boss的存在并绘制boss
        if(this.game.getBossFlag())
        {
        	paintBoss(g);
        	paintBoss_Life(g);
        }
        
        if(GameController.state == GameController.START){  
            g.drawImage(Game.start, 0, 0, null);  
        }else if(GameController.state == GameController.PAUSE){  
            g.drawImage(Game.pause, 0, 0, null);  
        }else if(GameController.state == GameController.GAME_OVER){  
            g.drawImage(Game.gameover, 0, 0, null);  
        }
    }
    
    /** 
     * 绘制英雄机对象的方法 
     * @param g 画笔 
     */  
    public void paintHero(Graphics g){  
        g.drawImage(game.player.getImage(), game.player.getX(), game.player.getY(), null);  
    }  
      
    /** 
     * 遍历敌人数组，批量绘制所有敌人的方法 
     * @param g  
     */  
    public void paintFlyers(Graphics g){  
        for(int i = 0;i < game.characters.length;i++){  
            g.drawImage(game.characters[i].getImage(), game.characters[i].getX(), game.characters[i].getY(), null);  
        }  
    }  
      
    /** 
     * 遍历子弹数组，批量绘制所有子弹的方法 
     * @param g 
     */  
    public void paintBullets(Graphics g){  
        for(int i = 0;i < game.bullets.length;i++){ 
        	if(game.bullets[i]==null)
        		break;
            g.drawImage(game.bullets[i].getImage(), game.bullets[i].getX(), game.bullets[i].getY(), null);  
        }  
    }
    /*
     * 遍历敌人子弹数组
     */
    public void paintBossBullets(Graphics g){  
        for(int i = 0;i < game.bossbullets.length;i++){  
            g.drawImage(game.bossbullets[i].getImage(), game.bossbullets[i].getX(), game.bossbullets[i].getY(), null);  
        }  
    }
    
    /** 
     * 绘制boss
     */  
    public void paintBoss(Graphics g){  
        g.drawImage(game.theBoss.getImage(), game.theBoss.getX(), game.theBoss.getY(), null);     
    }
       
    /** 
     * 绘制分数和生命值的方法 
     * @param g 
     */ 
    public void paintScore_Life(Graphics g){  
        int x = 10; //文字在左上角的x坐标  
        int y = 15; //文字在左上角的y坐标  
        Font font = new Font(Font.SANS_SERIF,Font.BOLD,16);  
        g.setFont(font); //设置字体的画笔对象  
        //绘制第一行:分数  
        g.drawString("SCORE: " + game.player.getScore(), x, y);  
        //绘制第二行：生命值，y坐标下移20个单位  
        y += 20;  
        g.drawString("LIFE: " + Player.getLife(), x, y);  
    }
    
    public void paintBoss_Life(Graphics g){  
        int x = 710; //文字在左上角的x坐标  
        int y = 15; //文字在左上角的y坐标  
        Font font = new Font(Font.SANS_SERIF,Font.BOLD,16);  
        g.setFont(font); //设置字体的画笔对象  
        //绘制第一行:分数    
        g.drawString("BOSS LIFE: " + game.theBoss.getHp(), x, y);  
    }
}
