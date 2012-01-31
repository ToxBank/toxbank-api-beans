package net.toxbank.client.policy;

import net.toxbank.client.resource.Group;

/**
 * Group policy
 * @author nina
 *
 * @param <GROUP>
 */
public class GroupPolicyRule<GROUP extends Group> extends PolicyRule<GROUP> {

	public enum webform {
		allowReadByGroup,
		allowPostByGroup,
		allowPutByGroup,
		allowDeleteByGroup,
		denyReadByGroup,
		denyPostByGroup,
		denyPutByGroup,
		denyDeleteByGroup;
	}
	/**
	 * Defines READ only policy
	 * @param subject {@link Group}
	 */
	public GroupPolicyRule(GROUP subject) {
		super(subject);
	}
	/**
	 * 
	 * @param subject
	 * @param get true/false/null
	 * @param post true/false/null
 	 * @param put true/false/null
	 * @param delete true/false/null
	 */
	public GroupPolicyRule(GROUP subject,Boolean get,Boolean post, Boolean put, Boolean delete) {
		super(subject,get,post,put,delete);
	}
	
	@Override
	public String toString() {
		return String.format("%s\tGroup=%s\tGET=%s\tPOST=%s\tPUT=%s\tDELETE=%s\n", 
					getName()==null?"":getName(),
					getSubject().getGroupName()==null?getSubject().getResourceURL():getSubject().getGroupName(),
					allowsGET()==null?"":allowsGET(),
					allowsPOST()==null?"":allowsPOST(),
					allowsPUT()==null?"":allowsPUT(), 
					allowsDELETE()==null?"":allowsDELETE()
					);
	}
}
