package net.toxbank.client.resource;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Protocol extends AbstractToxBankResource {

	private URL organisation;
	private User author;
	private List<String> keywords;
	private String title;
	private String identifier;
	private String abstrakt;

	public Protocol() {}
	
	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveMetadata">API documentation</a>.
	 */
	public Protocol(URL identifier) {
		// FIXME: implement retrieving metadata from the URL and set the below fields
		this.organisation = null;
	}
	
	// bean methods
	
	public void addKeyword(String keyword) {
		if (keyword == null) return;

		if (keywords == null) keywords = new ArrayList<String>();
		keywords.add(keyword);
	}

	public void removeKeyword(String keyword) {
		if (keyword == null || keywords == null) return;
		
		if (keywords.contains(keyword)) keywords.remove(keyword);
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getAuthor() {
		return author;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveList">API documentation</a>.
	 */
	public static List<URL> listProtocols(String server) {
		// FIXME: retrieve a list of all registered protocols
		return Collections.emptyList();
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:Upload">API documentation</a>.
	 */
	public URL upload(String server) {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:Retrieve">API documentation</a>.
	 */
	public List<URL> listFiles() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveVersions">API documentation</a>.
	 */
	public List<URL> listVersions() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveVersions">API documentation</a>.
	 * Equivalent to {@link #listVersions()} but returns {@link ProtocolVersion}s
	 * already populated with metadata from the database.
	 */
	public List<ProtocolVersion> getVersions() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveTemplates">API documentation</a>.
	 */
	public List<URL> listTemplates() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:RetrieveTemplates">API documentation</a>.
	 * Equivalent to {@link #listTempaltes()} but returns {@link Template}s
	 * already populated with metadata from the database.
	 */
	public List<Template> getTemplates() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	public URL getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(URL organisation) {
		this.organisation = organisation;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setAbstract(String abstrakt) {
		this.abstrakt = abstrakt;
	}

	public String getAbstract() {
		return abstrakt;
	}
}
