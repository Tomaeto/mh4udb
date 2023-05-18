package dataTransferObjects;

//Class for containing data on a monster's body part
//Stores id, name, and hitzone data for attack type (cut, impact, shot) and element
//Type refers to part under different conditions (A = normal, B = enraged)
//Data obtained from JSON and written to object in DataAccessor class

public class MonsterBodyPart {
	
	//Data element variables
	private int id;
	private String type;
	private String local_name;
	private int res_cut;
	private int res_impact;
	private int res_shot;
	private int res_fire;
	private int res_water;
	private int res_thunder;
	private int res_ice;
	private int res_dragon;
	
	//Constructor for writing each data element
	public MonsterBodyPart(int id, String type, String local_name, int res_cut, int res_impact, int res_shot,
						   int res_fire, int res_water, int res_thunder, int res_ice, int res_dragon) {
		this.id = id;
		this.type = type;
		this.local_name = local_name;
		this.res_cut = res_cut;
		this.res_impact = res_impact;
		this.res_shot = res_shot;
		this.res_fire = res_fire;
		this.res_water = res_water;
		this.res_thunder = res_thunder;
		this.res_ice = res_ice;
		this.res_dragon = res_dragon;
	}
	
	//Getters for data elements
	public int getID() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	public String getName() {
		return local_name;
	}
	
	public int getResCut() {
		return res_cut;
	}
	
	public int getResImpact() {
		return res_impact;
	}
	
	public int getResShot() {
		return res_shot;
	}
	
	public int getResFire() {
		return res_fire;
	}
	
	public int getResWater() {
		return res_water;
	}
	
	public int getResThunder() {
		return res_thunder;
	}
	
	public int getResIce() {
		return res_ice;
	}
	
	public int getResDragon() {
		return res_dragon;
	}
	
	public int[] getAllRes() {
		int[] res = {res_cut, res_impact, res_shot, res_fire, res_water, res_thunder, res_ice, res_dragon};
		return res;
	}
}
