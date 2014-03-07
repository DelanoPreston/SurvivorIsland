package Main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		ContentBank.ContentLoader();
		JFrame frame = new JFrame();
		GamePanel gamePanel = new GamePanel();
		frame.setSize(275, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(gamePanel);
		frame.setVisible(true);
	}

}
