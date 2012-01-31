package net.toxbank.client.policy;

import net.toxbank.client.resource.User;

/**
 * User policy
 * @author nina
 *
 * @param <USER>
 */
public class UserPolicyRule<USER extends User> extends PolicyRule<USER> {
	
	public enum webform {
		allowReadByUser,
		allowPostByUser,
		allowPutByUser,
		allowDeleteByUser,
		denyReadByUser,
		denyPostByUser,
		denyPutByUser,
		denyDeleteByUser
	}
	
	/**
	 * Defines READ only policy
	 * @param subject {@link User}
	 */
	public UserPolicyRule(USER subject) {
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
	public UserPolicyRule(USER subject,Boolean get,Boolean post, Boolean put, Boolean delete) {
		super(subject,get,post,put,delete);
	}
}
