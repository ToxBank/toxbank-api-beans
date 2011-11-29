package net.toxbank.client.resource;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Protocol extends AbstractToxBankResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8372109619710612869L;
	private Organisation organisation;
	private User owner;
	private List<String> keywords;
	private List<User> authors;
	private String identifier;
	private String abstrakt;

	public Protocol() {}
	
	public Protocol(URL identifier) {
		setResourceURL(identifier);
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
		if (keywords == null) return Collections.emptyList();
		return keywords;
	}

	public void addAuthor(User author) {
		if (author == null) return;

		if (authors == null) authors = new ArrayList<User>();
		authors.add(author);
	}

	public void removeAuthor(User author) {
		if (author == null || authors == null) return;
		
		if (authors.contains(author)) authors.remove(author);
	}

	public List<User> getAuthors() {
		if (authors == null) return Collections.emptyList();
		return authors;
	}

	public void setOwner(User author) {
		this.owner = author;
	}

	public User getOwner() {
		return owner;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
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
