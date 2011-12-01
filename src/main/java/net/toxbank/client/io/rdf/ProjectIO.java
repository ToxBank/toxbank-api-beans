package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Project;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class ProjectIO implements IOClass<Project> {
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
			toAddTo.add(res, RDF.type, TOXBANK.PROJECT);
		}
		return toAddTo;
	}

	public List<Project> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.PROJECT);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Project> projects = new ArrayList<Project>();
		while (iter.hasNext()) {
			Project project = new Project();
			Resource res = iter.next();
			System.out.println(res);
			try {
				project.setResourceURL(
					new URL(res.getURI())
				);
				if (res.getProperty(DCTerms.title) != null)
					project.setTitle(res.getProperty(DCTerms.title).getString());				
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"a project",res.getURI()));
			}
			projects.add(project);
		}

		return projects;
	}

}
