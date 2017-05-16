import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		// we are tying to create a jframe here
		JFrame frame = new JFrame("Block breaker by Deepanshu Lulla");
		BlockBreakerPanel panel= new BlockBreakerPanel();
		frame.getContentPane().add(panel);
		frame.setVisible(true);//setting frame to visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// the game may keep running even if we close the window
		//what will end up if we don't do this, we may end up with a lot of processes unnecessarily running in background
		frame.setSize(1024, 960);
		frame.setResizable(false);
		
		
		
	}

}
