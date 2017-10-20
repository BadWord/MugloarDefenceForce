/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	class Dragon is a storage class for a Dragon. 		*
*	constructor asks game id or all stats and game id.	*
*	methods: accessors, setters, toString for logging 	*
*		and toJSonString to make a String that is a 	*
*		JSON representation	of the Dragon				*	
*********************************************************/

public class Dragon {

	private final String TAB = "    ";
	private int scaleThickness;
	private int clawSharpness;
	private int wingStrength;
	private int fireBreath;
	private int id;
	
	public Dragon(int gameid){
		this.scaleThickness = 0;
		this.clawSharpness = 0;
		this.wingStrength = 0;
		this.fireBreath = 0;
		this.id = gameid;
	}
	public Dragon(int scale, int claw, int wings, int breath, int gameid) {
		this.scaleThickness = scale;
		this.clawSharpness = claw;
		this.wingStrength = wings;
		this.fireBreath = breath;
		this.id = gameid;
	}

	public int getScale() {
		return this.scaleThickness;
	}
	public int getClaw() {
		return this.clawSharpness;
	}
	public int getWings() {
		return this.wingStrength;
	}
	public int getBreath() {
		return this.fireBreath;
	}
	
	public int getID () {
		return this.id;
	}
	
	public int remainingPoints(){		
		int i = 20;
		i = i-this.scaleThickness;
		i = i-this.clawSharpness;
		i = i-this.wingStrength;
		i = i-this.fireBreath;
		return i;
	}
	
	public int setScale(int newScale) {
		this.scaleThickness = newScale;
		return remainingPoints();
	}
	public int setClaw(int newClaw) {
		this.clawSharpness = newClaw;
		return remainingPoints();
	}
	public int setWings(int newWings) {
		this.wingStrength = newWings;
		return remainingPoints();

	}
	public int setBreath(int newBreath) {
		this.fireBreath = newBreath;
		return remainingPoints();

	}
	public String toString() {
		StringBuilder newstring = new StringBuilder ("Dragon #");
		newstring.append(this.id);
		if (remainingPoints() == 40) {
			newstring.append(" trained  only to eat, stays home.");
			return newstring.toString();
		}

		newstring.append(" Sc: ");
		if (this.scaleThickness <10) {
			newstring.append('0');
		}
		newstring.append(this.scaleThickness);
		newstring.append(" Cl: ");
		if (this.clawSharpness <10) {
			newstring.append('0');
		}
		newstring.append(this.clawSharpness);
		newstring.append(" Wi: ");
		if (this.wingStrength <10) {
			newstring.append('0');
		}
		newstring.append(this.wingStrength);
		newstring.append(" Br: ");
		if (this.fireBreath <10) {
			newstring.append('0');
		}
		newstring.append(this.fireBreath);
		return newstring.toString();
	}
	public String toJSONString () {
		if (remainingPoints() == 40 ){
			String noDragonString = new String("{\n}");
			return noDragonString;
		}
		StringBuilder jSONLike = new StringBuilder("{\n");
		jSONLike.append(TAB+"\"dragon\": {\n");
		jSONLike.append(TAB+TAB+"\"scaleThickness\": " + this.scaleThickness+",\n");
		jSONLike.append(TAB+TAB+"\"clawSharpness\": " + this.clawSharpness+",\n");
		jSONLike.append(TAB+TAB+"\"wingStrength\": " + this.wingStrength+",\n");
		jSONLike.append(TAB+TAB+"\"fireBreath\": " + this.fireBreath+"\n");
		jSONLike.append(TAB+"}\n");
		jSONLike.append("}");

		
		return jSONLike.toString();
	}
}