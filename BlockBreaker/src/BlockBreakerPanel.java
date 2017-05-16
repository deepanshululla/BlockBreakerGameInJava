import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener{
	ArrayList<Block> blocks=new ArrayList<Block>();
	ArrayList<Block> ball=new ArrayList<Block>();
	ArrayList<Block> powerupList=new ArrayList<Block>();
	Block paddle;
	Thread thread;
	Animate animate;
	int ballSize=50;//ballSize of ball
	int countDestroyed=0;//no. of blocks destroyed
	int paddleWidth=200;
	int paddleHeight=75;
	int ballXCor=237;
	int ballYCor=437;
	BlockBreakerPanel(){
		//add blocks to arraylist
		paddle = new Block(200,800,paddleWidth,paddleHeight,"paddle.png");
		for(int i=0;i<10;i++){
			blocks.add(new Block((i*100+2),0, 100, 75,"blue.png"));
		}
		for(int i=0;i<10;i++){
			blocks.add(new Block((i*100+2),75, 100, 75,"red.png"));
		}
		for(int i=0;i<10;i++){
			blocks.add(new Block((i*100+2),150, 100, 75,"yellow.png"));
		}
		for(int i=0;i<10;i++){
			blocks.add(new Block((i*100+2),225, 100, 75,"green.png"));
		}
		Random random=new Random();
		blocks.get(random.nextInt(40)).powerup=true;//choosing a random blcok out of 40 blocks
		blocks.get(random.nextInt(40)).powerup=true;
		blocks.get(random.nextInt(40)).powerup=true;
		blocks.get(random.nextInt(40)).powerup=true;
		blocks.get(random.nextInt(40)).powerup=true;
		blocks.get(random.nextInt(40)).powerup=true;
		blocks.get(random.nextInt(40)).powerup=true;
		ball.add(new Block(ballXCor,ballYCor,ballSize,ballSize,"ball.png"));
		//need to add the listener
		addKeyListener(this);
		setFocusable(true);//
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);//erase the screen and reprint it
		for(Block b: blocks){
			b.draw(g,this);	
		}
		for(Block b: ball){
			b.draw(g,this);	
		}
		for(Block p:powerupList){
			p.draw(g, this);
		}
		paddle.draw(g, this);
	}
	public void update(){
		if(countDestroyed<40){
			for(Block p: powerupList){
				p.y+=1;
				if (p.intersects(paddle) && !p.destroyed){
					p.destroyed=true;
					ball.add(new Block(paddle.dx+(paddleWidth/2),ballYCor,ballSize,ballSize,"ball.png"));
				}
			}
			for(Block ba: ball){
				ba.x+=ba.dx;
				
				if(ba.x>(getWidth()-ballSize) && ba.dx>0 || ba.x<0){
					//the first checks if the ball doesn't go out of the window, the second checks if the ball is moving right
					ba.dx*=-1;//change the direction of ball to left
				}
				if(ba.y<0 || ba.intersects(paddle)){
					ba.dy*=-1;//if ball intersects the paddle or tries to exit out of the window flip the direction in y direction
				}
				for(Block b:blocks){
					if ((b.leftRect.intersects(ba)|| b.rightRect.intersects(ba)) && !b.destroyed ){
						//so this if block essentially differentiates when the block hits the side of block vs it hits anywhere else
						ba.dx*=-1;
						b.destroyed=true;
						countDestroyed+=1;
						if (b.powerup){
							powerupList.add(new Block(b.x,b.y,ballSize,ballSize,"extra.png"));
						}
					}
					else if(ba.intersects(b) && !b.destroyed){
						b.destroyed=true;
						countDestroyed+=1;
						ba.dy*=-1;
						if (b.powerup){
							powerupList.add(new Block(b.x,b.y,ballSize,ballSize,"extra.png"));
						}
					}
				}
				ba.y+=ba.dy;	
			}
		}	
		else{
			System.out.println("Congrats You won");
			System.exit(ABORT);
		}
		repaint();//this will repaint it
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			//we will start the thread
			//System.out.println("Enter pressed");
			animate=new Animate(this);
			thread =new Thread(animate);
			thread.start();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x>1 ){
			//what happens when we press the left arrow key
			//System.out.println("left key pressed");
			paddle.x=paddle.x-55;//change paddle position to left or decrease x coordinate vale of it
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x<(getWidth()-paddle.width-10)){
			//what happens when we press the right arrow key
			//System.out.println("right key pressed");
			paddle.x=paddle.x+55;//increase x coordinate or move paddle to right
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
