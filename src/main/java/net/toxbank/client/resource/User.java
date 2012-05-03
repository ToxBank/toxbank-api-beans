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

	private ToxBankResourceSet<Project> projects;
	private ToxBankResourceSet<Organisation> organisations;
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

	public void setProjects(ToxBankResourceSet<Project> projects) {
		this.projects = projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = new ToxBankResourceSet<Project>(projects);
	}
		
	public void addProject(Project project) {
		if (project == null) return;
		if (projects == null) this.projects = new ToxBankResourceSet<Project>();
		projects.add(project);		
	}

	public ToxBankResourceSet<Project> getProjects() {
		return projects;
	}

	public void setOrganisations(ToxBankResourceSet<Organisation> orgs) {
		this.organisations = orgs;
	}

	public void setOrganisations(List<Organisation> orgs) {
		this.organisations = new ToxBankResourceSet<Organisation>(orgs);
	}
	
	public ToxBankResourceSet<Organisation> getOrganisations() {
		return organisations;
	}
	public void addOrganisation(Organisation org) {
		if (org == null) return;
		if (organisations == null) this.organisations = new ToxBankResourceSet<Organisation>();
		organisations.add(org);		
	}	

	public void setWeblog(URL weblog) {
		this.weblog = weblog;
	}

	public URL getWeblog() {
		return weblog;
	}
	@Override
	public String toString() {
		return String.format("%s %s %s (%s)", 
				getTitle()==null?"":getTitle(),
				getFirstname()==null?"":getFirstname(),
				getLastname()==null?"":getLastname(),
				getUserName()==null?"":getUserName());
	}
	
	public static final String mailto = "mailto";
	/**
	 * Wrapper to access the first email account
	 * @return
	 */
	public String getEmail() {
		List<Account> accounts = getAccounts();
		if ((accounts==null) || (accounts.size()==0)) return null;
		for (Account account : accounts) if (mailto.equals(account.getService())) return account.getAccountName();
		return null;
	}
	/**
	 * Wrapper to set the first email account
	 * @param email
	 */
	public void setEmail(String email) {
		List<Account> accounts = getAccounts();
		Account newAccount = null;
		if (accounts!=null) 
			for (Account account : accounts) {
				if (mailto.equals(account.getService()))  {
					account.setAccountName(email);
					newAccount = account;
					break;
				}
			}
		else { 
			newAccount = new Account(); 
			newAccount.setAccountName(email);newAccount.setService(mailto);
			addAccount(newAccount);
		}
		if (newAccount!=null)
		try {
			newAccount.setResourceURL(new URL(String.format("%s:%s", mailto,email)));
		} catch (Exception x) { x.printStackTrace();
			newAccount.setResourceURL(null); 
		}		

	}
}
