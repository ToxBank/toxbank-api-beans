package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Document;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class DocumentIO implements IOClass<Document> {

	public Model toJena(Model toAddTo, Document... accounts) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (accounts == null) return toAddTo;

		for (Document doc : accounts) {
			if (doc.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "docs"));
			}
			Resource res = toAddTo.createResource(doc.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.DOCUMENT);
			if (doc.getLicense() != null) {
				toAddTo.add(res, DCTerms.license, toAddTo.createResource(
					doc.getLicense().toString()
				));
			}
		}
		return toAddTo;
	}

	public List<Document> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.DOCUMENT);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Document> docs = new ArrayList<Document>();
		while (iter.hasNext()) {
			Document doc = new Document();
			Resource res = iter.next();
			try {
				doc.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}
			if (res.getProperty(DCTerms.license) != null)
				try {
					doc.setLicense(new URL(res.getProperty(DCTerms.license).getObject().toString()));
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"a license",res.getProperty(DCTerms.license).getObject())
					);
				}
			docs.add(doc);
		}

		return docs;
	}

}
