package net.toxbank.client.resource;

/**
 * Modeled after FOAF where possible.
 * 
 * @author egonw
 */
public class Account extends AbstractToxBankResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3429767766313633591L;
	private String service;
	private String accountName;

	/**
	 * foaf:accountServiceHomepage
	 */
	public void setService(String service) {
		this.service = service;
	}
	public String getService() {
		return service;
	}
	/**
	 * foaf:accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountName() {
		return accountName;
	}

}
