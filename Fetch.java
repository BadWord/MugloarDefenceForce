/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	Fetch is a class that provides methods to handle 	*
*	HTTP Get requests.									*
*	it extends Fetch, which handles get methods.		*
*	methods: fetchKnight								*
*********************************************************/
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fetch {
	
	protected String userAgent;
	protected URL destination;
	
	protected Fetch (String fetchURL, String userAgent) throws MalformedURLException {
//		System.out.println("using URL: "+fetchURL);
		this.destination = new URL (fetchURL);
		this.userAgent = userAgent;
	}
	
	protected String sendGetRequest () throws Exception {

		HttpURLConnection pipe = (HttpURLConnection) destination.openConnection();
		
		pipe.setRequestMethod("GET");
		pipe.setRequestProperty("User-Agent", userAgent);
		
		BufferedReader whoCanReadAnyway = new BufferedReader(
		        new InputStreamReader(pipe.getInputStream()));
		String oneLineInput;
		StringBuffer responseGiven = new StringBuffer();

		while ((oneLineInput = whoCanReadAnyway.readLine()) != null) {
			responseGiven.append(oneLineInput);
		}
		whoCanReadAnyway.close();
		
		return responseGiven.toString();
		
	}
	
	
}

	