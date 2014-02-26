package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Items.Food;
import Items.Furniture;
import Items.Item;
import Items.Tool;
import Items.ToolType;

@SuppressWarnings("serial")
public class ItemCreatorPanel extends JPanel {
	String path = "";
	String fileName = "";
	List<Item> items = new ArrayList<>();
	IOClass io = new IOClass();
	JTextArea textArea;
	String itemType;
	
	String[] toolTypeArray = { "NONE", "AXE", "SHOVEL", "PICKAXE", "SWORD", "KNIFE", "GUN", "BOW", "CROSSBOW", "STAFF", "HOE", "RAKE", "BUCKET" };
	JLabel nameL, weightL, conditionL, durabilityL, replenishmentL, toolTypeL;
	JTextField nameTA, weightTA, conditionTA, durabilityTA, replenishmentTA;
	JComboBox<String> toolTypeCB;

	public ItemCreatorPanel() {
		setFocusable(true);

		createLayout();

		// String input = "";
		// Scanner in = new Scanner(System.in);
		// while(true){
		//
		// input = in.nextLine();
		//

		// }else if(input.contains("new file")){
		// fileName = getInput(input);
		// items.clear();
		// }
		//
		//
		// if(input.equalsIgnoreCase("quit"))
		// break;
		// }
		// in.close();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private void createLayout() {
		this.setLayout(new BorderLayout());

		/**
		 * menu Creation
		 */
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem tempMI;
		MenuListener menuListener = new MenuListener();

		/**
		 * file menu item creation
		 */
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("The File Menu Item");
		menuBar.add(fileMenu);

		tempMI = new JMenuItem("Save New");
		tempMI.getAccessibleContext().setAccessibleDescription("The Save New File Menu Item");
		tempMI.addActionListener(menuListener);
		fileMenu.add(tempMI);

		tempMI = new JMenuItem("Save All");
		tempMI.getAccessibleContext().setAccessibleDescription("The Save All File Menu Item");
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

		/*
		 * Button Panel creation
		 */
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		// create buttons
		JButton addButton = new JButton("New");
		JButton printButton = new JButton("Add");
		JButton editButton = new JButton("Edit");

		// create drop down
		String[] recipeTypeArray = { "Plain Item", "Food Item", "Tool Item", "Furniture Item" };
		JComboBox<String> recipeTypeComboBox = new JComboBox<>(recipeTypeArray);
		recipeTypeComboBox.setSelectedIndex(0);

		// put text field and buttons on panel
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(addButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(recipeTypeComboBox);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(editButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(printButton);

		// add action listeners
		ButtonListener buttonListener = new ButtonListener();//this);
		recipeTypeComboBox.addActionListener(buttonListener);
		addButton.addActionListener(buttonListener);
		printButton.addActionListener(buttonListener);
		editButton.addActionListener(buttonListener);

		// // ////////////////////////////////////////////////////////////
		// JButton temp = new JButton("temp");
		// buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		// buttonPane.add(temp);
		// temp.addActionListener(buttonListener);
		// // ////////////////////////////////////////////////////////////

		this.add(buttonPane, BorderLayout.PAGE_END);

		textArea = new JTextArea();

		this.add(textArea, BorderLayout.CENTER);

		// durabilityL, , toolTypeL;durabilityTA, replenishmentTA;toolTypeCB;
		nameL = new JLabel("Name");
		nameTA = new JTextField();
		nameTA.setMaximumSize(new Dimension(250, 10));
		weightL = new JLabel("Weight");
		weightTA = new JTextField();
		weightTA.setMaximumSize(new Dimension(500, 10));
		conditionL = new JLabel("Condition");
		conditionTA = new JTextField();
		conditionTA.setMaximumSize(new Dimension(500, 10));
		durabilityL = new JLabel("Durability");
		durabilityTA = new JTextField();
		durabilityTA.setMaximumSize(new Dimension(250, 10));
		replenishmentL = new JLabel("Replenishment");
		replenishmentTA = new JTextField();
		replenishmentTA.setMaximumSize(new Dimension(250, 10));
		toolTypeL = new JLabel("Tool Type");
		
		// ToolType[] toolTypeArray = { ToolType.NONE, ToolType.AXE,
		// ToolType.SHOVEL, ToolType.PICKAXE, ToolType.SWORD, ToolType.KNIFE,
		// ToolType.GUN,
		// ToolType.BOW, ToolType.CROSSBOW, ToolType.STAFF, ToolType.HOE,
		// ToolType.RAKE, ToolType.BUCKET };
		toolTypeCB = new JComboBox<>(toolTypeArray);
		recipeTypeComboBox.setSelectedIndex(0);

		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		// put text field and buttons on panel
		buttonPane.add(Box.createVerticalGlue());
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(nameL);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(nameTA);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(weightL);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(weightTA);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(conditionL);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(conditionTA);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(durabilityL);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(durabilityTA);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(replenishmentL);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(replenishmentTA);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(toolTypeL);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(toolTypeCB);

		this.add(buttonPane, BorderLayout.AFTER_LINE_ENDS);
	}

	private void setTextArea() {
		textArea.setText("");
		// Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < items.size(); i++) {
			textArea.append(items.get(i).name + "\r\n");
		}
	}

	/**
	 * MenuListener class, implements ActionListener, this class is used when a
	 * menu item is selected, clicked or other things
	 * 
	 * @author Preston Delano
	 * 
	 */
	private class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.paramString().contains("Save New")) {
				io.saveItems(System.getProperty("user.dir") + "/Items", items);
			} else if (arg0.paramString().contains("Save All")) {
				io.saveItems(System.getProperty("user.dir") + "/Items", items);
			} else if (arg0.paramString().contains("Load")) {
				HashMap<String, Item> temp = io.loadItems(new File(System.getProperty("user.dir") + "/Items/Item"), "Item");
				temp.putAll(io.loadItems(new File(System.getProperty("user.dir") + "/Items/Tool"), "Tool"));
				temp.putAll(io.loadItems(new File(System.getProperty("user.dir") + "/Items/Food"), "Food"));
				temp.putAll(io.loadItems(new File(System.getProperty("user.dir") + "/Items/Furniture"), "Furniture"));
				items = new ArrayList<Item>(temp.values());
				setTextArea();
				for(int i = 0; i< items.size(); i++){
					System.out.println(items.get(i).getClass().toString().split("\\.")[1]);
				}
			} else if (arg0.paramString().contains("Exit")) {
				System.exit(0);
			}
		}
	}

	/**
	 * ButtonListener class, implements ActionListener, this class is used when
	 * a button is clicked
	 * 
	 * @author Preston Delano
	 * 
	 */
	private class ButtonListener implements ActionListener {
//		ItemCreatorPanel ref;

		public ButtonListener(){//ItemCreatorPanel inRef) {
//			ref = inRef;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("New")) {
			} else if (arg0.getActionCommand().equals("Add")) {
				if (!nameTA.getText().equals("") && !nameTA.getText().equals(null)) {
					String tempName = nameTA.getText();
					if (!weightTA.getText().equals("") && !weightTA.getText().equals(null)) {
						double tempWeight = Double.parseDouble(weightTA.getText());
						if (!conditionTA.getText().equals("") && !conditionTA.getText().equals(null)) {
							int tempCondition = Integer.parseInt(conditionTA.getText());
							switch (itemType) {
							case "Item":
								Item tempItem = new Item(tempName, tempWeight, tempCondition);
								items.add(tempItem);
								break;
							case "Food":
								if (!replenishmentTA.getText().equals("") && !replenishmentTA.getText().equals(null)) {
									int tempReplen = Integer.parseInt(replenishmentTA.getText());
									Food tempFood = new Food(tempName, tempWeight, tempCondition, tempReplen);
									items.add(tempFood);
								}
								break;
							case "Furniture":
								Furniture tempFurniture = new Furniture(tempName, tempWeight, tempCondition);
								items.add(tempFurniture);
								break;
							case "Tool":
								if (!durabilityTA.getText().equals("") && !durabilityTA.getText().equals(null)) {
									int tempDura = Integer.parseInt(durabilityTA.getText());
									Tool tempTool = new Tool(tempName, tempWeight, tempCondition, ToolType.valueOf(toolTypeArray[toolTypeCB.getSelectedIndex()]), tempDura);
									items.add(tempTool);
								}
								break;
							}
						}
					}
				}

				setTextArea();
			} else if (arg0.getActionCommand().equals("Edit")) {
				//durabilityL, replenishmentL, toolTypeL; durabilityTA, replenishmentTA;
			} else if (arg0.getSource().toString().contains("Plain Item")) {
				durabilityL.setVisible(false);
				durabilityTA.setVisible(false);
				replenishmentL.setVisible(false);
				replenishmentTA.setVisible(false);
				toolTypeL.setVisible(false);
				toolTypeCB.setVisible(false);
				//paintImmediately(0,0,600,600);
				isPaintingOrigin();
				itemType = "Item";
			} else if (arg0.getSource().toString().contains("Tool Item")) {
				durabilityL.setVisible(true);
				durabilityTA.setVisible(true);
				replenishmentL.setVisible(false);
				replenishmentTA.setVisible(false);
				toolTypeL.setVisible(true);
				toolTypeCB.setVisible(true);
				isPaintingOrigin();
				itemType = "Tool";
			} else if (arg0.getSource().toString().contains("Food Item")) {
				durabilityL.setVisible(false);
				durabilityTA.setVisible(false);
				replenishmentL.setVisible(true);
				replenishmentTA.setVisible(true);
				toolTypeL.setVisible(false);
				toolTypeCB.setVisible(false);
				isPaintingOrigin();
				itemType = "Food";
			} else if (arg0.getSource().toString().contains("Furniture Item")) {
				durabilityL.setVisible(true);
				durabilityTA.setVisible(true);
				replenishmentL.setVisible(false);
				replenishmentTA.setVisible(false);
				toolTypeL.setVisible(false);
				toolTypeCB.setVisible(false);
				isPaintingOrigin();
				itemType = "Furniture";
			}
		}
	}
}
