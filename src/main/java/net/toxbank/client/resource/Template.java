package net.toxbank.client.resource;

import java.net.URL;

public class Template extends AbstractToxBankResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -594888466228546223L;

	public Template() {}
	
	public Template(URL identifier) {
		setResourceURL(identifier);
	}

}
