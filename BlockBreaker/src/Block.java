import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle{
	Image pic;
	int dx=10;
	int dy=-10;
	Rectangle leftRect,rightRect;
	boolean destroyed=false;
	boolean powerup=false;
	Block(int xCor,int yCor,int wid,int heit, String s){
		//constructor that takes 4 arguments
		x=xCor;
		y=yCor;
		width=wid;
		height=heit;
		leftRect=new Rectangle(xCor-1,yCor,1,heit);
		rightRect=new Rectangle(xCor+wid+1,yCor,1,heit);
		pic = Toolkit.getDefaultToolkit().getImage(s);
	}
	public void draw(Graphics g, Component c){
		if (!destroyed){
			g.drawImage(pic,x,y,width,height,c);
		}
	}
}
