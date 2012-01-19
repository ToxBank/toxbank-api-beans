package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Organisation;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class OrganisationIO extends AbstractIOClass<Organisation> {

	@Override
	public Resource objectToJena(Model toAddTo, Organisation org)
			throws IllegalArgumentException {
		if (org.getResourceURL() == null) {
			throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "organisations"));
		}
		Resource res = toAddTo.createResource(org.getResourceURL().toString());
		toAddTo.add(res, RDF.type, TOXBANK.ORGANIZATION);
		if (org.getTitle() != null)
			res.addLiteral(DCTerms.title, org.getTitle());
		if (org.getGroupName() != null)
			res.addLiteral(TOXBANK.HASTBACCOUNT, org.getGroupName());
		return res;
	}
	public List<Organisation> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, TOXBANK.ORGANIZATION));
	}
	@Override
	public Organisation fromJena(Model source, Resource res)
			throws IllegalArgumentException {

			Organisation org = new Organisation();
			try {
				org.setResourceURL(
					new URL(res.getURI())
				);
				if (res.getProperty(DCTerms.title) != null)
					org.setTitle(res.getProperty(DCTerms.title).getString());	
				
				if (res.getProperty(TOXBANK.HASTBACCOUNT) != null)
					org.setGroupName(res.getProperty(TOXBANK.HASTBACCOUNT).getString());
				
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an organisation",res.getURI()));
			}

		return org;
	}

}
