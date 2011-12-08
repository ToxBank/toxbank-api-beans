package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Template;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class TemplateIO implements IOClass<Template> {

	public Model toJena(Model toAddTo, Template... accounts) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (accounts == null) return toAddTo;

		for (Template template : accounts) {
			if (template.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "templates"));
			}
			Resource res = toAddTo.createResource(template.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.TEMPLATE);
		}
		return toAddTo;
	}

	public List<Template> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.TEMPLATE);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Template> accounts = new ArrayList<Template>();
		while (iter.hasNext()) {
			Template template = new Template();
			Resource res = iter.next();
			try {
				template.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}
			accounts.add(template);
		}

		return accounts;
	}

}
