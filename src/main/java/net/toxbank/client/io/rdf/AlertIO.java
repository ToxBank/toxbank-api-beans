package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Alert;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class AlertIO  extends AbstractIOClass<Alert> {

	public Model toJena(Model toAddTo, Alert... accounts) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (accounts == null) return toAddTo;

		for (Alert alert : accounts) {
			if (alert.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "alerts"));
			}
			Resource res = toAddTo.createResource(alert.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.ALERT);
		}
		return toAddTo;
	}
	
	public List<Alert> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, TOXBANK.ALERT));
	}
	
	@Override
	public Alert fromJena(Model source, Resource res)  throws IllegalArgumentException {
			Alert alert = new Alert();
			try {
				alert.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}

		return alert;
	}

}
