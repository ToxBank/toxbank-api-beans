package net.toxbank.client.resource;

import java.net.URL;

public class Organisation extends Group {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7810201736399572742L;

	public Organisation() {
		this(null);
	}
	public Organisation(URL resourceURL) {
		setResourceURL(resourceURL);
	}

}
