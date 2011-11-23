package net.toxbank.client.resource;

import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Modeled after FOAF where possible.
 * 
 * @author egonw
 */
public class User extends AbstractToxBankResource {

	private Organisation seuratProject;
	private Organisation institute;
	private String title;
	private String firstname;
	private String lastname;
	private URL homepage;
	private URL weblog;
	private List<Account> accounts;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public URL getHomepage() {
		return homepage;
	}

	public void setHomepage(URL homepage) {
		this.homepage = homepage;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

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

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveProtocols">API documentation</a>.
	 */
	public List<URL> listProtocols() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveProtocols">API documentation</a>.
	 * Equivalent to {@link #listProtocols()} but returns {@link Protocol}s
	 * already populated with metadata from the database.
	 */
	public List<Protocol> getProtocols() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveStudies">API documentation</a>.
	 */
	public List<URL> listStudies() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveStudies">API documentation</a>.
	 * Equivalent to {@link #listStudies()} but returns {@link Study}s
	 * already populated with metadata from the database.
	 */
	public List<Study> getStudies() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveAlerts">API documentation</a>.
	 */
	public List<URL> listAlerts() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/User:RetrieveAlerts">API documentation</a>.
	 * Equivalent to {@link #listAlerts()} but returns {@link Alert}s
	 * already populated with metadata from the database.
	 */
	public List<Alert> getAlerts() {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	public void setSeuratProject(Organisation seuratProject) {
		this.seuratProject = seuratProject;
	}

	public Organisation getSeuratProject() {
		return seuratProject;
	}

	public void setInstitute(Organisation institute) {
		this.institute = institute;
	}

	public Organisation getInstitute() {
		return institute;
	}

	public void setWeblog(URL weblog) {
		this.weblog = weblog;
	}

	public URL getWeblog() {
		return weblog;
	}
}
