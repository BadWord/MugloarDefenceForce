/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	JSONDecodeLite is a lightweight decoder of a simple	*
*		JSON object, specifically works with a Mugloar  *
		Knight JSON coded								*	
*	constructor: no parameters							*
*	methods: parseJSONKnight							*
*********************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.Hashtable;


public class JSONDecodeLite {
	
	private static int HASH_SIZE = 9;
	
	private Hashtable<String, String> objectData;
	
//	private String testString;
	
	public JSONDecodeLite () {
		this.objectData = new Hashtable<String, String> (HASH_SIZE);		
//		testString = ("{\"gameId\":7003808,\"knight\":{\"name\":\"Sir. Christian Todd of Newfoundland and Labrador\",\"attack\":6,\"armor\":5,\"agility\":8,\"endurance\":1}}");
	}

		
	public void separateKV(String keyValue) {
		
		int valueNumber;
		String[] pair = keyValue.split (":");

		if (pair.length == 1) return;
		
		
		pair[0] = pair[0].substring (1, pair[0].length()-1); // strip quote marks
		
		if (pair[1].charAt(0) == '"') { // value is word
		
			pair[1] = pair[1].substring (1, pair[1].length()-1); // strip quote marks
			
			
		} 
			
		objectData.put(pair[0], pair[1]); // even if numeric type is String, String
		
//		System.out.println("Key: "+ pair[0] + " Value: " + pair[1] );
			
		
	}
	
	public void parseIntoKV(String multiplepairs) {
		
		String[] kV = multiplepairs.split(",");
		
		for (String s:kV) {
			if (s.length() > 4 ) {	// "key":value is 5 characters minimally e.g. "a":4 
				separateKV(s);
			}
		}
		
	}
	
	
	public Hashtable parseSimpleJSON (String rawdata) {
			
		String[] parsedString;

		parsedString = rawdata.split("\\{|}");
	
			for (String s:parsedString) {
				if (s.length() > 4) {	 // "key":value minimally 5 characters
					parseIntoKV(s);
				}
			}
		
		
		return this.objectData;
	}
		
}