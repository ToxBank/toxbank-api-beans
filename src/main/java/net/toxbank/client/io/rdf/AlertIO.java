package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Alert;
import net.toxbank.client.resource.Alert.RecurrenceFrequency;
import net.toxbank.client.resource.Query.QueryType;
import net.toxbank.client.resource.User;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * Modeled according to iCalendar http://www.ietf.org/rfc/rfc2445.txt 
 * and NCAL http://www.semanticdesktop.org/ontologies/ncal/
 * @author nina
 *
 */
public class AlertIO  extends AbstractIOClass<Alert> {

	@Override
	public Resource objectToJena(Model toAddTo, Alert alert)
			throws IllegalArgumentException {
		if (alert.getResourceURL() == null) {
			throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "alerts"));
		}
		Resource res = toAddTo.createResource(alert.getResourceURL().toString());
		toAddTo.add(res, RDF.type, NCAL.Event);
		if (alert.getTitle()!=null)
			toAddTo.add(res, DCTerms.title, alert.getTitle());
		//category, e.g. freetext search
		if (alert.getQuery().getType()!=null)
			toAddTo.add(res, NCAL.categories, alert.getQuery().getType().name());
		//this could be better a category parameter, but ncal:category is a string ...
		if (alert.getQuery().getContent()!=null)
			toAddTo.add(res, DCTerms.subject, alert.getQuery().getContent());
		//recurrence rule
		Resource rrule = toAddTo.createResource();
		toAddTo.add(rrule, RDF.type, NCAL.RecurrenceRule);
		//frequency, e.g. weekly
		Resource frequency = toAddTo.createResource(String.format("%s%s",NCAL.URI,alert.getRecurrenceFrequency().name())).addProperty(RDF.type, NCAL.RecurrenceFrequency);
		toAddTo.add(rrule,NCAL.freq,frequency);
		//interval , e.g. 2 means every second week
		toAddTo.addLiteral(rrule,NCAL.interval,alert.getRecurrenceInterval());
		toAddTo.add(res,NCAL.rrule, rrule);
		//the user
		if ((alert.getUser()!=null) && (alert.getUser().getResourceURL()!=null)) {
			Resource user = toAddTo.createResource(alert.getUser().getResourceURL().toString());
			toAddTo.add(user, RDF.type, NCAL.Attendee);
			toAddTo.add(user, RDF.type, TOXBANK.USER);
			toAddTo.add(res,NCAL.attendee,user);
		}
		return res;
	}
	/*
	public static final Resource alarmaction_instance(Alert.AlarmAction action) {
	    	return ResourceFactory.createResource(String.format("%s%s",URI,action.name())).addProperty(RDF.type, AlarmAction);
	}
	*/
	public List<Alert> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, NCAL.Event));
	}
	
	@Override
	public Alert fromJena(Model source, Resource res)  throws IllegalArgumentException {
			Alert<User> alert = new Alert<User>();
			try {
				alert.setResourceURL(new URL(res.getURI()));
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an alert",res.getURI())
				);
			}				
			if (res.getProperty(DCTerms.title) != null)
				alert.setTitle(res.getProperty(DCTerms.title).getString());
				
			if (res.getProperty(DCTerms.subject) != null)
				alert.setQueryString(res.getProperty(DCTerms.subject).getString());
				
			if (res.getProperty(NCAL.categories) != null) 
				alert.setType(QueryType.valueOf(res.getProperty(NCAL.categories).getString()));
				
			if (res.getProperty(NCAL.rrule) != null) try {
				
				Resource rrule = res.getPropertyResourceValue(NCAL.rrule);
				if (rrule.getPropertyResourceValue(NCAL.freq)!=null) 
					alert.setRecurrenceFrequency(RecurrenceFrequency.valueOf(rrule.getPropertyResourceValue(NCAL.freq).getLocalName()));
				if(rrule.getProperty(NCAL.interval)!=null)
					alert.setRecurrenceInterval(rrule.getProperty(NCAL.interval).getInt());
					
			} catch (Exception x) {
				throw new IllegalArgumentException(x);	
			}
			try {
				if (res.getPropertyResourceValue(NCAL.attendee) != null) 
					alert.setUser(new User(new URL(res.getPropertyResourceValue(NCAL.attendee).getURI())));
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an alert",
										res.getPropertyResourceValue(NCAL.attendee).getURI()));

			}

		return alert;
	}

}
