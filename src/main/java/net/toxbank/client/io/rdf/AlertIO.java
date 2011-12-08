package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Alert;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class AlertIO implements IOClass<Alert> {

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

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.ALERT);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Alert> accounts = new ArrayList<Alert>();
		while (iter.hasNext()) {
			Alert alert = new Alert();
			Resource res = iter.next();
			try {
				alert.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}
			accounts.add(alert);
		}

		return accounts;
	}

}
