package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StartGame;
import model.B2enemy;
import model.Player;
/**
 *
 * 躲避游戏——杜欣伟
 * 
 */
public class Battle2View extends JPanel implements KeyListener{
	int BATTLE_H=520;
	int BATTLE_W=950;
	Battle2Attr leftAera;
	M2BattleView view;
	/**船的坐标*/
	public static int x;
	public static int y;
	/**终点的坐标*/
	int endX;
	int endY;
	public static int enemyNum=8;
	public static int enemyLeft;
	int speed=10;
	int step=5;
	public static int blood=100;
	boolean U=false,D=false,L=false,R=false; 
	private B2enemy[] enemy=new B2enemy[enemyNum];

	public Battle2View(Battle2Attr leftAera,M2BattleView view) {
		this.leftAera=leftAera;
		this.view=view;
		enemyLeft=enemyNum;
		x=40;
		y=300;
		/**玩家*/
		M2BattleView.boat = new JLabel("");
		M2BattleView.boat.setIcon(new ImageIcon("img\\boat.png"));
		M2BattleView.boat.setBounds(x, y, 100, 100);
	    this.add(M2BattleView.boat);
	    for(int i=0;i<enemyNum;i++) {
	    	enemy[i]= new B2enemy();
	    	do {
	    	enemy[i].enemySX=(int)(Math.random()*(BATTLE_W-100))+100;
	    	enemy[i].enemySY=(int)(Math.random()*(BATTLE_H-57))+57;
	    	enemy[i].enemyEX=(int)(Math.random()*(BATTLE_W-100))+100;
	    	enemy[i].enemyEY=(int)(Math.random()*(BATTLE_H+57))-57;
	    	int sx=(int)(Math.random()*(40))+40;
	    	enemy[i].enemyKX=(enemy[i].enemyEX-enemy[i].enemySX)/sx;
	    	enemy[i].enemyKY=(enemy[i].enemyEY-enemy[i].enemySY)/sx;
	    	}while(Math.abs(enemy[i].enemyEX-enemy[i].enemySX)<100);
	    	
	    	enemy[i].enemyX=enemy[i].enemySX;
	    	enemy[i].enemyY=enemy[i].enemySY;
	    	enemy[i].enemyW=false;
	    	System.out.println(enemy[i].enemyKX+" "+enemy[i].enemySX);
		    this.add(enemy[i].enemy);
	    }
	    /*JLabel boss = new JLabel("");
	    boss.setIcon(new ImageIcon("img\\boss.png"));
	    boss.setBounds(300, 30, 150, 109);
	    this.add(boss);*/
	    JLabel destination = new JLabel("");
	    destination.setIcon(new ImageIcon("img\\destination.png"));
	    endX=(int)(Math.random()*200)+600;
	    endY=(int)(Math.random()*400)+100;
	    destination.setBounds(endX, endY, 80, 45);
	    this.add(destination);
	    
	}
    public void paint(Graphics g)  
    {  
        super.paint(g);  
        M2BattleView.boat.setBounds(x, y, 100, 100);
        for(int i=0;i<enemyNum;i++) {
        	enemy[i].enemy.setBounds(enemy[i].enemyX, enemy[i].enemyY, 50, 57);
        }
        if(x<0) {
			x=0;
			L = false;
		}
		else if(x>BATTLE_W) {
			x=BATTLE_W;
			R = false; 
		}
		if(y<0) {
			y=0;
			U =false;
		}
		else if(y>BATTLE_H) {
			y=BATTLE_H;
			D =false;
		}
        run();  
		check_collision();
        try {  
            Thread.sleep(20);  
        } catch (InterruptedException e) {  
            // TODO 自动生成的 catch 块  
            e.printStackTrace();  
        }  
        repaint();
    }  
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {  
		case KeyEvent.VK_A:
        case KeyEvent.VK_LEFT:  
            L=true;  
            break;  
        case KeyEvent.VK_D:
        case KeyEvent.VK_RIGHT:  
            R=true;  
            break;  
        case KeyEvent.VK_W:
        case KeyEvent.VK_UP:  
            U=true;  
            break;  
        case KeyEvent.VK_S:
        case KeyEvent.VK_DOWN:  
            D=true;  
            break;  
        }  
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		 switch (e.getKeyCode()) { 
		 case KeyEvent.VK_A:
         case KeyEvent.VK_LEFT:  
             L=false;  
             break;  
         case KeyEvent.VK_D:
         case KeyEvent.VK_RIGHT:  
             R=false;  
             break;  
         case KeyEvent.VK_W:
         case KeyEvent.VK_UP:  
             U=false;  
             break;  
         case KeyEvent.VK_S:
         case KeyEvent.VK_DOWN:  
             D=false;  
             break;  
         }  
     }  
	private void check_collision() {
		int xl=x-20;
		int xr=x+20;
		int yu=y-35;
		int yd=y+35;
		/**检查是否碰撞到敌人*/
		for(int i=0;i<enemyNum;i++) {
			if(!enemy[i].enemy.isVisible()) {
				continue;
			}
			int el=enemy[i].enemyX-25;
			int er=enemy[i].enemyX+25;
			int eu=enemy[i].enemyY-28;
			int ed=enemy[i].enemyY+28;
			if(check_in(xl,yu,el,eu,er,ed)||check_in(xl,yd,el,eu,er,ed)||check_in(xr,yu,el,eu,er,ed)||check_in(xr,yd,el,eu,er,ed)) {
				enemy[i].enemy.setVisible(false);
				Player.hp-=20;
				enemyLeft--;
				if(Player.hp<=0) {
					over(0);//gameover
				}
				leftAera.repaint();
			}
		}
		/**检查是否到达终点*/
		int l=endX-20;
		int r=endX+20;
		int u=endY-20;
		int d=endY+20;
		if(check_in(xl,yu,l,u,r,d)||check_in(xl,yd,l,u,r,d)||check_in(xr,yu,l,u,r,d)||check_in(xr,yd,l,u,r,d)) {
			System.out.println("in");
			over(1);//finished
		}
		
		
	}
	private boolean check_in(int x,int y,int lx,int ly,int rx,int ry) {
		if(x>=lx&&x<=rx&&y>=ly&&y<=ry) {
			return true;
		}
		return false;
	}
	public void over(int status) {//0为游戏失败，1为完成游戏
		Battle2Over overView=new Battle2Over("海神",status);
		/**奖励 属性改变*/
		if(status==0) {
			ContentPane.tattr[0].setText(""+Player.hp);//edit-add
            ContentPane.textLabel2.append(StartGame.date+"："+"解救失败\n");
            ContentPane.picture.setVisible(false);//edit-add
            ContentPane.picture.setVisible(true);//edit-add
            			
		}else if(status==1) {
			ContentPane.tattr[0].setText(""+Player.hp);//edit-add
            ContentPane.textLabel2.append(StartGame.date+"："+"成功解救\n");
            ContentPane.picture.setVisible(false);//edit-add
            ContentPane.picture.setVisible(true);//edit-add
		}
		StartGame.gameOver();
	    view.dispose();
	}
	public void run(){  
		for(int i=0;i<enemyNum;i++) {
			if(enemy[i].enemyW) {//正向移动
				enemy[i].enemyX+=enemy[i].enemyKX;
				enemy[i].enemyY+=enemy[i].enemyKY;
			}else {
				enemy[i].enemyX-=enemy[i].enemyKX;
				enemy[i].enemyY-=enemy[i].enemyKY;
			}
			if(enemy[i].enemyX<100||enemy[i].enemyX>950||enemy[i].enemyY<0||enemy[i].enemyY>620) {
				enemy[i].enemyW=!enemy[i].enemyW;
			}
		}
        if(!U&&!D&&L&&!R){  
            x-=step;  
        }else if(!U&&!D&&!L&&R){  
            x+=step;  
        }else if(U&&!D&&!L&&!R){  
            y-=step;  
        }else if(!U&&D&&!L&&!R){  
            y+=step;  
        }else if(U&&!D&&L&&!R){  
            x-=step;  
            y-=step;  
        }else if(!U&&D&&L&&!R){  
            x-=step;  
            y+=step;  
        }else if(U&&!D&&!L&&R){  
            x+=step;  
            y-=step;  
        }else if(!U&&D&&!L&&R){  
            x+=step;  
            y+=step;  
        }  
    } 
}
