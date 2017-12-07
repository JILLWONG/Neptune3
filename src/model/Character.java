package model;

import java.awt.image.BufferedImage;

/** 
 * 封装所有船的公共属性和功能的父类 
 */ 
public abstract class Character {
	
	protected int x; //船左上角x坐标  
    protected int y; //船左上角y坐标  
    protected int height; //船的高度  
    protected int width; //船的宽度  
    protected BufferedImage image; //船的图片  
	
    
    /** 
     * 要求所有船必须都能移动 
     * 但移动的方式由子类自己实现 
     * 因为不同类型的船移动方式不同
     */  
    public abstract void step();  
      
    /** 
     * 检查越界的方法 
     * @return 是否越界 
     */  
    public abstract boolean outOfBounds();  
      
    /** 
     * 专门检测两个矩形是否碰撞的工具方法 
     * 和具体对象无关，所以定义为静态方法 
     * @param f1 船对象1 
     * @param f2 船对象2 
     * @return 是否碰撞 
     */  
    public static boolean boom(Character f1,Character f2){  
        //step1: 求出两个矩形的中心点  
        int f1x = f1.x + f1.width/2;  
        int f1y = f1.y + f1.height/2;  
        int f2x = f2.x + f2.width/2;  
        int f2y = f2.y + f2.height/2;  
        //step2: 横向和纵向碰撞检测  
        boolean H = Math.abs(f1x - f2x) < (f1.width + f2.width)/2;  
        boolean V = Math.abs(f1y -f2y) < (f1.height + f2.height)/2;  
        //step3: 必须两个方向同时碰撞  
        return H&V;  
    }
    
    public int getX()
    {
    	return this.x;
    }
    public int getY()
    {
    	return this.y;
    }
    public int getHeight()
    {
    	return this.height;
    }
    public int getWidth()
    {
    	return this.width;
    }
    public BufferedImage getImage()
    {
    	return this.image;
    }
    
    public void setX(int X)
    {
    	this.x=X;
    }
    public void setY(int Y)
    {
    	this.y=Y;
    }
    public void setHeight(int Height)
    {
    	this.height=Height;
    }
    public void setWidth(int Width)
    {
    	this.width=Width;
    }
    public void setImage(BufferedImage Image)
    {
    	this.image=Image;
    }
}
