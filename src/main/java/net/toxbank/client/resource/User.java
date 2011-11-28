package net.toxbank.client.resource;

import java.net.URL;
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

	public User(URL identifier) {
		setResourceURL(identifier);
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
