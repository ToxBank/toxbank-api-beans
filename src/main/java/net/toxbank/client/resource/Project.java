package net.toxbank.client.resource;

import java.net.URL;

public class Project extends Group {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1097822695655002870L;

	public Project() {
		this(null);
	}
	public Project(URL resourceURL) {
		setResourceURL(resourceURL);
	}
}
