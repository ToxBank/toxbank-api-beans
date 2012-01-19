package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Project;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class ProjectIO extends AbstractIOClass<Project> {
	public final String message =  "All projects must have resource URIs.";
	public Model toJena(Model toAddTo, Project... projects) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (projects == null) return toAddTo;

		for (Project project : projects) {
			if (project.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "projects"));
			}
			Resource res = toAddTo.createResource(project.getResourceURL().toString());
			if (project.getTitle() != null)
				res.addLiteral(DCTerms.title, project.getTitle());		
			if (project.getGroupName() != null)
				res.addLiteral(TOXBANK.HASTBACCOUNT, project.getGroupName());			
			toAddTo.add(res, RDF.type, TOXBANK.PROJECT);
		}
		return toAddTo;
	}

	public List<Project> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, TOXBANK.PROJECT));
	}
	@Override
	public Project fromJena(Model source, Resource res)
			throws IllegalArgumentException {
			Project project = new Project();
			try {
				project.setResourceURL(
					new URL(res.getURI())
				);
				if (res.getProperty(DCTerms.title) != null)
					project.setTitle(res.getProperty(DCTerms.title).getString());
				
				if (res.getProperty(TOXBANK.HASTBACCOUNT) != null)
					project.setGroupName(res.getProperty(TOXBANK.HASTBACCOUNT).getString());
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"a project",res.getURI()));
			}
		return project;
	}

}
