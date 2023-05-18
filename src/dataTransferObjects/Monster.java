package dataTransferObjects;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

//Class for storing monster data parsed from JSON files & monster icon
//Uses DataAccessor to read and parse given JSON file and store data elements on monster's ID, name, and body part info, and store icon from icons folder
public class Monster {
	
	//Data element variables
	private int id;
	private String local_name;
	private List<MonsterBodyPart> bodyParts;
	private Icon icon;
	
	public Monster(File infoFile) throws IOException {
		//Creating DataAccessor for given file and storing monster data
		DataAccessor accessor = new DataAccessor(infoFile);
		id = accessor.getID();
		local_name = accessor.getName();
		bodyParts = accessor.getBodyParts();
		icon = accessor.getIcon();
	}
	
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return local_name;
	}
	
	public List<MonsterBodyPart> getBodyParts() {
		return bodyParts;
	}
	
	public Icon getIcon() {
		return icon;
	}
}
