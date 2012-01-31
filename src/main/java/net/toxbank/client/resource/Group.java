package net.toxbank.client.resource;

import java.net.URL;

/**
 * Common ancestor for {@link Organisation} and {@link Project}
 */
public class Group extends AbstractToxBankResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6788788684779974881L;

	private String groupName;
	private URL cluster;

	public URL getCluster() {
		return cluster;
	}

	public void setCluster(URL cluster) {
		this.cluster = cluster;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s)", 
				getTitle()==null?"":getTitle(),
				getGroupName()==null?"":getGroupName());
	}
}
