package Main;

import java.awt.BorderLayout;
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
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

import Entity.Entity;
import Entity.ItemEntity;
import Entity.StructureEntity;
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
	int timer = 0;//if I load a game, this will be reset to 0 causing problems with crops and other timed things
	Timer mainTimer;
	PopupListener popupListener;
	IOClass iostuff;
	// Map map;
	public Level level;
	CustomEventSource source;
	double translateX = 0;
	double translateY = 0;
	double scale = 1.0;
	public static Random random;
	AffineTransform holder;
	
	// public List<Human> humans = new ArrayList<>();
	// public static List<ItemEntity> itemEntities = new ArrayList<>();

	/**
	 * Constructor for the GamePanel class that extends JPanel
	 */
	public GamePanel() {
		random = new Random();
		setFocusable(true);

		level = new Level();
		source = new CustomEventSource();
		source.addEventListener(level);

		// creates the popup menu
		CreatePopupMenu();

		createLayout();

		// adds the keyboard listener for keyboard input
		addKeyListener(new KeyboardListener());
		this.addMouseListener(popupListener);
		this.addMouseMotionListener(popupListener);
		this.addMouseWheelListener(popupListener);

		// makes the file io class, and gets the map data
		iostuff = new IOClass();
		level.setMap(iostuff.ReadMap());

		// timer for updating game every 10 miliseconds
		mainTimer = new Timer(10, new TimerListener());
		mainTimer.start();
	}

	/**
	 * CreatePopupMenu class is only called when the gamePanel is created to
	 * instantiate the popup menu
	 */
	public void CreatePopupMenu() {
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
	 * Update Method, Action performed calls this to update game
	 */
	public void Update() {
		if (level != null)
			level.update();
	}

	/**
	 * Paint Method, Action performed repaint to paint the game
	 */
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
		
		//this resets the at for the j components to draw normally
		AffineTransform at = new AffineTransform();
		g2D.setTransform(at);
	}

	/**
	 * TimerListener class, implements ActionListener, this class only calls the
	 * update methods that run for every cycle/scene of the game
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
	 * KeyboardListener class, implements ActionListener, this class is used
	 * when there is a key press, release, or type
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

		}

		@Override
		public void keyTyped(KeyEvent arg0) {

		}
	}

	/**
	 * MenuListener class, implements ActionListener, this class is used when a
	 * menu item is selected, clicked or other things
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
				Location tempLoc = new Location(popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY());
				Location loc = ref.source.getTileAtLocation(tempLoc);
				Survivor survivor = new Survivor("Rob", loc, 150, true, ref.source);
				level.addHuman(survivor);
			} else if (arg0.paramString().contains("new axe")) {
				Location tempLoc = new Location(popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY());
				Location loc = ref.source.getTileAtLocation(tempLoc);
				Tool axe = new Tool("axe", 2.5, 100, ToolType.AXE, 100);
				ItemEntity axeEntity = new ItemEntity(axe, loc);
				level.addItem(axeEntity);
			} else if (arg0.paramString().contains("new wall")) {
				Location tempLoc = new Location(popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY());
				Location loc = ref.source.getTileAtLocation(tempLoc);
				System.out.println("GamePanel: " + loc.getMapX() + "," + loc.getMapY());
				StructureEntity wall = new StructureEntity("Wall", loc, 3.4, true);
//				FurnitureEntity fEntity = new FurnitureEntity(chest, loc);
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
	 * PopupListener class, implements ActionListener, this is called when the
	 * user clicks anywhere, this is only used for right click for the popup at
	 * the momment
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
		BaseGameFunctions bgf = new BaseGameFunctions();

		PopupListener(JPopupMenu popupMenu, GamePanel inGamePanel) {
			reference = inGamePanel;
			popup = popupMenu;
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
			ShowPopup(e);
		}

		private void ShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {

				popup.show(e.getComponent(), e.getX(), e.getY());
				location = new Point2D.Double(e.getX() - reference.translateX, e.getY() - reference.translateY);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
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

		@Override
		public void mouseMoved(MouseEvent e) {
//			System.out.println("Mouse Location:" + e.getX() + "," + e.getY());
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
//			double[] loc = { popupListener.GetPopupLocation().getX(), popupListener.GetPopupLocation().getY() };
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
}
