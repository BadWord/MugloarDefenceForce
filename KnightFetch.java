/********************************************************
*	Dragons Of Mugloar - Mugloar Defence Force			*
*	A solution by Tommi Nirha							*
*														*
*	KnightFetch handles sending a get request and 		*
*		parses incoming JSON into hashtable, creating	*
*		a Knight class from it.							*	
*	it extends Fetch, which handles get methods.		*
*	methods: fetchKnight								*
*********************************************************/
import java.net.MalformedURLException;

public class KnightFetch extends Fetch {
		
	public KnightFetch (String contactURL, String userAgent) throws MalformedURLException {
		super(contactURL,userAgent);
	}

	public Knight getKnight() throws Exception { 

	
		String armoredGuy = sendGetRequest();
		
		JSONDecodeLite transcribe = new JSONDecodeLite();
		
		Knight candidate = new Knight(transcribe.parseSimpleJSON(armoredGuy));
		
		// we have a null knight from transcribe
		return candidate;
	
	}
	
}