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
	private User author;
	private List<String> keywords;
	private String title;
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

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getAuthor() {
		return author;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(Organisation organisation) {
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
