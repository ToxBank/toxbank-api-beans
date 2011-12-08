package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Assay;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class AssayIO implements IOClass<Assay> {

	public Model toJena(Model toAddTo, Assay... accounts) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (accounts == null) return toAddTo;

		for (Assay assay : accounts) {
			if (assay.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "Assays"));
			}
			Resource res = toAddTo.createResource(assay.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.ASSAY);
		}
		return toAddTo;
	}

	public List<Assay> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.ASSAY);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Assay> accounts = new ArrayList<Assay>();
		while (iter.hasNext()) {
			Assay assay = new Assay();
			Resource res = iter.next();
			try {
				assay.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}
			accounts.add(assay);
		}

		return accounts;
	}

}
