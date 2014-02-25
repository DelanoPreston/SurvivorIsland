package Main;

import javax.swing.JFrame;

public class MainObjectCreator {
	public static void main(String[] args) {
		ContentBank.ContentLoader();
		JFrame frame = new JFrame();
		ItemCreatorPanel itemCreatorPanel = new ItemCreatorPanel();
		frame.setSize(650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(itemCreatorPanel);
	}
}
