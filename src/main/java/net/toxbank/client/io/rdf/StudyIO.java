package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Study;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class StudyIO implements IOClass<Study> {

	public Model toJena(Model toAddTo, Study... accounts) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (accounts == null) return toAddTo;

		for (Study study : accounts) {
			if (study.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "Studies"));
			}
			Resource res = toAddTo.createResource(study.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.STUDY);
		}
		return toAddTo;
	}

	public List<Study> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.STUDY);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Study> accounts = new ArrayList<Study>();
		while (iter.hasNext()) {
			Study study = new Study();
			Resource res = iter.next();
			try {
				study.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}
			accounts.add(study);
		}

		return accounts;
	}

}
