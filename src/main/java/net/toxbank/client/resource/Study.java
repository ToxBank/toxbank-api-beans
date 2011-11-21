package net.toxbank.client.resource;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class Study extends AbstractToxBankResource {

	private List<String> keywords;
	private String abstrakt;
	private boolean isSummarySearchable;
	private User author;
	private Assay assay;
	private Organisation owner;
	private Project project;
	private String versionInfo;

	public Study() {}
	
	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Study:Retrieve">API documentation</a>.
	 */
	public Study(URL identifier) {
		// FIXME: implement retrieving metadata from the URL and set the below fields
		this.setOwner(null);
	}

	public void setOwner(Organisation owner) {
		this.owner = owner;
	}

	public Organisation getOwner() {
		return owner;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Study:RetrieveList">API documentation</a>.
	 */
	public static List<URL> list(String server) {
		// FIXME: retrieve a list of all registered protocols
		return Collections.emptyList();
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Study:Upload">API documentation</a>.
	 */
	public URL upload(String server) {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	public void addKeyword(String keyword) {
	}

	public void removeKeyword(String keyword) {
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setAbstract(String abstrakt) {
		this.abstrakt = abstrakt;
	}

	public String getAbstract() {
		return abstrakt;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getAuthor() {
		return author;
	}

	public void setSummarySearchable(boolean isSummarySearchable) {
		this.isSummarySearchable = isSummarySearchable;
	}

	public boolean isSummarySearchable() {
		return isSummarySearchable;
	}

	public void setAssay(Assay assay) {
		this.assay = assay;
	}

	public Assay getAssay() {
		return assay;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	public String getVersionInfo() {
		return versionInfo;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Study:RetrieveVersions">API documentation</a>.
	 */
	public List<URL> listVersions() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Study:RetrieveVersions">API documentation</a>.
	 * Equivalent to {@link #listVersions()} but returns {@link Study}s
	 * already populated with metadata from the database.
	 */
	public List<Study> getVersions() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}
}
