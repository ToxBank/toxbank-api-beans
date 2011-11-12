package net.toxbank.client.resource;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class User {

	public User() {}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveList">API documentation</a>.
	 */
	public User(URL identifier) {
		// FIXME: implement retrieving metadata from the URL and set the below fields
	}
	
	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveList">API documentation</a>.
	 */
	public static List<URL> list(String server) {
		// FIXME: retrieve a list of all registered protocols
		return Collections.emptyList();
	}

}
