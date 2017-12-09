package model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;  
import java.util.Arrays; 
import java.util.Random;  
  
import javax.imageio.ImageIO;

import controller.GameController;
import view.BattleView;  

public class Game{
	    
    /*  
     * 游戏启动第一件事是从硬盘加载所有要用到的图片到内存当中 
     * 而且仅在启动时加载一次——静态块 
     * 缓存在程序中的所有图片，都会反复使用，仅保存一份——静态变量 
     * 下面，为每张图片加载一个静态变量，然后在静态块加加载每张图片 
     */  
    public static BufferedImage background; //背景图片  
    public static BufferedImage start; //开始图片  
    public static BufferedImage easyenemy; //简单敌人图片
    public static BufferedImage hardenemy; //困难敌人图片  
    public static BufferedImage boss;
    public static BufferedImage hero0; //英雄状态0  
    public static BufferedImage hero1; //英雄状态1  
    public static BufferedImage bullet; //子弹  
    public static BufferedImage pause; //暂停图片  
    public static BufferedImage gameover; //游戏结束  
      
    //静态块，在类加载到方法区时执行一次，专门加载静态资源  
    static{  
        /* 
         * java从硬盘中加载图片到内存中： 
         * ImageIO.read方法：专门从硬盘中加载图片的静态方法 
         * 不用实例化，直接调用 
         */  
        try {  
            background = ImageIO.read(new File("src\\battle\\img\\background.png"));  
            easyenemy = ImageIO.read(new File("src\\battle\\img\\airplane.png"));  
            hardenemy = ImageIO.read(new File("src\\battle\\img\\bigplane.png")); 
            boss = ImageIO.read(new File("src\\battle\\img\\boss.png")); 
            bullet = ImageIO.read(new File("src\\battle\\img\\bullet.png"));  
            start = ImageIO.read(new File("src\\battle\\img\\start.png"));  
            pause = ImageIO.read(new File("src\\battle\\img\\pause.png"));  
            hero0 = ImageIO.read(new File("src\\battle\\img\\hero0.png"));  
            hero1 = ImageIO.read(new File("src\\battle\\img\\hero1.png"));  
            gameover = ImageIO.read(new File("src\\battle\\img\\gameover.png"));  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
      
    /* 
     * 为游戏中的角色定义数据结构，包括： 
     * 1个英雄对象 
     * 1个存储所有敌人的对象数组 
     * 1个存储所有子弹的对象数组 
     */  
    public Player player = new Player(0); //传入参数为角色当前血量
    public Character[] characters = {}; //存储所有敌人对象的数组  
    public Bullet[] bullets = {}; //存储所有子弹对象的数组   
    public BossBullet[] bossbullets={};//储存所有敌方boss的子弹数组
    public Boss theBoss=new Boss();//敌方boss
    private boolean bossflag=false;//bool类型表示boss是否存在
    
    
    public void setBossFlag()
    {
    	bossflag=false;
    }
    public boolean getBossFlag()
    {
    	return this.bossflag;
    }
    
    public void creatBoss()
    {
    	this.theBoss=new Boss();
    	bossflag=true;
    	this.theBoss.beginShoot();
    }
    
    /** 
     * 随机生成1个敌人对象 
     * 每生成一个新敌人， flyers数组就要扩容1 
     * 然后将新敌人放入数组最后一个元素 
     */  
    public void nextOne(){  
        Random r = new Random();  
        Character f = null;  
        if(r.nextInt(20) == 0){ //只有随机数取0时才创建困难敌人  
            f = new HardEnemy();  
        }else{ //其余全部生成简单敌人
            f = new EasyEnemy();  
        }  
        //对数组扩容1  
        characters = Arrays.copyOf(characters, characters.length + 1);  
        //将新敌人放入数组末尾  
        characters[characters.length - 1] = f;  
    }  
      
    /** 
     * 获得英雄对象发射的子弹对象 
     * 将新的子弹对象保存到子弹数组中，统一管理 
     */  
    public void shoot(){  
        Bullet[] newBullets = player.shoot(); //获得英雄返回的新子弹数组  
        //根据返回新子弹的数量，扩容子弹数组  
        bullets = Arrays.copyOf(bullets, bullets.length + newBullets.length);  
        //从newBullets数组中拷贝所有元素到bullets数组末尾  
        System.arraycopy(newBullets, 0, bullets, bullets.length - newBullets.length, newBullets.length);    
    }  
    
    public void bossshoot()
    {
    	Bullet[] newBullets = theBoss.shoot(); //获得boss返回的新子弹数组  
        //根据返回新子弹的数量，扩容子弹数组  
    	bossbullets = Arrays.copyOf(bossbullets, bossbullets.length + newBullets.length);  
        //从newBullets数组中拷贝所有元素到bullets数组末尾  
        System.arraycopy(newBullets, 0, bossbullets, bossbullets.length - newBullets.length, newBullets.length);
    }
      
    /** 
     * 遍历子弹数组和敌人数组，进行碰撞检测 
     * 一旦发生碰撞，子弹和敌人都减少一个 
     */  
    public void boom(){  
        for(int i = 0;i < bullets.length;i++){  
            for(int j = 0;j < characters.length;j++){  
                if(Character.boom(bullets[i], characters[j])){  
                    //为英雄获得分数和奖励  
                    player.addScore(characters[j]);  
                    //从敌人数组中删除被击中的敌人
                    //step1： 使用敌人数组最后一个元素替换被击中的敌人  
                    characters[j] = characters[characters.length - 1];  
                    //step2: 压缩数组  
                    characters = Arrays.copyOf(characters, characters.length - 1);  
                    //从子弹数组中删除击中敌人的子弹  
                    bullets[i] = bullets[bullets.length - 1];  
                    bullets = Arrays.copyOf(bullets, bullets.length -1);  
                    i--; //第发现一次碰撞，子弹就要退一个元素，重新检测当前位置  
                    break; //只要发现碰撞就退出当前敌人数组的循环  
                }
            } 
        }
        for(int i = 0;i < bullets.length;i++){ 
            if(bossflag&&Character.boom(bullets[i], theBoss))
            {
            	theBoss.takeDamage();
            	if(theBoss.getHp()<=0)
            	{
            		player.addScore(theBoss);
            		bossflag=false;
            		theBoss=null;  
            		GameController.state=GameController.GAME_OVER;
            	}
            	//从子弹数组中删除击中敌人的子弹  
                bullets[i] = bullets[bullets.length - 1];  
                bullets = Arrays.copyOf(bullets, bullets.length -1);  
                i--; //第发现一次碰撞，子弹就要退一个元素，重新检测当前位置  
                break; //只要发现碰撞就退出当前敌人数组的循环  
            }
        }  
    }  
    
    /*
     * 遍历敌人子弹数组，判断与玩家之间的碰撞
     */
    public void bossboom(){  
        for(int i = 0;i < bossbullets.length;i++){  
            	if(player.hit(bossbullets[i])){   
                //从子弹数组中删除击中玩家的子弹  
                bossbullets[i] = bossbullets[bossbullets.length - 1];  
                bossbullets = Arrays.copyOf(bossbullets, bossbullets.length -1);  
                i--; //每一次碰撞，子弹就要退一个元素，重新检测当前位置
                
                if(player.getLife() <= 0){ //如果英雄生命值小于等于0，游戏结束   
                    GameController.state = GameController.GAME_OVER; 
                    break;
                }
            }
        } 
    }
         
    /** 
     * 检查所有角色是否越界 
     */  
    public void outOfBounds(){  
        //检查所有敌人是否越界  
        Character[] enemies = new Character[characters.length];  
        //遍历敌人数组，将存活的敌人对象存到新数组中   
        int index = 0;  
        for(int i = 0;i < characters.length;i++){  
            if(!characters[i].outOfBounds()){ //没有越界的对象  
                enemies[index] = characters[i];  
                index++;  
            } //遍历结束后：  
            //index是存活对象的个数  
            //enemies数组里是存活的对象，个数为index  
            //把enemies数组压缩为index大小  
            //压缩后的新数组 应替换回characters数组  
        }  
        characters = Arrays.copyOf(enemies, index);  
          
        //检测所有子弹是否越界  
        Bullet[] Blives = new Bullet[bullets.length];  
        index = 0;  
        for(int i = 0;i < bullets.length;i++){  
            if(!bullets[i].outOfBounds()){  
                Blives[index] = bullets[i];  
                index++;  
            }  
        }  
        bullets = Arrays.copyOf(Blives, index); 
        
        
        //检测敌人子弹是否越界
        BossBullet[] BossBlives = new BossBullet[bossbullets.length];  
        index = 0;  
        for(int i = 0;i < bossbullets.length;i++){  
            if(bossbullets[i]!=null&&!bossbullets[i].outOfBounds()){  
                BossBlives[index] = bossbullets[i];  
                index++;  
            }  
        }  
        bossbullets = Arrays.copyOf(BossBlives, index);
    }  
      
    /** 
     * 遍历敌人数组，判断英雄和每个敌人是否碰撞 
     */  
    public void hit(){  
        Character[] lives = new Character[characters.length];  
        //记录存活的敌人  
        int index = 0;  
        for(int i = 0;i < characters.length;i++){  
            if(!player.hit(characters[i])){  
                lives[index] = characters[i];  
                index++;  
            }  
        }  
        if(player.getLife() <= 0){ //如果英雄生命值小于等于0，游戏结束   
            GameController.state = GameController.GAME_OVER;  
        }  
        //压缩敌人数组，并替换数组  
        characters = Arrays.copyOf(lives, index);  
          
    }  
}
