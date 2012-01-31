package net.toxbank.client.policy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	 * Add policy rule
	 * @param rule
	 */
	public void addRule(PolicyRule rule) {
		if (rules==null) rules = new ArrayList<PolicyRule>();
		rules.add(rule);
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
