package net.toxbank.client.policy;

import java.util.ArrayList;

import net.toxbank.client.resource.IToxBankResource;

/**
 * Policy rule
 * @author nina
 *
 */
public class PolicyRule<T extends IToxBankResource> {

	protected T subject;

	public enum Method {
		GET,PUT,POST,DELETE
		//,HEAD,OPTIONS
	}
	protected Boolean[] actions = new Boolean[] {null,null,null,null};
	
	public Boolean[] getActions() {
		return actions;
	}
	/**
	 * @param subject
	 */
	public PolicyRule(T subject) {
		this(subject,true,null,null,null);
	}
	/**
	 * 
	 * @param subject
	 * @param get
	 * @param post
	 * @param put
	 * @param delete
	 */
	public PolicyRule(T subject,Boolean get,Boolean post, Boolean put, Boolean delete) {
		setSubject(subject);
		setAllowDELETE(delete);
		setAllowGET(get);
		setAllowPOST(post);
		setAllowPUT(put);
	}
	
	public T getSubject() {
		return subject;
	}
	public void setSubject(T subject) {
		this.subject = subject;
	}

	public Boolean allows(Method method) {
		return actions[method.ordinal()];
	}
	
	public Boolean allows(String method) {
		try {
			return allows(Method.valueOf(method));
		} catch (Exception x) { return null;}
	}
	private void setAllow(Method method, Boolean value) {
		actions[method.ordinal()] = value;
	}
	public Boolean allowsGET() {
		return allows(Method.GET);
	}
	
	public void setAllowGET(Boolean allow) {
		setAllow(Method.GET,allow);
	}
	public Boolean allowsPOST() {
		return allows(Method.POST);
	}
	public void setAllowPOST(Boolean allow) {
		setAllow(Method.POST,allow);
	}
	public Boolean allowsPUT() {
		return allows(Method.PUT);
	}
	public void setAllowPUT(Boolean allow) {
		setAllow(Method.POST,allow);
	}
	public boolean allowsDELETE() {
		return allows(Method.DELETE);
	}
	public void setAllowDELETE(Boolean allow) {
		setAllow(Method.DELETE,allow);
	}
	public String[] getActionsAsArray() {
		ArrayList<String> a = new ArrayList<String>();
		for (Method method : Method.values()) {
			if (actions[method.ordinal()]) a.add(method.toString());
		}
		return a.toArray(new String[a.size()]);
	}
}
