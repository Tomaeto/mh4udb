package dataTransferObjects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//Data Accessor for monster data
//Takes JSON file w/ monster data and parses monster's name, id, and body part data as defined in MonsterBodyPart class
//Used to read JSON files and pass data into Monster class
public class DataAccessor {
	
	//Data element variables
	private ArrayList<MonsterBodyPart> bodyParts;
	private String name;
	private int monster_id;
	private ImageIcon icon;
	
	public DataAccessor(File file) throws IOException {
		
		//ObjectMapper for reading JSON tree from passed in file
		ObjectMapper mapper = new ObjectMapper();
		
		//Opening JSON file w/ mapper, reading tree, and getting tree from "monster" node as it is stored weird
		JsonNode root = mapper.readTree(file).get("monster");
		
		//Getting name from JSON node, substring to remove quotations at each end of default string
		name = root.get("local_name").toString();
		name = name.substring(1, name.length()-1);
		monster_id = Integer.parseInt(root.get("id").toString());
		
		//Root with all entries for monster parts
		root = root.findValue("monsterbodyparts");
		
		//ArrayList for storing body part data
		bodyParts = new ArrayList<MonsterBodyPart>();
		
		//For each monster body part node, parse data elements and make entry in bodyParts ArrayList
		root.forEach(node -> {
			
			//Node containing part's hitzone data
			JsonNode hitzoneNode = node.findValue("pivot");
			
			//Parsing each data element from either part node or hitzone node
			int id = Integer.parseInt(node.get("id").toString());
			String local_name = node.get("local_name").toString();
			local_name = local_name.substring(1, local_name.length()-1);
			String type = hitzoneNode.get("type").toString();
			type = type.substring(1, type.length()-1);
			int res_cut = Integer.parseInt(hitzoneNode.get("res_cut").toString());
			int res_impact = Integer.parseInt(hitzoneNode.get("res_impact").toString());
			int res_shot = Integer.parseInt(hitzoneNode.get("res_shot").toString());
			int res_fire = Integer.parseInt(hitzoneNode.get("res_fire").toString());
			int res_water = Integer.parseInt(hitzoneNode.get("res_water").toString());
			int res_thunder = Integer.parseInt(hitzoneNode.get("res_thunder").toString());
			int res_ice = Integer.parseInt(hitzoneNode.get("res_ice").toString());
			int res_dragon = Integer.parseInt(hitzoneNode.get("res_dragon").toString());
			
			//Adding new body part entry to ArrayList
			bodyParts.add(new MonsterBodyPart(id, type, local_name, res_cut, res_impact, res_shot,
											  res_fire, res_water, res_thunder, res_ice, res_dragon));
		});
		
		//Getting icon from icons folder using name obtained from info file
		//Converting name info filename format (replace spaces and dashes with underscores and convert to lowercase)
		String tempname = name.replace(' ', '_');
		tempname = tempname.replace('-', '_');
		
		//Removing apostrophe from Dah'ren Mohran filename, only applies to this monster
		if (tempname.charAt(3) == '\'') {
			tempname = tempname.substring(0,3) + tempname.substring(4, tempname.length());
		}
		
		tempname = tempname.toLowerCase();
		
		//If monster has unique icon in directory, set that icon, else set to unkown icon
		if (new File("data/monsterIcons/" + tempname + ".png").exists()) {
			icon = new ImageIcon("data/monsterIcons/" + tempname + ".png");
		}
		else {
			icon = new ImageIcon("data/monsterIcons/unknown.png");
		}
	}
	
	//Getters for data elements
	public String getName() {
		return name;
	}
	
	public int getID() {
		return monster_id;
	}
	
	public ArrayList<MonsterBodyPart> getBodyParts() {
		return bodyParts;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}
