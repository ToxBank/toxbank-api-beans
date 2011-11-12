package net.toxbank.client.resource;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class Protocol {

	private URL organisation;

	public Protocol() {}
	
	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveMetadata">API documentation</a>.
	 */
	public Protocol(URL identifier) {
		// FIXME: implement retrieving metadata from the URL and set the below fields
		this.organisation = null;
	}
	
	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveList">API documentation</a>.
	 */
	public static List<URL> listProtocols(String server) {
		// FIXME: retrieve a list of all registered protocols
		return Collections.emptyList();
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:Uploadt">API documentation</a>.
	 */
	public URL upload(String server) {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	public URL getOrganisation() {
		return this.organisation;
	}
}
