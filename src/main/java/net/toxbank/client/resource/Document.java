package net.toxbank.client.resource;

import java.net.URL;

public class Document extends AbstractToxBankResource {

	private static final long serialVersionUID = 7166009221679269640L;

	private URL license;

	public Document() {
		super();
	}
	public Document(URL resourceURL) {
		super(resourceURL);
	}
	public void setLicense(URL license) {
		this.license = license;
	}
	public URL getLicense() {
		return license;
	}
	
}
