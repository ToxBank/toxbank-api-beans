package net.toxbank.client.resource;

import java.net.URL;

public abstract class AbstractToxBankResource implements IToxBankResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8819428763217419573L;

	private URL resourceURL;
	private String title;
	
	public AbstractToxBankResource(URL resourceURL) {
		setResourceURL(resourceURL);
	}

	public AbstractToxBankResource() {
		this(null);
	}

	public void setResourceURL(URL resourceURL) {
		this.resourceURL = resourceURL;
	}

	public URL getResourceURL() {
		return resourceURL;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
