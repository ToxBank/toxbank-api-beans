package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Modeled after FOAF where possible.
 * 
 * @author egonw
 */
public class User extends AbstractToxBankResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9147403734724679765L;
	private String userName;

	private Organisation seuratProject;
	private Organisation institute;
	private String firstname;
	private String lastname;
	private URL homepage;
	private URL weblog;
	private List<Account> accounts;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void addAccount(Account account) {
		if (account == null) return; // nothing to add
		if (accounts == null) accounts = new ArrayList<Account>();
		accounts.add(account);
		
		// make sure the account has a resource URI
		if (account.getResourceURL() == null &&
			this.getResourceURL() != null) {
			try {
				// IMPORTANT: this URI pattern is used in the roundtripping
				account.setResourceURL(
					new URL(this.getResourceURL().toString() + "/accounts/" + accounts.size())
				);
			} catch (MalformedURLException e) {
				// OK, forget it...
			}
		}
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
