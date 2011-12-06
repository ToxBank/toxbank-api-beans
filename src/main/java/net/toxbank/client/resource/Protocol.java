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
	private Project project;
	public static final String id_prefix="SEURAT-Protocol";
	public static final String id_pattern=String.format("%s%s",id_prefix,"-%d-%d"); 
	
	protected int version;
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	private Organisation organisation;
	private User owner;
	private List<String> keywords;
	private ToxBankResourceSet<User> authors;
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
		if (keywords == null) keywords =  new ArrayList<String>();
		return keywords;
	}

	public void addAuthor(User author) {
		if (author == null) return;

		if (authors == null) authors = new ToxBankResourceSet<User>();
		authors.add(author);
	}

	public void removeAuthor(User author) {
		if (author == null || authors == null) return;
		if (authors.contains(author)) authors.remove(author);
	}

	public ToxBankResourceSet<User> getAuthors() {
		if (authors == null) authors = new ToxBankResourceSet<User>();
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
