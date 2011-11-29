package net.toxbank.client.resource;

import java.io.Serializable;
import java.net.URL;

public interface IToxBankResource extends Serializable {

	public void setResourceURL(URL resourceURL);
	
	public URL getResourceURL();
	
}
