package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Document;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class DocumentIO  extends AbstractIOClass<Document> {

	public Model toJena(Model toAddTo, Document... accounts) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (accounts == null) return toAddTo;

		for (Document doc : accounts) {
			if (doc.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "docs"));
			}
			Resource res = toAddTo.createResource(doc.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.DOCUMENT);
		}
		return toAddTo;
	}
	
	public List<Document> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, TOXBANK.DOCUMENT));
	}
	
	@Override
	public Document fromJena(Model source, Resource res)  throws IllegalArgumentException {
			Document doc = new Document();
			try {
				doc.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"a document",res.getURI())
				);
			}
		return doc;
	}

}
