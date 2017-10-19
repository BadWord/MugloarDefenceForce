/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	class DragonTrainer is the class where the stats    *
*   required to beat the knight faced are calculated.	*
*	constructor wants a knight to beat					*
*	methods: train - version 1.0 train is without		*
*		parameter, later also train(weatherid)			*
*********************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class DragonTrainer {
		private Knight sir;
		private Dragon drake;
		
		public DragonTrainer (Knight invader) {
			this.sir = invader;
		}

/********************************************************************************
* The main logic of the dragon training is here. I thought I needed one function*
* for each weather, but turns out all other weathers just need a weather        *
* specific dragon. 																*
* 																				*
* First, the knight stats are placed in an array and arranged from high to low. *
* I am not certain if agility is more important than other stats, maybe the     *
* first impression was colored by chance, but anyway, the dragon is created     *
* in the following manner: 														*
*																				*
* Dragon's scale compares to knight attack, claw to armor, wings to agility,    *
* breath to endurance. The dragon is given 2 points over the knight's best stat *
* (which seems to max out at 8), two next highest stats are given one less,     *
* any remaining points are given to the last stat. Seems to work.				*
*********************************************************************************/		
		
		public Dragon trainNormal () {
						
			int pool = 20;
			BattleStat bs;

			Dragon trainee = new Dragon(this.sir.getGameID());
			
		//	System.out.println(sir.getGameID()); // Did not clean up all of these.
			
			ArrayList<BattleStat> statcomp = new ArrayList<BattleStat>();
			statcomp.add(new BattleStat(3, this.sir.getAgility()));			// note that order is nonstandard!
			statcomp.add(new BattleStat(1, this.sir.getAttack()));
			statcomp.add(new BattleStat(2, this.sir.getArmor()));
			statcomp.add(new BattleStat(4, this.sir.getEndurance()));
			
			Collections.sort(statcomp);
			int todrake;
			bs = (BattleStat)statcomp.get(0);		// HIGHEST stat
			todrake = bs.getValue() + 2;
//			System.out.println("todrake first "+bs.getOrder()+ " : "+ bs.getValue());
			switch (bs.getOrder()) {
					case 1: pool= trainee.setScale(todrake);
							break;
					case 2:	pool = trainee.setClaw(todrake);
							break;
					case 3: pool = trainee.setWings(todrake);
							break;
					case 4: pool = trainee.setBreath(todrake);
						    break;
			}
						
			bs = (BattleStat)statcomp.get(1);	// Second highest
	//		System.out.println("todrake second "+bs.getOrder()+ " : "+ bs.getValue());
			todrake = bs.getValue() -1;
			switch (bs.getOrder()) {
					case 1: pool= trainee.setScale(todrake);
							break;
					case 2:	pool = trainee.setClaw(todrake);
							break;
					case 3: pool = trainee.setWings(todrake);
							break;
					case 4: pool = trainee.setBreath(todrake);
						    break;
			}
			bs = (BattleStat)statcomp.get(2);	// third highest
//			System.out.println("todrake third "+bs.getOrder()+ " : "+ bs.getValue());

			todrake = bs.getValue()-1;
			
			switch (bs.getOrder()) {
					case 1: pool= trainee.setScale(todrake);
							break;
					case 2:	pool = trainee.setClaw(todrake);
							break;
					case 3: pool = trainee.setWings(todrake);
							break;
					case 4: pool = trainee.setBreath(todrake);
						    break;
			}
	
			bs = (BattleStat)statcomp.get(3);	// fourth highest
//			System.out.println("todrake fourth "+bs.getOrder()+ " : "+ bs.getValue());
			todrake = pool;
			switch (bs.getOrder()) {
					case 1: pool= trainee.setScale(todrake);
							break;
					case 2:	pool = trainee.setClaw(todrake);
							break;
					case 3: pool = trainee.setWings(todrake);
							break;
					case 4: pool = trainee.setBreath(todrake);
						    break;
			}
		
			return trainee;
		
		}
		
		public Dragon train(String weathercode){
			if (weathercode.equals("NMR")) {
				return trainNormal();
			} 
			if (weathercode.equals("SRO")) {
				return new Dragon(-5, -5, -5, -5, this.sir.getGameID()); // don't send a dragon to storm!
			} 
			if (weathercode.equals("HVA")) {							
				return new Dragon(5, 10, 5, 0, this.sir.getGameID());	// Juse need claw strength
			}
			
			return new Dragon(5, 5, 5, 5, this.sir.getGameID()); // Both T E and FINDEFINEDG are solved by this
			
			
		}
}
