package net.toxbank.client.io.rdf;

import net.toxbank.client.resource.Alert.RecurrenceFrequency;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;

/**
BEGIN:VCALENDAR
PRODID:-//???//iCal4j 1.0//EN
VERSION:2.0
CALSCALE:GREGORIAN
BEGIN:VEVENT
DTSTAMP:20120216T173320Z
DESCRIPTION:Search alert
CATEGORIES;search=toxicity;scope=all:FREETEXTSEARCH
RRULE:FREQ=WEEKLY;WKST=MO
ATTENDEE:http://example.com/U1
UID:RRULE
BEGIN:VALARM
ACTION:EMAIL
END:VALARM
END:VEVENT
END:VCALENDAR
 * @author nina
 *
 */
public class NCAL {
	public static final String URI ="http://www.semanticdesktop.org/ontologies/ncal/";
	
    private static final Resource resource(String local) {
        return ResourceFactory.createResource(String.format("%s%s",URI,local));
    }

    private static final Property property(String local) {
        return ResourceFactory.createProperty(String.format("%s%s",URI,local));
    }
    
    private static final Resource instance(RecurrenceFrequency local,Resource type) {
    	return ResourceFactory.createResource(String.format("%s%s",URI,local)).addProperty(RDF.type, type);
    }
    

 
    /**
     * Classes
     */
    public static final Resource Event = resource("Event");
    public static final Resource RecurrenceRule = resource("RecurrenceRule");
    public static final Resource RecurrenceFrequency = resource("RecurrenceFrequency");
    public static final Resource Alarm = resource("Alarm");
    public static final Resource AlarmAction = resource("AlarmAction");
    public static final Resource Attendee = resource("Attendee");
    
    
    
    /**
     * Properties
     */
    public static final Property summary = property("summary");
    public static final Property categories = property("categories");
    public static final Property prodid = property("prodid");
    public static final Property rrule = property("rrule");
    public static final Property freq = property("freq");
    public static final Property interval = property("interval");
    public static final Property action = property("action");
    public static final Property attendee = property("attendee");
    

}
