
/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	class Knight is a storage class for Knight. 		*
*	constructor parses a Hashtable.						*
*	methods: accessors, toString						*
*********************************************************/

import java.util.Hashtable;

public class Knight {
	private int gameID;
	private int attack;
	private int armor;
	private int agility;
	private int endurance;
	private String name;
		
	public Knight (Hashtable tinCan) {
//		System.out.println(tinCan);
		try {
			this.gameID = Integer.valueOf(tinCan.get("gameId").toString());
			this.name = tinCan.get("name").toString();
			this.attack = Integer.valueOf(tinCan.get("attack").toString());
			this.armor = Integer.valueOf(tinCan.get("armor").toString());
			this.agility = Integer.valueOf(tinCan.get("agility").toString());
			this.endurance = Integer.valueOf(tinCan.get("endurance").toString());
		} catch (NullPointerException e) {
			System.out.println (e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println (e.getMessage());
		}
		
		
	}
	
	/* the following constructor is for testing purposes only. */
	
	public Knight (int game, String callsign, int offense, int defense, int speed, int durability) {
		this.gameID = game;
		this.name = callsign;
		this.attack = offense;
		this.armor = defense;
		this.agility = speed;
		this.endurance = durability;
	}
	
	
	public int getAttack() {
		return this.attack;
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public int getAgility() {
		return this.agility;
	}
	
	public int getEndurance() {
		return this.endurance;
	}
	
	public int getGameID () {
		return this.gameID;
	}
	
	public String getName() {	
		return (new String(this.name));
	}

	public String toStringName () {
		StringBuilder newstring = new StringBuilder ();
		newstring.append("Enter "+this.name);		
		return newstring.toString();
	}
	public String toStringStats() {
		StringBuilder newstring = new StringBuilder ();
		newstring.append("Knight #"+this.gameID);
		newstring.append(" At: ");
		if (this.attack <10) {
			newstring.append('0');
		}
		newstring.append(this.attack);
		newstring.append(" Ar: ");
		if (this.armor <10) {
			newstring.append('0');
		}
		newstring.append(this.armor);
		newstring.append(" Ag: ");
		if (this.agility <10) {
			newstring.append('0');
		}
		newstring.append(this.agility);
		newstring.append(" En: ");
		if (this.endurance <10) {
			newstring.append('0');
		}
		newstring.append(this.endurance);
		
		return newstring.toString();		
	}
		
	public String toString() {
		StringBuilder newstring = new StringBuilder ();
		newstring.append("Enter "+this.name+"\n");
		newstring.append("Knight #"+this.gameID);
		newstring.append(" At: ");
		if (this.attack <10) {
			newstring.append('0');
		}
		newstring.append(this.attack);
		newstring.append(" Ar: ");
		if (this.armor <10) {
			newstring.append('0');
		}
		newstring.append(this.armor);
		newstring.append(" Ag: ");
		if (this.agility <10) {
			newstring.append('0');
		}
		newstring.append(this.agility);
		newstring.append(" En: ");
		if (this.endurance <10) {
			newstring.append('0');
		}
		newstring.append(this.endurance);
		
		return newstring.toString();	
	}
	
}