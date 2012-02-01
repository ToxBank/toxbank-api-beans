package net.toxbank.client.policy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Group;
import net.toxbank.client.resource.User;

/**
 * 
 * @author nina
 *
 */
public class AccessRights {
	protected String policyID;

	public String getPolicyID() {
		return policyID;
	}
	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}

	protected URL resource;
	protected List<PolicyRule> rules;
	
	public List<PolicyRule> getRules() {
		return rules==null?Collections.EMPTY_LIST:rules;
	}
	public void setRules(List<PolicyRule> rules) {
		this.rules = rules;
	}
	/**
	 * 
	 * @param userName
	 * @param get
	 * @param post
	 * @param put
	 * @param delete
	 * @return
	 */
	public PolicyRule addUserRule(String userName,Boolean get, Boolean post, Boolean put, Boolean delete) {
		User user = new User();
		user.setUserName(userName);
		return addUserRule(user,get,post,put,delete);
	}
	/**
	 * 
	 * @param groupName
	 * @param get
	 * @param post
	 * @param put
	 * @param delete
	 * @return
	 */
	public PolicyRule addGroupRule(String groupName,Boolean get, Boolean post, Boolean put, Boolean delete) {
		Group group = new Group();
		group.setGroupName(groupName);
		return addGroupRule(group,get,post,put,delete);
	}
	/**
	 * GET only allowed
	 * @param user  Should have non null username!
	 * @return
	 */
	public PolicyRule addReadOnlyUserRule(User user) {
		return addUserRule(user,true,false,false,false);
	}
	/**
	 * GET only allowed
	 * @param group  Should have non null groupname!
	 * @return
	 */
	public PolicyRule addReadOnlyGroupRule(Group group) {
		return addGroupRule(group,true,false,false,false);
	}
	/**
	 * 
	 * @param user
	 * @param get
	 * @param post
	 * @param put
	 * @param delete
	 * @return
	 */
	public PolicyRule addUserRule(User user, Boolean get, Boolean post, Boolean put, Boolean delete) {
		return addRule(new UserPolicyRule<User>(user,get,post,put,delete));
	}
	/**
	 * 
	 * @param group
	 * @param get
	 * @param post
	 * @param put
	 * @param delete
	 * @return
	 */
	public PolicyRule addGroupRule(Group group, Boolean get, Boolean post, Boolean put, Boolean delete) {
		return addRule(new GroupPolicyRule<Group>(group,get,post,put,delete));
	}
	/**
	 * Add policy rule
	 * @param rule
	 */
	public PolicyRule addRule(PolicyRule rule) {
		if (rules==null) rules = new ArrayList<PolicyRule>();
		rules.add(rule);
		return rule;
	}
	/**
	 * 
	 * @return
	 */
	public URL getResource() {
		return resource;
	}
	public void setResource(URL resource) {
		this.resource = resource;
	}

	public AccessRights(URL resource) {
		this(resource,null);
	}
	/**
	 * 
	 * @param resource
	 * @param rule
	 */
	public AccessRights(URL resource,PolicyRule rule) {
		setResource(resource);
		if (rule !=null) addRule(rule);
	}
	

	@Override
	public String toString() {
		
		return String.format("%s\t%s\n%s\n",getPolicyID()==null?"":getPolicyID(),getResource(),rules);
	}
}
