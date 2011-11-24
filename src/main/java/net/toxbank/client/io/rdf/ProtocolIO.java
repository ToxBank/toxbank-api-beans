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
import com.hp.hpl.jena.vocabulary.RDF;

public class ProtocolIO implements IOClass<Protocol> {

	public Model toJena(Model toAddTo, Protocol... protocols) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (protocols == null) return toAddTo;

		for (Protocol protocol : protocols) {
			if (protocol.getResourceURL() == null) {
				throw new IllegalArgumentException("All protocols must have resource URIs.");
			}
			toAddTo.add(
			    toAddTo.createResource(protocol.getResourceURL().toString()),
			    RDF.type,
			    TOXBANK.PROTOCOL
			);
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
			try {
				protocol.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(
					"Found resource with an invalid URI:" + res.getURI()
				);
			}
			protocols.add(protocol);
		}

		return protocols;
	}

}
