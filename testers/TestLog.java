/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	tester class for the logging functionality		 	*
*														*
*	methods: runGame									*
*********************************************************/
import java.net.MalformedURLException;

public class TestLog {

// Our defines. 
	static final String KNIGHTFETCHURL = "http://www.dragonsofmugloar.com/api/game";
	static final String WEATHERFETCHURL = "asdf";
	static final String BATTLEGROUNDURLSTART = "http://www.dragonsofmugloar.com/api/game/";
	static final String USER_AGENT = "MugloarDefenceForce/0.1 <Tommi Nirha>";
	static final String BATTLEGROUNDURLEND = "/solution";	
	static final String FILENAME = "Mugloar";
	static final int DEFAULT_RUN = 1;
	static final int LOG_EXTENSION  = 100000;
	
	
	static int runtimes = DEFAULT_RUN;
	private Dragon wyrm;
	private Knight tincan; 
	private DragonTrainer dragonSchool;
	private static ResultLogger log;
	private KnightFetch incoming;
	private BattleSend fightTime;

	public TestLog() {
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
		tincan = new Knight(12345, "SirX", 5,5,5,5); // enter values given in testGet name irrelevant;
		log.logKnight(tincan);
		// install and fetch weather HERE ** TODO **
		wyrm = new Dragon (12345, 5,5,5,5) ;	// no fight in testlog
		log.logDragon(wyrm);
		log.logError("Testing error logging");
		log.logResult("Success!, Dragon wins");
	}
	
	public static void main(String []args) {
		int runtimes = DEFAULT_RUN;
		try {
			if (args.length >= 1 && Integer.valueOf(args[0]) > 0) {
				runtimes = Integer.valueOf(args[0]); // if first arg is number, run that many times, otherwise
			} 										// default number of times remains. See definitions in beginning.
			if (args.length >= 2) { // second arg is logfile extension.
				log = new ResultLogger(FILENAME, args[1]); 
			} else {
				int time = (int) (System.currentTimeMillis() % LOG_EXTENSION); // or pseudorandom number if not given
				log = new ResultLogger(FILENAME, Integer.toString(time));				
			}
		} catch (NumberFormatException e) { // first parameter was not integer.
				System.out.println("first arg not a number, using it as filename add");
				log = new ResultLogger(FILENAME, args[0]); 			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		TestLog tl = new TestLog();
		tl.runGame(runtimes);
		
		log.end();
	}
}