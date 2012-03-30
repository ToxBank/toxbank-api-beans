package net.toxbank.client.resource;

import net.toxbank.client.resource.Query.QueryType;

/**
 * Modeled according to iCalendar http://www.ietf.org/rfc/rfc2445.txt 
 * and NCAL http://www.semanticdesktop.org/ontologies/ncal/
 * @author nina
 *
 */
public class Alert<USER extends User> extends AbstractToxBankResource  {
	private static final long serialVersionUID = 7412323715113886505L;	

	public enum RecurrenceFrequency {secondly, minutely, hourly, daily, weekly, monthly,  yearly};
	public enum AlarmAction {audioAction, displayAction, emailAction, procedureAction};
	//ncal:byhour, ncal:bysetpos, ncal:bysecond, ncal:byminute, ncal:bymonth, ncal:bymonthday, ncal:byweekno, ncal:interval, ncal:count, ncal:byday, ncal:wkst, ncal:byyearday, ncal:until
	protected long sentAt;
	public long getSentAt() {
		return sentAt;
	}
	public void setSentAt(long sentAt) {
		this.sentAt = sentAt;
	}
	protected Query query;
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	protected RecurrenceFrequency recurrenceFrequency = RecurrenceFrequency.weekly;
	
	protected int recurrenceInterval = 1;
	protected USER user;
	
	public Alert() {
		this(new Query(),null);
	}
	public Alert(Query query, USER user) {
		this(query,user,RecurrenceFrequency.weekly,1);
	}
	public Alert(Query query, USER user, RecurrenceFrequency frequency) {
		this(query,user,frequency,1);
	}
	public Alert(Query query, USER user, RecurrenceFrequency frequency, int interval) {
		setRecurrenceFrequency(frequency);
		setRecurrenceInterval(interval);
		setUser(user);
		setQuery(query);

	}
	
	public String getQueryString() {
		if (query==null) query = new Query(); 
		return query.getContent();
	}
	public void setQueryString(String content) {
		if (query==null) query = new Query(); 
		query.setContent(content);
	}
	public QueryType getType() {
		if (query==null) query = new Query(); 
		return query.getType();
	}
	public void setType(QueryType type) {
		if (query==null) query = new Query(); 
		query.setType(type);
	}
	public RecurrenceFrequency getRecurrenceFrequency() {
		return recurrenceFrequency;
	}
	public void setRecurrenceFrequency(RecurrenceFrequency recurrenceFrequency) {
		this.recurrenceFrequency = recurrenceFrequency;
	}
	public int getRecurrenceInterval() {
		return recurrenceInterval;
	}
	public void setRecurrenceInterval(int recurrenceInterval) {
		this.recurrenceInterval = recurrenceInterval;
	}
	public USER getUser() {
		return user;
	}
	public void setUser(USER user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return
		String.format(
		"DESCRIPTION:%s\nCATEGORIES;search=%s:%s\nRRULE:FREQ=%s\nATTENDEE:%s\n",
		getTitle(),
		getQueryString(),getType(),getRecurrenceFrequency(),
		getUser()!=null && getUser().getResourceURL()!=null?getUser().getResourceURL():""
				);
	}
}
