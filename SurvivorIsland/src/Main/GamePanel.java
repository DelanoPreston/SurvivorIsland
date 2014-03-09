package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

import Entity.Entity;
import Entity.ItemEntity;
import Entity.WallEntity;
import Event.CustomEventSource;
import Items.Tool;
import Items.ToolType;
import People.Human;
import People.Survivor;

/**
 * GamePanel class that extends JPanel
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	int timer = 0;// if I load a game, this will be reset to 0 causing problems with crops and other timed things
	Timer mainTimer;
	PopupListener popupListener;
	IOClass iostuff;
	public Level level;
	CustomEventSource source;
	double translateX = 0;
	double translateY = 0;
	double scale = 1.0;
	
//	public static Random random;
//	AffineTransform holder;

	/**
	 * Constructor for the GamePanel class that extends JPanel
	 */
	public GamePanel() {
//		random = new Random();
		setFocusable(true);

		level = new Level();
		source = new CustomEventSource();
		source.addEventListener(level);

		// creates the popup menu
		createPopupMenu();

		// creates the menu layout
		createLayout();

		// creates the button layout
		createButtonLayout();
		
		

		// adds the keyboard listener for keyboard input
		addKeyListener(new KeyboardListener());
		this.addMouseListener(popupListener);
		this.addMouseMotionListener(popupListener);
		this.addMouseWheelListener(popupListener);

		// makes the file io class, and gets the map data
		iostuff = new IOClass();
		level.setMap(iostuff.ReadMap());

		// timer for updating game every 17 miliseconds
		mainTimer = new Timer(17, new TimerListener());
		mainTimer.start();
	}

	/**
	 * Update Method, Action performed calls this to update game
	 */
	public void Update() {
		if (level != null)
			level.update();
	}

	/**
	 * Paint Method, Action performed repaint to paint the game
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		AffineTransform holder = new AffineTransform();

		holder.translate(getWidth() / 2, getHeight() / 2);
		holder.scale(scale, scale);
		holder.translate(-getWidth() / 2, -getHeight() / 2);

		holder.translate(translateX, translateY);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setTransform(holder);

		if (level != null)
			level.paintComponent(g2D);

		// this resets the at for the j components to draw normally
		AffineTransform at = new AffineTransform();
		g2D.setTransform(at);
	}

	/**
	 * TimerListener class, implements ActionListener, this class only calls the update methods that run for every cycle/scene of the game
	 * 
	 * @author Preston Delano
	 */
	class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Update();
			repaint();

			// if(timer >= 25){
			// System.out.println(timer);
			// }
			// timer++;
		}
	}

	/**
	 * KeyboardListener class, implements ActionListener, this class is used when there is a key press, release, or type
	 * 
	 * @author Preston Delano
	 * 
	 */
	class KeyboardListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {

			int key = arg0.getKeyCode();

			if (key == KeyEvent.VK_SPACE) {
				System.out.println("nothing");
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			int key = arg0.getKeyCode();

			if (key == KeyEvent.VK_SPACE) {
				System.out.println("nothing");
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			int key = arg0.getKeyCode();

			if (key == KeyEvent.VK_SPACE) {
				System.out.println("nothing");
			}
		}
	}

	/**
	 * MenuListener class, implements ActionListener, this class is used when a menu item is selected, clicked or other things
	 * 
	 * @author Preston Delano
	 * 
	 */
	class MenuListener implements ActionListener {
		GamePanel ref;

		public MenuListener(GamePanel inref) {
			ref = inref;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.paramString().contains("new survivor")) {
				Location loc = new Location(popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY());
				// Location loc = ref.source.getTileAtLocation(tempLoc);
				Survivor survivor = new Survivor("Rob", loc, 150, true, ref.source);
				level.addHuman(survivor);
			} else if (arg0.paramString().contains("new axe")) {
				Location loc = new Location(popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY());
				// Location loc = ref.source.getTileAtLocation(tempLoc);
				Tool axe = new Tool("axe", 2.5, 100, ToolType.AXE, 100);
				ItemEntity axeEntity = new ItemEntity(axe, loc);
				level.addItem(axeEntity);
			} else if (arg0.paramString().contains("new wall")) {
				Location loc = new Location(popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY());
				// Location loc = ref.source.getTileAtLocation(tempLoc);
				System.out.println("GamePanel: " + loc.getMapX() + "," + loc.getMapY());
				WallEntity wall = new WallEntity("Wall", loc, 3.4, true);
				// FurnitureEntity fEntity = new FurnitureEntity(chest, loc);
				level.addStructure(wall);
			} else if (arg0.paramString().contains("Save")) {
				ref.iostuff.saveLevel(ref.level, "name");
			} else if (arg0.paramString().contains("Load")) {
				level = ref.iostuff.loadLevel("name");
			} else if (arg0.paramString().contains("Exit")) {
				System.exit(-1);
			}
			// else if(arg0.paramString().contains("Poison")){
			// NewTower(ContentBank.TowerType.Poison);
			// }
		}
	}

	/**
	 * ButtonListener class, implements ActionListener, this class is used when a button is clicked
	 * 
	 * @author Preston Delano
	 * 
	 */
	private class ButtonListener implements ActionListener {
		// ItemCreatorPanel ref;

		public ButtonListener() {// ItemCreatorPanel inRef) {
			// ref = inRef;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			 if (arg0.getActionCommand().equals("structure")) {
				 JButton tempButton = (JButton)arg0.getSource();
				 tempButton.setVisible(false);
			 } //else if (arg0.getActionCommand().equals("Add")) {
			// if (!nameTA.getText().equals("") && !nameTA.getText().equals(null)) {
			// String tempName = nameTA.getText();
			// if (!weightTA.getText().equals("") && !weightTA.getText().equals(null)) {
			// double tempWeight = Double.parseDouble(weightTA.getText());
			// if (!conditionTA.getText().equals("") && !conditionTA.getText().equals(null)) {
			// int tempCondition = Integer.parseInt(conditionTA.getText());
			// switch (itemType) {
			// case "Item":
			// Item tempItem = new Item(tempName, tempWeight, tempCondition);
			// items.add(tempItem);
			// break;
			// case "Food":
			// if (!replenishmentTA.getText().equals("") && !replenishmentTA.getText().equals(null)) {
			// int tempReplen = Integer.parseInt(replenishmentTA.getText());
			// Food tempFood = new Food(tempName, tempWeight, tempCondition, tempReplen);
			// items.add(tempFood);
			// }
			// break;
			// case "Furniture":
			// Furniture tempFurniture = new Furniture(tempName, tempWeight, tempCondition);
			// items.add(tempFurniture);
			// break;
			// case "Tool":
			// if (!durabilityTA.getText().equals("") && !durabilityTA.getText().equals(null)) {
			// int tempDura = Integer.parseInt(durabilityTA.getText());
			// Tool tempTool = new Tool(tempName, tempWeight, tempCondition, ToolType.valueOf(toolTypeArray[toolTypeCB.getSelectedIndex()]), tempDura);
			// items.add(tempTool);
			// }
			// break;
			// }
			// }
			// }
			// }
			//
			// // setTextArea();
			// } else if (arg0.getActionCommand().equals("Edit")) {
			// //durabilityL, replenishmentL, toolTypeL; durabilityTA, replenishmentTA;
			// } else if (arg0.getSource().toString().contains("Plain Item")) {
			// // durabilityL.setVisible(false);
			// // durabilityTA.setVisible(false);
			// // replenishmentL.setVisible(false);
			// // replenishmentTA.setVisible(false);
			// // toolTypeL.setVisible(false);
			// // toolTypeCB.setVisible(false);
			// //paintImmediately(0,0,600,600);
			// isPaintingOrigin();
			// // itemType = "Item";
			// } else if (arg0.getSource().toString().contains("Tool Item")) {
			// // durabilityL.setVisible(true);
			// // durabilityTA.setVisible(true);
			// // replenishmentL.setVisible(false);
			// // replenishmentTA.setVisible(false);
			// // toolTypeL.setVisible(true);
			// // toolTypeCB.setVisible(true);
			// isPaintingOrigin();
			// // itemType = "Tool";
			// } else if (arg0.getSource().toString().contains("Food Item")) {
			// // durabilityL.setVisible(false);
			// // durabilityTA.setVisible(false);
			// // replenishmentL.setVisible(true);
			// // replenishmentTA.setVisible(true);
			// // toolTypeL.setVisible(false);
			// // toolTypeCB.setVisible(false);
			// isPaintingOrigin();
			// // itemType = "Food";
			// } else if (arg0.getSource().toString().contains("Furniture Item")) {
			// // durabilityL.setVisible(true);
			// // durabilityTA.setVisible(true);
			// // replenishmentL.setVisible(false);
			// // replenishmentTA.setVisible(false);
			// // toolTypeL.setVisible(false);
			// // toolTypeCB.setVisible(false);
			// isPaintingOrigin();
			// // itemType = "Furniture";
			// }
		}
	}

	/**
	 * PopupListener class, implements ActionListener, this is called when the user clicks anywhere, this is only used for right click for the popup at the
	 * momment
	 * 
	 * @author Preston Delano
	 * 
	 */
	class PopupListener implements MouseListener, MouseWheelListener, MouseMotionListener {
		GamePanel reference;
		JPopupMenu popup;
		private int lastOffsetX;
		private int lastOffsetY;
		Point2D location = null;
		boolean showPopup;
		BaseGameFunctions bgf = new BaseGameFunctions();

		PopupListener(JPopupMenu popupMenu, GamePanel inGamePanel) {
			reference = inGamePanel;
			popup = popupMenu;
			showPopup = true;
		}

		public Point2D GetPopupLocation() {
			System.out.println("PopupLocation:" + location.getX() + "," + location.getY());
			return location;
		}

		public void mousePressed(MouseEvent e) {
			// capture starting point
			lastOffsetX = e.getX();
			lastOffsetY = e.getY();
		}

		public void mouseReleased(MouseEvent e) {
			// System.out.println(e.getButton());
			if (showPopup)
				ShowPopup(e);
			else
				showPopup = true;
		}

		private void ShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {

				popup.show(e.getComponent(), e.getX(), e.getY());
				location = new Point2D.Double(e.getX() - reference.translateX, e.getY() - reference.translateY);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// System.out.println(e.getModifiersEx());
			if (e.getModifiersEx() == 4096) {// right click
				showPopup = false;
				// new x and y are defined by current mouse location subtracted
				// by previously processed mouse location
				int newX = e.getX() - lastOffsetX;
				int newY = e.getY() - lastOffsetY;

				// increment last offset to last processed by drag event.
				lastOffsetX += newX;
				lastOffsetY += newY;

				// update the canvas locations
				reference.translateX += newX;
				reference.translateY += newY;

				// schedule a repaint.
				reference.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// System.out.println("Mouse Location:" + e.getX() + "," + e.getY());
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

				// make it a reasonable amount of zoom
				// .1 gives a nice slow transition
				reference.scale -= (.1 * e.getWheelRotation());
				// don't cross negative threshold.
				// also, setting scale to 0 has bad effects
				reference.scale = Math.max(1.0, reference.scale);
				reference.repaint();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// this is clicking with no movement
			// System.out.println("mouse clicked");
			// double[] loc = { popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY() };
			Location loc = new Location(e.getX(), e.getY());
			Human temp = new Human("mouse", loc, 0.0, false, reference.source);
			Entity tempSel = reference.level.getSelectedEntity();
			tempSel = reference.source.findEntityEvent(temp, "humans");
			if (tempSel != null && bgf.getDistance(loc, tempSel.getMapLocation()) < 25)
				System.out.println("you found: " + tempSel.name);
			else
				System.out.println("no one is there");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// this is when the mouse enters the jpanel i think
			// System.out.println("mouse entered");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// this is when the mouse exits the jpanel i think
			// System.out.println("mouse exited");
		}
	}

	/**
	 * CreatePopupMenu class is only called when the gamePanel is created to instantiate the popup menu
	 */
	public void createPopupMenu() {
		JMenuItem menuItem;

		MenuListener menuListener = new MenuListener(this);

		// Create the popup menu.
		JPopupMenu popup = new JPopupMenu();
		menuItem = new JMenuItem("new survivor");
		menuItem.addActionListener(menuListener);
		popup.add(menuItem);
		menuItem = new JMenuItem("new axe");
		menuItem.addActionListener(menuListener);
		popup.add(menuItem);
		menuItem = new JMenuItem("new wall");
		menuItem.addActionListener(menuListener);
		popup.add(menuItem);
		// menuItem = new JMenuItem("Tower:Poison");
		// menuItem.addActionListener(menuListener);
		// popup.add(menuItem);

		// Add listener to the text area so the popup menu can come up.
		popupListener = new PopupListener(popup, this);

	}

	/**
	 * createLayout class is called to create the menu panel
	 */
	public void createLayout() {
		this.setLayout(new BorderLayout());

		/**
		 * menu Creation
		 */
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		// JMenu editMenu = new JMenu("Edit");
		// JMenu helpMenu = new JMenu("Help");
		JMenuItem tempMI;
		MenuListener menuListener = new MenuListener(this);

		/**
		 * file menu item creation
		 */
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("The File Menu Item");
		menuBar.add(fileMenu);

		tempMI = new JMenuItem("Save");
		tempMI.getAccessibleContext().setAccessibleDescription("The Save File Menu Item");
		tempMI.addActionListener(menuListener);
		fileMenu.add(tempMI);

		tempMI = new JMenuItem("Load");
		tempMI.getAccessibleContext().setAccessibleDescription("The Load File Menu Item");
		tempMI.addActionListener(menuListener);
		fileMenu.add(tempMI);

		tempMI = new JMenuItem("Exit");
		tempMI.getAccessibleContext().setAccessibleDescription("The Exit File Menu Item");
		tempMI.addActionListener(menuListener);
		fileMenu.add(tempMI);

		/**
		 * adding menu bar to panel
		 */
		this.add(menuBar, BorderLayout.PAGE_START);
	}

	/**
	 * createButtonLayout class creates buttons for the screen
	 */
	private void createButtonLayout() {
		/*
		 * Button Panel creation
		 */
		Container buttonPane = new Container();
//		Container container = new Container();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));
//		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPane.setBackground(new Color(0,0,0,0));//sets the portion of the panel to transparent, so I can see the map
		
		ButtonListener btnListener = new ButtonListener();
		
		buttonPane.add(Box.createVerticalGlue());
		
		JButton btnBuildStructure = new JButton(new ImageIcon(ContentBank.buttonIcons[0]));
//		btnBuildStructure.setSize(64, 64);
//		btnBuildStructure.setMaximumSize(new Dimension(64, 64));
		btnBuildStructure.setPreferredSize(new Dimension(64, 64));
		btnBuildStructure.setActionCommand("structure");
//		btnBuildStructure.setBorder(BorderFactory.createEmptyBorder());//this removes the border around the button
		btnBuildStructure.setBackground(new Color(0,96,0,255));
//		btnBuildStructure.setContentAreaFilled(false);//new Color(0,0,0,0));//this removes the gray background of the button
		btnBuildStructure.addActionListener(btnListener);
		
		buttonPane.add(btnBuildStructure);
		buttonPane.add(Box.createRigidArea(new Dimension(0, 3)));
//		buttonPane.add(Box.createVerticalGlue());
		
		JButton btnRoster = new JButton(new ImageIcon(ContentBank.buttonIcons[1]));
//		btnRoster.setSize(64, 64);
//		btnRoster.setMaximumSize(new Dimension(64, 64));
		btnRoster.setPreferredSize(new Dimension(64, 64));
		btnRoster.setActionCommand("roster");
		btnRoster.setBackground(new Color(0,96,0,255));
//		btnRoster.setIcon(new ImageIcon(ContentBank.buttonIcons[1]));
		btnRoster.addActionListener(btnListener);
		
		buttonPane.add(btnRoster);
		
		this.add(buttonPane, BorderLayout.AFTER_LINE_ENDS);
		
	}
}
