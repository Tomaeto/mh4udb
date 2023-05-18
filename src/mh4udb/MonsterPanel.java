package mh4udb;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

import dataTransferObjects.*;

public class MonsterPanel extends JPanel {
	private ArrayList<Monster> monsters;
	
	public MonsterPanel() throws IOException {
		//Setting layout to 14x6 grid to fit all 86 monsters
		setLayout(new GridLayout(14,6));
		createUI();
	}
	private void createUI() throws IOException {
		//Getting all info files from directory
		File infoDir = new File("data/monsterInfo");
		monsters = new ArrayList<Monster>();
		File[] infoFiles = infoDir.listFiles();
		
		//Building all Monster objects from info files
		for (File infoFile : infoFiles) {
			monsters.add(new Monster(infoFile));
		}
		
		//Building panel w/ hitzone data table for each monster
		for (Monster monster : monsters) {
			JPanel miniPanel = new JPanel(new BorderLayout());
			JButton button = new JButton(monster.getIcon());
			
			//Creates JTable with # of rows for each body part + 1 for column names
			//	and # of columns for part name and each hitzone value (cut, impact, shot, fire, water, thunder, ice, dragon)
			JTable hitzoneTable = new JTable(monster.getBodyParts().size() + 1, 9);
			
			//Setting column titles
			hitzoneTable.setValueAt("Body Part", 0, 0);
			hitzoneTable.setValueAt("Cut", 0, 1);
			hitzoneTable.setValueAt("Impact", 0, 2);
			hitzoneTable.setValueAt("Shot", 0, 3);
			hitzoneTable.setValueAt("Fire", 0, 4);
			hitzoneTable.setValueAt("Water", 0, 5);
			hitzoneTable.setValueAt("Thunder", 0, 6);
			hitzoneTable.setValueAt("Ice", 0, 7);
			hitzoneTable.setValueAt("Dragon", 0, 8);
			
			//Setting values for each row
			//Each row contains hitzone values for a body part
			for (int i = 0; i < monster.getBodyParts().size(); i++) {
				//Getting current part from Monster
				MonsterBodyPart currentPart = monster.getBodyParts().get(i);
				
				//Setting row title as part name
				hitzoneTable.setValueAt(currentPart.getName(), i + 1, 0);
				
				//Setting cells of row to part's hitzone values
				int[] res = currentPart.getAllRes();
				for (int j = 0; j < res.length; j++) {
					hitzoneTable.setValueAt(res[j], i + 1, j + 1);
				}
			}
			//Creating JFrame for displaying monster's hitzone data
			JFrame hitzoneFrame = new JFrame(monster.getName());
			hitzoneFrame.add(hitzoneTable);
			hitzoneFrame.pack();
			button.addActionListener(event -> {
				//Show hitzone data JFrame if not already visible
				if (!hitzoneFrame.isVisible()) {
					hitzoneFrame.setVisible(true);
				}
			});
			
			//Adding monster button and label w/ monster's name to the miniPanel, and adding miniPanel to monster panel
			miniPanel.add(new JLabel(monster.getName()), BorderLayout.SOUTH);
			miniPanel.add(button, BorderLayout.NORTH);
			this.add(miniPanel);

		}
	}

	//Main driver code
	public static void main(String[] args) throws IOException {
		//Creating frame and setting to exit on close
		JFrame mainFrame = new JFrame("MH4U Monsters");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creating scrollable window w/ preferred size
		MonsterPanel window = new MonsterPanel();
		JScrollPane scrollWindow = new JScrollPane(window);
		mainFrame.setContentPane(scrollWindow);
		//Adding window to frame, packing frame and displaying
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}
