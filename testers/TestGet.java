import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TestGet {

	private final String USER_AGENT = "MugloarDefenceForce/0.1 <Tommi Nirha>";

	public static void main(String[] args) throws Exception {

		TestGet getTest = new TestGet();

		System.out.println("Testing 1 - Send Http GET request");
		getTest.sendGet();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://www.dragonsofmugloar.com/api/game";

		URL target = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) target.openConnection();

		// optional default is GET
		conn.setRequestMethod("GET");

		//add request header
		conn.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

}