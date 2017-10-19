/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	class Battlesend handles http PUT method to send 	*
*		our dragon to the api.							*
*   constructor: start of url, Dragon, end of url0		*
*	methods: fight 										*
*********************************************************/
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

public class BattleSend {
	
	private URL combatLocation;
	private String userAgent;
	private Dragon combatant;
	
	public BattleSend(String start, Dragon scaleTerror, String end, String userAgentString) throws MalformedURLException {
		this.userAgent = userAgentString;
		
		String combatLocationString = new String(start+scaleTerror.getID()+end);
		
		combatLocation = new URL(combatLocationString);
		this.combatant = scaleTerror;
	}
	
	public String fight () throws Exception {
		
		HttpURLConnection putAPIConn = (HttpURLConnection) this.combatLocation.openConnection();
		putAPIConn.setDoOutput(true);
		putAPIConn.setRequestMethod("PUT");
		putAPIConn.setRequestProperty("User-Agent", this.userAgent);
		putAPIConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		OutputStreamWriter scribe = new OutputStreamWriter(putAPIConn.getOutputStream());
		scribe.write(this.combatant.toJSONString());
		scribe.close();
		putAPIConn.getInputStream();
		
		BufferedReader oneWhoReads = new BufferedReader(
		        new InputStreamReader(putAPIConn.getInputStream()));
		String lineOfInput;
		StringBuffer responseBuffer = new StringBuffer();

		while ((lineOfInput = oneWhoReads.readLine()) != null) {
			responseBuffer.append(lineOfInput);
		}
		oneWhoReads.close();
		
		return (responseBuffer.toString());
		
	}
	
	
}