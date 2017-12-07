package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import model.Boss;
import model.BossBullet;
import model.Bullet;
import model.Game;
import model.Character;
import model.Player;
import view.BattleView;
import view.ContentPane;



public class GameController {
	
	private BattleView battleView;
	private Game game;
	  
    //定义游戏状态的备选项常量：  
    public static final int START = 0;  
    public static final int RUNNING = 1;  
    public static final int PAUSE = 2;  
    public static final int GAME_OVER = 3;
    public static final int PAUSE2 = 4;
    
  //定义游戏状态：当前状态变量：默认为开始状态  
    public static int state = START; 
    
    //首先定义一个计时器变量index，记录run方法运行的次数   
    private int runTimes = 0; 
    
    public GameController()
    {
    	this.game=new Game();
    	this.battleView=new BattleView();
    	this.battleView.initialization(game);
    }

    /*
     * 添加事件监听
     */
    void listener()
    {
        //step1: 创建MouseAdapter匿名内部类——事件的响应程序  
        MouseAdapter l = new MouseAdapter(){  
            //step2: 重写希望的鼠标事件——鼠标移动  
            @Override  
            public void mouseMoved(MouseEvent e) {  
                //只有在RUNNING状态下英雄机才跟随鼠标移动  
                if(state == RUNNING){  
                    //step3: 获得鼠标新位置  
                    int x = e.getX();  
                    int y = e.getY();  
                    //step4: 将鼠标位置传给英雄机的move方法  
                    game.player.move(x, y);  
                }  
            }  
  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                if(state == START || state == PAUSE){ //START或者PAUSE状态，单击才会改改为RUNNING状态  
                    state = RUNNING;  
                }else if(state == RUNNING){ //游戏点击暂停  
                    state = PAUSE;  
                }else if(state == GAME_OVER){ //游戏结束后单击，游戏初始化                 	
                    state = START;  
                    //从GAME_OVER到START，要重新初始化游戏数据  
                    game.characters = new Character[0];   
                    game.bullets = new Bullet[0];  
                    //game.player = new Player(0);  
                    game.bossbullets=new BossBullet[0];
                    game.setBossFlag();
                    game.theBoss=new Boss();
                    runTimes=0;
                    BattleView.frame.dispose();
                    ContentPane.tattr[0].setText(""+Player.hp);//edit-add
                    ContentPane.picture.setVisible(false);//edit-add
                    ContentPane.picture.setVisible(true);//edit-add
                    StartGame.gameOver();
                }  
            }  
  
  
            @Override  
            public void mouseExited(MouseEvent e) {  
                if(state == RUNNING){  
                    //仅在处于RUNNING状态下，鼠标移出才暂停  
                    state = PAUSE2;  
                }  
            }  
              
            @Override  
            public void mouseEntered(MouseEvent e) {  
                if(state == PAUSE2){  
                    state = RUNNING;  
                }  
            }  
  
        }; //匿名内部类要以分号结尾  
        /*step5: 要响应鼠标事件，必须将鼠标事件添加到程序的监听器中*/  
        battleView.addMouseMotionListener(l); //支持鼠标的移动事件，不支持鼠标单击事件  
        battleView.addMouseListener(l);; //支持鼠标单击事件  
    }
    
    /*
     * 完成游戏主逻辑
     */
    void action(){         
        //step1: 创建定时器  
        Timer timer = new Timer();  
        //step2: 调用定时器对象的schedule方法，做计划  
        //       第一个参数：TimerTask类型的匿名内部类  
        //               必须重写run方法——核心——要做什么事  
        timer.schedule(new TimerTask(){  
  
            @Override  
            public void run() {  
                //除了repaint方法，其余功能只在RUNNING状态下执行  
                if(state == RUNNING){  
                    //每执行一次run方法，runTimes就+1  
                    runTimes++;  
                      
                    //每500亳秒生成一次敌人  
                    if(runTimes % 50 == 0&&!game.getBossFlag()){  
                        game.nextOne(); //自动随机创建敌人对象  
                    }  
                    if(runTimes % 150 == 0&&game.getBossFlag()){  
                        game.nextOne(); //自动随机创建敌人对象  
                    }
                    //遍历每一个对象，调用对象的step方法，移动一次对象的位置  
                    for(int i = 0;i < game.characters.length;i++){  
                        game.characters[i].step();  
                    }  
                      
                    //每300亳秒生成一次子弹  
                    if(runTimes % 30 == 0){  
                        game.shoot(); //创建一次子弹  
                    }  
                    //遍历子弹数组的每一个对象，移动位置  
                    for(int i = 0;i < game.bullets.length;i++){  
                        game.bullets[i].step();  
                    } 
                    
                    if(runTimes % 1500==0&&!game.getBossFlag())
                    {
                    	game.creatBoss();
                    }
                      
                    //英雄机动画效果  
                    game.player.step();
                      
                    //添加子弹和敌人的碰撞检测  
                    game.boom(); 
                   
                    if(game.getBossFlag())
                    {
                    	game.theBoss.step();
                    	game.bossboom();
                    	if(runTimes%50==0)
                    		game.bossshoot();
                    	if(runTimes%300==0)
                    		game.theBoss.beginShoot();
                    }
                    
                    //遍历子弹数组的每一个对象，移动位置  
                    for(int i = 0;i < game.bossbullets.length;i++){  
                        game.bossbullets[i].step();  
                    }
                      
                    //英雄机碰撞检测  
                    game.hit();  
                      
                    //添加越界检测  
                    game.outOfBounds();  
                }     
                /*强调：只要界面发生变化，必须调用repaint方法重新绘制界面*/  
                battleView.repaint();  
            }        
        }, 10,10); //界面每隔10亳秒变化一次       
    }  
  
}
