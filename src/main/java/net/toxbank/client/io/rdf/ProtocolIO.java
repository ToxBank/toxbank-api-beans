package net.toxbank.client.io.rdf;

import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Protocol;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
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
		return (List<Protocol>)null;
	}

}
