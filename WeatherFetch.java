/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	WeaterFetch handles sending a get request via 		*
*		parent class and creates an enumeration with 	*
*		proper weather selected.						*	
*	it extends Fetch, which handles get methods.		*
*	methods: getWeather, parseWeather					*
*********************************************************/
import java.net.MalformedURLException;
import java.util.regex.*;

public class WeatherFetch extends Fetch {
		
	public WeatherFetch (String contactURLStart, int gameID, String userAgent) 
			throws MalformedURLException {
		super(new String(contactURLStart+gameID),userAgent);
	}

	public String parseWeather(String weatherInXML){ 
		Pattern p = Pattern.compile("(<code>)(.*)(</code>)");
		Matcher m = p.matcher(weatherInXML);
		m.find();
		String code = m.group(2);		

		// NMR, T E, HVA, SRO, FUNDEFINEDG
		return code;
	}
	
	public String getWeather() throws Exception { 
	
		String forecast = sendGetRequest();
		
		return parseWeather(forecast);
	
	}
	
}