package Main;
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

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

import Maps.Map;
/**
 * GamePanel class that extends JPanel
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	int timer = 0;
	Timer mainTimer;
//	GameFunctions.BaseGameFunctions bgf;
	PopupListener popupListener;
	IOClass fileStuff;
	Map map;
	double translateX = 0;
	double translateY = 0;
	double scale = 1.0;
//	public List<Tower> towers = new ArrayList<Tower>();
//	public List<Creep> creeps = new ArrayList<Creep>();
	
	/**
	 * Constructor for the GamePanel class that extends JPanel
	 */
	public GamePanel(){
		setFocusable(true);
		
		//creates the popup menu
		CreatePopupMenu();
		
		//adds the keyboard listener for keyboard input
		addKeyListener(new KeyboardListener());
		this.addMouseListener(popupListener);
		this.addMouseMotionListener(popupListener);
		this.addMouseWheelListener(popupListener);
		
		//makes the file io class, and gets the map data
		fileStuff = new IOClass();
		map = fileStuff.ReadMap();
		
		//timer for updating game every 10 miliseconds
		mainTimer = new Timer(10, new TimerListener());
		mainTimer.start();
	}
	
	/**
	 * CreatePopupMenu class is only called when the gamePanel is created to instantiate the popup menu
	 */
	public void CreatePopupMenu() {
        JMenuItem menuItem;
        
        MenuListener menuListener = new MenuListener();
        
        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Tower:Standard");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Tower:Fire");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Tower:Ice");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Tower:Poison");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);
 
        //Add listener to the text area so the popup menu can come up.
        popupListener = new PopupListener(popup, this);
        
    }
	
	/**
	 * Update Method, Action performed calls this to update game
	 */
	public void Update(){
		
	}
	
	/**
	 * Paint Method, Action performed repaint to paint the game
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		AffineTransform at = new AffineTransform();
		
		at.translate(getWidth()/2, getHeight()/2);
	    at.scale(scale, scale);
	    at.translate(-getWidth()/2, -getHeight()/2);
		
	    at.translate(translateX, translateY);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setTransform(at);
		
		map.paintComponent(g2D);
//		g.drawImage(ContentBank.beach, 0, 0, null);
		
	}
	
	/**
	 * TimerListener class, implements ActionListener, this class only calls the update methods that
	 * run for every cycle/scene of the game
	 * 
	 * @author Preston Delano
	 */
	class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Update();
			repaint();
			
			if(timer >= 25){
//				Creep tempCreep = new Creep(1.0, map.mapPath, 10.0, 1);
//				creeps.add(tempCreep);
//				timer = 0;
			}
			timer++;
		}
	}
	
	/**
	 * KeyboardListener class, implements ActionListener, this class is used when there is a key press,
	 * release, or type
	 * 
	 * @author Preston Delano
	 *
	 */
	class KeyboardListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {
			
			int key = arg0.getKeyCode();
			
			if(key == KeyEvent.VK_SPACE){
//				Creep tempCreep = new Creep(1.0, map.mapPath, 10.0, 1);
//				creeps.add(tempCreep);
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
	 * MenuListener class, implements ActionListener, this class is used when a menu item is selected,
	 * clicked or other things
	 * 
	 * @author Preston Delano
	 *
	 */
	class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
//			if(arg0.paramString().contains("Standard")){
//				NewTower(ContentBank.TowerType.Standard);
//			}
//			if(arg0.paramString().contains("Fire")){
//				NewTower(ContentBank.TowerType.Fire);
//			}
//			else if(arg0.paramString().contains("Ice")){
//				NewTower(ContentBank.TowerType.Ice);
//			}
//			else if(arg0.paramString().contains("Poison")){
//				NewTower(ContentBank.TowerType.Poison);
//			}
		}
	}
	
	/**
	 * PopupListener class, implements ActionListener, this is called when the user clicks anywhere, this is
	 * only used for right click for the popup at the momment
	 * 
	 * @author Preston Delano
	 *
	 */
	class PopupListener implements MouseListener, MouseWheelListener, MouseMotionListener{
		GamePanel reference;
		JPopupMenu popup;
		private int lastOffsetX;
		private int lastOffsetY;
		Point2D location = null;
		
	    PopupListener(JPopupMenu popupMenu, GamePanel inGamePanel) {
	        reference = inGamePanel;
	    	popup = popupMenu;
	    }
	    public Point2D GetPopupLocation(){
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
	            location = new Point2D.Double(e.getX(), e.getY());
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
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
				
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
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
