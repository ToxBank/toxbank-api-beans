package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Assay;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class AssayIO  extends AbstractIOClass<Assay> {


	@Override
	public Resource objectToJena(Model toAddTo, Assay assay)
			throws IllegalArgumentException {
		if (assay.getResourceURL() == null) {
			throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "Assays"));
		}
		Resource res = toAddTo.createResource(assay.getResourceURL().toString());
		toAddTo.add(res, RDF.type, TOXBANK.ASSAY);
		return res;
	}
	public List<Assay> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, TOXBANK.ASSAY));
	}
	
	@Override
	public Assay fromJena(Model source, Resource res)  throws IllegalArgumentException {
			Assay assay = new Assay();
			try {
				assay.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an assay",res.getURI())
				);
			}
		return assay;
	}

}
