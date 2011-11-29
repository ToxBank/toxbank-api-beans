package net.toxbank.client.resource;

/**
 * Common ancestor for {@link Organisation} and {@link Project}
 */
public class Group extends AbstractToxBankResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6788788684779974881L;

	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
