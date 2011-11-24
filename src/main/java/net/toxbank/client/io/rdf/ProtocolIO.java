package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Protocol;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class ProtocolIO implements IOClass<Protocol> {

	public Model toJena(Model toAddTo, Protocol... protocols) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (protocols == null) return toAddTo;

		for (Protocol protocol : protocols) {
			if (protocol.getResourceURL() == null) {
				throw new IllegalArgumentException("All protocols must have resource URIs.");
			}
			Resource res = toAddTo.createResource(protocol.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.PROTOCOL);
			if (protocol.getTitle() != null)
				res.addLiteral(DCTerms.title, protocol.getTitle());
			if (protocol.getIdentifier() != null)
				res.addLiteral(DCTerms.identifier, protocol.getIdentifier());
			if (protocol.getAbstract() != null)
				res.addLiteral(TOXBANK.HASABSTRACT, protocol.getAbstract());
		}
		return toAddTo;
	}

	public List<Protocol> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.PROTOCOL);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Protocol> protocols = new ArrayList<Protocol>();
		while (iter.hasNext()) {
			Protocol protocol = new Protocol();
			Resource res = iter.next();
			System.out.println(res);
			try {
				protocol.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(
					"Found resource with an invalid URI:" + res.getURI()
				);
			}
			if (res.getProperty(DCTerms.title) != null)
				protocol.setTitle(res.getProperty(DCTerms.title).getString());
			if (res.getProperty(DCTerms.identifier) != null)
				protocol.setIdentifier(res.getProperty(DCTerms.identifier).getString());
			if (res.getProperty(TOXBANK.HASABSTRACT) != null)
				protocol.setAbstract(res.getProperty(TOXBANK.HASABSTRACT).getString());
			protocols.add(protocol);
		}

		return protocols;
	}

}
