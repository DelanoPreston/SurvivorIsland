package Main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		try{
		JFrame frame = new JFrame();
		ContentBank.ContentLoader();
		GamePanel gamePanel = new GamePanel();
		frame.setSize(850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(gamePanel);
		frame.setVisible(true);
		}catch(Exception e){
//			IOClass io = new IOClass();
			e.printStackTrace();
		}
		//https://github.com/richardlord/Asteroids
		//https://github.com/richardlord/Ash
		//http://www.ashframework.org/
	}

}
