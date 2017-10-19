/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	the main  class for the solution/game		 		*
*														*
*	methods: runGame									*
*********************************************************/
import java.net.MalformedURLException;

public class MugloarDefenceForce {

// Our defines. 
	static final String KNIGHTFETCHURL = "http://www.dragonsofmugloar.com/api/game";
	static final String WEATHERURLSTART = "http://www.dragonsofmugloar.com/weather/api/report/";
	static final String BATTLEGROUNDURLSTART = "http://www.dragonsofmugloar.com/api/game/";
	static final String USER_AGENT = "MugloarDefenceForce/1.0 <Tommi Nirha>";
	static final String BATTLEGROUNDURLEND = "/solution";	
	static final String FILENAME = "MugloarDefence";
	static final int EXTENSION_MOD = 100000;
	static final int DEFAULT_RUN = 1;
	
	
	static int runtimes = DEFAULT_RUN;
	private Dragon wyrm;
	private Knight tincan; 
	private DragonTrainer dragonSchool;
	private static ResultLogger log;
	private KnightFetch incoming;
	private WeatherFetch weatherwatch;
	private String forecast;
	private BattleSend fightTime;

	public MugloarDefenceForce() {
		;	//empty for now
	}

	/*************************************************************************************
		The main loop of the game. It first gets and decodes a Knight, then finds out weather,
		initializes and sets the values to a dragon to fight the knight. Those values are then
		sent to the battle api. 
		At every point the logfile is updated. 	
	**************************************************************************************/
	
	private void runGame (int runthroughNumber) {
		log.logGameStart(runthroughNumber);
		System.out.println("Running battle #"+runthroughNumber);
		try {
			this.incoming = new KnightFetch(KNIGHTFETCHURL, USER_AGENT);
			this.tincan = this.incoming.getKnight();
		} catch (Exception e) {
			log.logError("No knight: "+ e.getMessage());
		}
		log.logKnight(this.tincan);
		try {
			this.weatherwatch = new WeatherFetch(WEATHERURLSTART, this.tincan.getGameID(), USER_AGENT);
			this.forecast = weatherwatch.getWeather();
//			System.out.println("Weather is: "+this.forecast); // was useful for testing
		} catch (Exception e) {
			System.out.println("Problem getting weather : "+e.getMessage());
		}
		dragonSchool = new DragonTrainer(tincan);

		wyrm = dragonSchool.train(this.forecast);	
		
		if (wyrm.remainingPoints()  == 40) {		// every stat -5, a special value to indicate no dragon sending.
			log.logNoCombatSuccess("Victory (self defined) : Knight Dies to Storm, no dragon sent to die");
			return;
		}
		log.logDragon(wyrm);
		try {
			fightTime = new BattleSend(BATTLEGROUNDURLSTART, wyrm, BATTLEGROUNDURLEND, USER_AGENT);
		} catch (MalformedURLException e) {
			log.logError("Tried new BattleSend, got "+e.getMessage());
		}
		try {
			log.logResult(fightTime.fight());
		} catch (Exception e) {
			log.logError("Tried BattleSend fight method, got " +e.getMessage());
		}
	}
	
	public static void main(String []args) {
		try {
			if (args.length >= 1 && Integer.valueOf(args[0]) > 0) {
				runtimes = Integer.valueOf(args[0]); // if first arg is number, run that many times, otherwise
			} 										// default number of times remains. See definitions in beginning.
			if (args.length >= 2) { // second arg is logfile extension.
				log = new ResultLogger(FILENAME, args[1]); 
			} else {
				int time = (int) (System.currentTimeMillis() % EXTENSION_MOD); // or pseudorandom number if not given
				log = new ResultLogger(FILENAME, Integer.toString(time));				
			}
		} catch (NumberFormatException e) {
				log = new ResultLogger(FILENAME, args[0]); 			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}		
		for (int i = 0;i<runtimes;i++) {
			MugloarDefenceForce mdf = new MugloarDefenceForce();
			mdf.runGame(i+1);
		}
		log.end();
	}
}