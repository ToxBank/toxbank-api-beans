package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Organisation;
import net.toxbank.client.resource.Study;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class StudyIO extends AbstractIOClass<Study> {

	@Override
	public Resource objectToJena(Model toAddTo, Study study)
			throws IllegalArgumentException {

			if (study.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "Studies"));
			}
			Resource res = toAddTo.createResource(study.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK_ISA.STUDY);
			if (study.getTitle() != null)
				res.addLiteral(DCTerms.title, study.getTitle());
			List<String> keywords = study.getKeywords();
			if (keywords != null) {
				for (String keyword : keywords)
					res.addLiteral(TOXBANK.HASKEYWORD, keyword);
			}
			if (study.getOwner() != null) {
				if (study.getOwner().getResourceURL()==null)
					throw new IllegalArgumentException(String.format(msg_InvalidURI, "study owner",res.getURI()));
					
				Resource ownerRes = toAddTo.createResource(
					study.getOwner().getResourceURL().toString()
				);
				res.addProperty(TOXBANK.HASOWNER, ownerRes);
			}

		return res;
	}
	
	public List<Study> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, TOXBANK_ISA.STUDY));
	}
	
	@Override
	public Study fromJena(Model source, Resource res)
			throws IllegalArgumentException {

			Study study = new Study();

			try {
				study.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}
			if (res.getProperty(DCTerms.title) != null)
				study.setTitle(res.getProperty(DCTerms.title).getString());
			StmtIterator keywords = res.listProperties(TOXBANK.HASKEYWORD);
			while (keywords.hasNext()) {
				study.addKeyword(keywords.next().getString());
			}
			String uri = null;
			if (res.getProperty(TOXBANK.HASOWNER) != null)
				try {
					uri = res.getProperty(TOXBANK.HASOWNER).getResource().getURI();		
					Organisation org = new Organisation();
					
					org.setResourceURL(
						new URL(uri)
					);
					study.setOwner(org);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"a study owner",uri));
				}


		return study;
	}

}
