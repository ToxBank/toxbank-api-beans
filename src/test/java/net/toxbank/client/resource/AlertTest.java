package net.toxbank.client.resource;

import net.toxbank.client.resource.Alert.RecurrenceFrequency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlertTest extends AbstractToxBankResourceTest {
	
	@Before
	public void setup() {
		setToxBankResource(new Alert());
	}

	@Test
	public void testConstructor() {
		Alert alert = new Alert();
		Assert.assertNotNull(alert);
	}
	
	@Test
	public void testGetSetQuery() {
		
		Alert alert = new Alert();
		Assert.assertNull(alert.getUser());
		User user = new User();
		alert.setUser(user);
		Assert.assertNotNull(alert.getUser());
		alert.setQueryString("cell");
		Assert.assertEquals("cell",alert.getQueryString());
		alert.setRecurrenceFrequency(RecurrenceFrequency.monthly);
		Assert.assertEquals(RecurrenceFrequency.monthly,alert.getRecurrenceFrequency());
		alert.setRecurrenceInterval(3);
		Assert.assertEquals(3,alert.getRecurrenceInterval());
	}
	/*
	This is a test for potential support of iCalendar format , via ical4j
	public void test() throws Exception {
		
		Recur recur = new Recur(Recur.WEEKLY,null);
		recur.setWeekStartDay(WeekDay.MO.toString());
		RRule rule = new RRule(recur);
		
		
		VAlarm alarm = new VAlarm();
		alarm.getProperties().add(Action.EMAIL);

		Categories cat = new Categories("FREETEXTSEARCH");
		cat.getParameters().add(new XParameter("search", "toxicity"));
		cat.getParameters().add(new XParameter("scope", "all"));
		
		VEvent vevent = new VEvent();
		vevent.getProperties().add(new Description("Search alert"));
		vevent.getProperties().add(cat);
		vevent.getAlarms().add(alarm);
		vevent.getProperties().add(rule);
		vevent.getProperties().add(new Attendee("http://example.com/U1"));
		vevent.getProperties().add(new Uid(Uid.RRULE));

  ATTENDEE;CN=John Smith;DIR="ldap://host.com:6666/o=eDABC%
   20Industries,c=3DUS??(cn=3DBJim%20Dolittle)":MAILTO:jimdo@
   host1.com
		
		Calendar cal = new Calendar();
		cal.getProperties().add(new ProdId("-//My product//iCal4j 1.0//EN"));
		cal.getProperties().add(Version.VERSION_2_0);
		cal.getProperties().add(CalScale.GREGORIAN);
		
		
		//cal.getProperties().add(Uid.  new Uid("abcde"));
		cal.getComponents().add(vevent);
		CalendarOutputter out = new CalendarOutputter();
		out.setValidating(true);
		out.output(cal, System.out);
	}
	*/

}

