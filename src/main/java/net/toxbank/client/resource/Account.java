package net.toxbank.client.resource;

/**
 * Modeled after FOAF where possible.
 * 
 * @author egonw
 */
public class Account {

	private String service;
	private String accountName;

	public void setService(String service) {
		this.service = service;
	}
	public String getService() {
		return service;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountName() {
		return accountName;
	}

}
