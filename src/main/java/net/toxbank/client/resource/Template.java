package net.toxbank.client.resource;

import java.net.URL;

public class Template extends AbstractToxBankResource {

	public Template() {}
	
	public Template(URL identifier) {
		setResourceURL(identifier);
	}

}
