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
public class Policy {
	
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

	public Policy(URL resource) {
		this(resource,null);
	}
	/**
	 * 
	 * @param resource
	 * @param rule
	 */
	public Policy(URL resource,PolicyRule rule) {
		setResource(resource);
		if (rule !=null) addRule(rule);
	}
}
