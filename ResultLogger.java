/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	ResultLogger logs the fight participant details and *
*		fight result to a file. 						*
*   constructor: filename for base filename, extended 	*
* 		with timestamp									*
*	methods: methods to log game start, knight, dragon, *
*  		weather and game result.						*
*********************************************************/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Hashtable;

public class ResultLogger {
	
	private BufferedWriter scribe;
	private File logFile;
	private int success = 0;
	private int failure = 0;
	
	public ResultLogger(String filename, String add) {
		try		
		{
			System.out.println("See log: "+filename+add+".log");

			logFile = new File(filename+add+".log");
			this.scribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile)));
						
		} catch (Exception e) {
			System.out.println ("No logfile: reason "+e.getMessage());
		}
	}

	public void end() {		
		int total = success+failure;
		int percentage;
		if (total > 0)
			percentage = success*100 / total;
		else 
			percentage = 0;
		try {
			scribe.write("**********");
			scribe.write("**********");
			scribe.write("**********");
			scribe.write("**********");
			scribe.write("******");
			scribe.newLine();
			scribe.newLine();
			scribe.write("Another invasion season draws to close.");
			scribe.newLine();
			scribe.write(+total+" Knights sought their fortune,");
			scribe.newLine();
			scribe.write(success+" of them ended up contributing to the dragon food pile.");
			scribe.newLine();
			scribe.write(failure+" knights mercilessly ravaged the lands.");
			scribe.newLine();
			scribe.write("Mugloar Defence Force prevented "+percentage+"% of attacks");
			scribe.newLine();
			this.scribe.close();
		} catch (Exception e) {
			System.out.println("Failure to write ending" + e.getMessage());
		}
	}
	
	public void logError(String error) {
		try {
			scribe.write("Woe unto us, a dark time has befallen us: "+ error);
			scribe.newLine();
		} catch (Exception e) {
			System.out.println ("Failure to write error message: "+error+" to logfile, reason given: "+e.getMessage());
		}
		
	}
	
	public void logGameStart(int gamenumber) {
		try {
			int digits = String.valueOf(gamenumber).length();
			int stars = ((46-2) /2) - ((digits) /2); 
			if (digits % 2 == 1) {
				for (int i = 0; i < stars-1;i++) { // odd number of digts
					scribe.write("*");
				}	
				scribe.write(" "+gamenumber+" ");
				for (int i = 0; i < stars;i++) { 
					scribe.write("*");
				}	
			}
			else {
				for (int i = 0; i < stars;i++) { 
					scribe.write("*");
				}	
				scribe.write(" "+gamenumber+" ");
				for (int i = 0; i < stars;i++) { 
					scribe.write("*");
				}	
				
			}
			scribe.newLine();
			scribe.newLine();
				
				//total of 18 characters. 2 whitespace, length of number, rest *.
		} catch (Exception e) {
			System.out.println ("Failure to log run "+gamenumber+" start to logfile, reason given: "+e.getMessage());
		}		
	}
	
	public void logKnight(Knight attacker) {
		try {
			scribe.write(attacker.toStringName());			
			scribe.newLine();
			scribe.write(attacker.toStringStats());
			scribe.newLine();
		} catch (Exception e) {
			System.out.println ("Failure to log Knight details: "+e.getMessage());
		}		
	}
	
	public void logDragon(Dragon defender) {
		try {
			scribe.write(defender.toString());			
			scribe.newLine();
		} catch (Exception e) {
			System.out.println ("Failure to log Dragon details: "+e.getMessage());
		}		
	}
	
	public void logResult(String resultString) {
		JSONDecodeLite js = new JSONDecodeLite();
		Hashtable result = js.parseSimpleJSON(resultString);
		String resultType = (String)result.get("status");
		if (resultType.equals("Victory")) {
			this.success +=1;
		} else {
			this.failure +=1;	
		}
		try {
			scribe.write(resultType+" : "+(String)result.get("message"));			
			scribe.newLine();
			scribe.newLine();
		} catch (Exception e) {
			System.out.println ("Failure to log Battle details: "+e.getMessage());
		}		
	}
	
	public void logNoCombatSuccess(String noCombatVictoryMessage) {
		this.success += 1;
		try {
			scribe.write(noCombatVictoryMessage);			
			scribe.newLine();
			scribe.newLine();
		} catch (Exception e) {
			System.out.println ("Failure to log Storm win due to: "+e.getMessage());
		}		
		
	}
	
}
