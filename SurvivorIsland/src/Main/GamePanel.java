package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
import Layout.CustomLayout;
import Maps.IslandGenerator;
import Maps.Map;
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
	double translateX = -6400;
	double translateY = -6400;
	double scale = 1.0;
	JPanel cards;

	/**
	 * Constructor for the GamePanel class that extends JPanel
	 */
	public GamePanel() {
		// random = new Random();
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
		// iostuff = new IOClass();
		// level.setMap(iostuff.ReadMap());
		int size = 128;
		IslandGenerator iGen = new IslandGenerator(size, size);
		iGen.generateIsland(size, size, 1, 16);
		level.setMap(new Map(iGen.getCharMap()));

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

		// if (level != null)
		// level.paintComponent(g2D);

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
			// gm - game menu
			if (arg0.getActionCommand().equals("gm structure")) {
				System.out.println("structure menu");
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "Structure Buttons");
			} else if (arg0.getActionCommand().equals("gm furniture")) {
				System.out.println("furniture menu");
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "Furniture Buttons");
			} else if (arg0.getActionCommand().equals("gm roster")) {
				System.out.println("roster list");
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "Structure Buttons");
			}

			// bm - build menu
			if (arg0.getActionCommand().equals("bm build wooden wall")) {
				System.out.println("oh ya wooden wall");
			} else if (arg0.getActionCommand().equals("bm build stone wall")) {
				System.out.println("oh ya stone wall");
			} else if (arg0.getActionCommand().equals("bm cancel")) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "Main Buttons");
			}

			// fm - furniture menu
			if (arg0.getActionCommand().equals("fm build bed")) {
				System.out.println("bed woo hoo");
				// CardLayout cl = (CardLayout) (cards.getLayout());
				// cl.show(cards, "Main Buttons");
			} else if (arg0.getActionCommand().equals("fm cancel")) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "Main Buttons");
			}

			// rm - roster menu
			if (arg0.getActionCommand().equals("rm cancel")) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "Main Buttons");
			}
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
			lastOffsetX = (int) (e.getX() / reference.scale);
			lastOffsetY = (int) (e.getY() / reference.scale);
		}

		public void mouseReleased(MouseEvent e) {
			// System.out.println(e.getButton());
			if (showPopup)
				ShowPopup(e);
			else
				showPopup = true;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// System.out.println(e.getModifiersEx());
			if (e.getModifiersEx() == 4096) {// right click
				showPopup = false;
				// new x and y are defined by current mouse location subtracted
				// by previously processed mouse location
				getMousePosition(e);
				int newX = (int) (e.getX() / reference.scale) - lastOffsetX;
				int newY = (int) (e.getY() / reference.scale) - lastOffsetY;

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
				reference.scale -= (.01 * e.getWheelRotation());
				// don't cross negative threshold.
				// also, setting scale to 0 has bad effects
				reference.scale = Math.max(0.03125, reference.scale);
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

		private void ShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {

				popup.show(e.getComponent(), e.getX(), e.getY());

				getMousePosition(e);
			}
		}

		public void getMousePosition(MouseEvent e) {
			// this gets the position on the map of the mouse, given the translation, and scale
			double x = ((reference.getWidth() / 2) - reference.translateX) - (((reference.getWidth() / 2) - e.getX()) / reference.scale);
			double y = ((reference.getHeight() / 2) - reference.translateY) - (((reference.getHeight() / 2) - e.getY()) / reference.scale);
			location = new Point2D.Double(x, y);
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
		cards = new JPanel(new CardLayout());

		/*
		 * Button Panel creation////////////////////////////////////possibly use cardlayout to show different buttons
		 */
		
		JPanel mainButtonsCard = createMainButtonsW();
		cards.add(mainButtonsCard, "Main Buttons");// I think the "Main Buttons" is the identifier for this card

		JPanel StructureButtonsCard = createBuildingButtonsW();
		cards.add(StructureButtonsCard, "Structure Buttons");

		JPanel FurnitureButtonsCard = createFurnitureButtonsW();
		cards.add(FurnitureButtonsCard, "Furniture Buttons");

		JPanel RosterButtonsCard = createRosterViewerW();
		cards.add(RosterButtonsCard, "Roster Buttons");

		// adds button pane to cards
		
		JPanel temp = new JPanel(new BorderLayout());
		temp.add(cards, BorderLayout.EAST);
		this.add(temp, BorderLayout.SOUTH);

		// this stuff is for button creation
		// btnBuildStructure.setBorder(BorderFactory.createEmptyBorder());//this removes the border around the button
		// btnBuildStructure.setContentAreaFilled(false);//new Color(0,0,0,0));//this removes the gray background of the button
	}

	private JPanel createMainButtonsW() {
		JPanel temp = new JPanel();
		// Container container = new Container();
		temp.setLayout(new CustomLayout(5));//new GridLayout(3, 0, 5, 5));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map


		ButtonListener btnListener = new ButtonListener();

		// makes buttons stick to the bottom right
//		temp.add(Box.createVerticalGlue());

		// this is the build structure button
		JButton btnGMBuildStructure = new JButton(new ImageIcon(ContentBank.buttonIcons[0]));
		btnGMBuildStructure.setPreferredSize(new Dimension(64, 64));
		btnGMBuildStructure.setActionCommand("gm structure");
		btnGMBuildStructure.setBackground(new Color(0, 96, 0, 255));
		btnGMBuildStructure.addActionListener(btnListener);
		temp.add(btnGMBuildStructure);

		// space
//		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the build structure button
		JButton btnGMBuildFurniture = new JButton(new ImageIcon(ContentBank.buttonIcons[0]));
		btnGMBuildFurniture.setPreferredSize(new Dimension(64, 64));
		btnGMBuildFurniture.setActionCommand("gm furniture");
		btnGMBuildFurniture.setBackground(new Color(0, 96, 0, 255));
		btnGMBuildFurniture.addActionListener(btnListener);
		temp.add(btnGMBuildFurniture);

		// space
//		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the roster button
		JButton btnGMRoster = new JButton(new ImageIcon(ContentBank.buttonIcons[1]));
		btnGMRoster.setPreferredSize(new Dimension(64, 64));
		btnGMRoster.setActionCommand("gm roster");
		btnGMRoster.setBackground(new Color(0, 96, 0, 255));
		btnGMRoster.addActionListener(btnListener);
		temp.add(btnGMRoster);

		return temp;
	}

	private JPanel createBuildingButtonsW() {
		JPanel temp = new JPanel();

		temp.setLayout(new CustomLayout(5));//new GridLayout(3, 0, 5, 5));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map

		ButtonListener btnListener = new ButtonListener();

		// this is the build structure button
		JButton btnBMBuildWoodenWall = new JButton(new ImageIcon(ContentBank.buttonIcons[0]));
		btnBMBuildWoodenWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWoodenWall.setActionCommand("bm build wooden wall");
		btnBMBuildWoodenWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWoodenWall.addActionListener(btnListener);
		temp.add(btnBMBuildWoodenWall);//, GridLayout(1,1));

		// this is the build structure button
		JButton btnBMBuildWtoneWall = new JButton(new ImageIcon(ContentBank.buttonIcons[1]));
		btnBMBuildWtoneWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWtoneWall.setActionCommand("bm build stone wall");
		btnBMBuildWtoneWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWtoneWall.addActionListener(btnListener);
		temp.add(btnBMBuildWtoneWall);

		// this is the build structure button
		JButton btnBMBuildWtonreWall = new JButton(new ImageIcon(ContentBank.buttonIcons[2]));
		btnBMBuildWtonreWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWtonreWall.setActionCommand("bm build stone wall");
		btnBMBuildWtonreWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWtonreWall.addActionListener(btnListener);
		temp.add(btnBMBuildWtonreWall);

		// this is the build structure button
		JButton btnBMBuildWtosneWall = new JButton(new ImageIcon(ContentBank.buttonIcons[3]));
		btnBMBuildWtosneWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWtosneWall.setActionCommand("bm build stone wall");
		btnBMBuildWtosneWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWtosneWall.addActionListener(btnListener);
		temp.add(btnBMBuildWtosneWall);

		// this is the build structure button
		JButton btnBMBuidldWtoneWall = new JButton(new ImageIcon(ContentBank.buttonIcons[4]));
		btnBMBuidldWtoneWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuidldWtoneWall.setActionCommand("bm build stone wall");
		btnBMBuidldWtoneWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuidldWtoneWall.addActionListener(btnListener);
		temp.add(btnBMBuidldWtoneWall);

		// this is the build structure button
		JButton btnBMBuildWwtoneWall = new JButton(new ImageIcon(ContentBank.buttonIcons[5]));
		btnBMBuildWwtoneWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWwtoneWall.setActionCommand("bm build stone wall");
		btnBMBuildWwtoneWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWwtoneWall.addActionListener(btnListener);
		temp.add(btnBMBuildWwtoneWall);
		
		// this is the build menu cancel button
		JButton btnBMCancel = new JButton(new ImageIcon(ContentBank.buttonIcons[15]));
		btnBMCancel.setPreferredSize(new Dimension(64, 64));
		btnBMCancel.setActionCommand("bm cancel");
		btnBMCancel.setBackground(new Color(0, 96, 0, 255));
		btnBMCancel.addActionListener(btnListener);
		temp.add(btnBMCancel);

		JPanel retTemp = new JPanel(new BorderLayout());
		retTemp.add(temp, BorderLayout.SOUTH);
		
		return retTemp;
	}

	private JPanel createFurnitureButtonsW() {
		JPanel temp = new JPanel();

		temp.setLayout(new CustomLayout(5));//new GridLayout(3, 0, 5, 5));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map


		ButtonListener btnListener = new ButtonListener();

		// makes buttons stick to the bottom right
//		temp.add(Box.createVerticalGlue());

		// this is the build structure button
		JButton btnBMBuildWall = new JButton(new ImageIcon(ContentBank.buttonIcons[0]));
		btnBMBuildWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWall.setActionCommand("fm build bed");
		btnBMBuildWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWall.addActionListener(btnListener);
		temp.add(btnBMBuildWall);

		// space
//		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the build menu cancel button
		JButton btnBMCancel = new JButton(new ImageIcon(ContentBank.buttonIcons[15]));
		btnBMCancel.setPreferredSize(new Dimension(64, 64));
		btnBMCancel.setActionCommand("fm cancel");
		btnBMCancel.setBackground(new Color(0, 96, 0, 255));
		btnBMCancel.addActionListener(btnListener);
		temp.add(btnBMCancel);

		return temp;
	}

	private JPanel createRosterViewerW() {
		JPanel temp = new JPanel();

		temp.setLayout(new CustomLayout(5));//new GridLayout(3, 0, 5, 5));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map

		ButtonListener btnListener = new ButtonListener();

		// makes buttons stick to the bottom right
//		temp.add(Box.createVerticalGlue());

		// this is the build menu cancel button
		JButton btnBMCancel = new JButton(new ImageIcon(ContentBank.buttonIcons[15]));
		btnBMCancel.setPreferredSize(new Dimension(64, 64));
		btnBMCancel.setActionCommand("rm cancel");
		btnBMCancel.setBackground(new Color(0, 96, 0, 255));
		btnBMCancel.addActionListener(btnListener);
		temp.add(btnBMCancel);

		return temp;
	}

	private JPanel createMainButtons() {
		JPanel temp = new JPanel();
		// Container container = new Container();
		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map

		ButtonListener btnListener = new ButtonListener();

		// makes buttons stick to the bottom right
		temp.add(Box.createVerticalGlue());

		// this is the build structure button
		JButton btnGMBuildStructure = new JButton(new ImageIcon(ContentBank.buttonIcons[0]));
		btnGMBuildStructure.setPreferredSize(new Dimension(64, 64));
		btnGMBuildStructure.setActionCommand("gm structure");
		btnGMBuildStructure.setBackground(new Color(0, 96, 0, 255));
		btnGMBuildStructure.addActionListener(btnListener);
		temp.add(btnGMBuildStructure);

		// space
		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the build structure button
		JButton btnGMBuildFurniture = new JButton(new ImageIcon(ContentBank.buttonIcons[0]));
		btnGMBuildFurniture.setPreferredSize(new Dimension(64, 64));
		btnGMBuildFurniture.setActionCommand("gm furniture");
		btnGMBuildFurniture.setBackground(new Color(0, 96, 0, 255));
		btnGMBuildFurniture.addActionListener(btnListener);
		temp.add(btnGMBuildFurniture);

		// space
		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the roster button
		JButton btnGMRoster = new JButton(new ImageIcon(ContentBank.buttonIcons[1]));
		btnGMRoster.setPreferredSize(new Dimension(64, 64));
		btnGMRoster.setActionCommand("gm roster");
		btnGMRoster.setBackground(new Color(0, 96, 0, 255));
		btnGMRoster.addActionListener(btnListener);
		temp.add(btnGMRoster);

		return temp;
	}

	private JPanel createBuildingButtons() {
		JPanel temp = new JPanel();

		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map

		ButtonListener btnListener = new ButtonListener();

		// makes buttons stick to the bottom right
		temp.add(Box.createVerticalGlue());

		// this is the build structure button
		JButton btnBMBuildWoodenWall = new JButton(new ImageIcon(ContentBank.woodenWalls[0]));
		btnBMBuildWoodenWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWoodenWall.setActionCommand("bm build wooden wall");
		btnBMBuildWoodenWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWoodenWall.addActionListener(btnListener);
		temp.add(btnBMBuildWoodenWall);

		// space
		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the build structure button
		JButton btnBMBuildWtoneWall = new JButton(new ImageIcon(ContentBank.woodenWalls[0]));
		btnBMBuildWtoneWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWtoneWall.setActionCommand("bm build stone wall");
		btnBMBuildWtoneWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWtoneWall.addActionListener(btnListener);
		temp.add(btnBMBuildWtoneWall);

		// space
		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the build menu cancel button
		JButton btnBMCancel = new JButton(new ImageIcon(ContentBank.buttonIcons[2]));
		btnBMCancel.setPreferredSize(new Dimension(64, 64));
		btnBMCancel.setActionCommand("bm cancel");
		btnBMCancel.setBackground(new Color(0, 96, 0, 255));
		btnBMCancel.addActionListener(btnListener);
		temp.add(btnBMCancel);

		return temp;
	}

	private JPanel createFurnitureButtons() {
		JPanel temp = new JPanel();

		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map

		ButtonListener btnListener = new ButtonListener();

		// makes buttons stick to the bottom right
		temp.add(Box.createVerticalGlue());

		// this is the build structure button
		JButton btnBMBuildWall = new JButton(new ImageIcon(ContentBank.woodenWalls[0]));
		btnBMBuildWall.setPreferredSize(new Dimension(64, 64));
		btnBMBuildWall.setActionCommand("fm build bed");
		btnBMBuildWall.setBackground(new Color(0, 96, 0, 255));
		btnBMBuildWall.addActionListener(btnListener);
		temp.add(btnBMBuildWall);

		// space
		temp.add(Box.createRigidArea(new Dimension(0, 3)));

		// this is the build menu cancel button
		JButton btnBMCancel = new JButton(new ImageIcon(ContentBank.buttonIcons[2]));
		btnBMCancel.setPreferredSize(new Dimension(64, 64));
		btnBMCancel.setActionCommand("fm cancel");
		btnBMCancel.setBackground(new Color(0, 96, 0, 255));
		btnBMCancel.addActionListener(btnListener);
		temp.add(btnBMCancel);

		return temp;
	}

	private JPanel createRosterViewer() {
		JPanel temp = new JPanel();

		temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		temp.setBackground(new Color(0, 0, 0, 0));// sets the portion of the panel to transparent, so I can see the map

		ButtonListener btnListener = new ButtonListener();

		// makes buttons stick to the bottom right
		temp.add(Box.createVerticalGlue());

		// this is the build menu cancel button
		JButton btnBMCancel = new JButton(new ImageIcon(ContentBank.buttonIcons[2]));
		btnBMCancel.setPreferredSize(new Dimension(64, 64));
		btnBMCancel.setActionCommand("rm cancel");
		btnBMCancel.setBackground(new Color(0, 96, 0, 255));
		btnBMCancel.addActionListener(btnListener);
		temp.add(btnBMCancel);

		return temp;
	}
}
